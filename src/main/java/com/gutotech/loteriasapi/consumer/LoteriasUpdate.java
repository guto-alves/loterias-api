package com.gutotech.loteriasapi.consumer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.gutotech.loteriasapi.model.Loteria;
import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.service.ResultadoService;

@Component
public class LoteriasUpdate {

    @Autowired
    private LoteriaUpdateTask loteriaUpdateTask;

    @Autowired
    private CacheManager cacheManager;

    public void checkForUpdates() {
        for (Loteria loteria : Loteria.values()) {
            try {
                loteriaUpdateTask.checkForUpdates(loteria.getNome());
            } catch (Exception e) {
                System.out.println("Erro " + loteria + ": " + e.getMessage());
            }
        }

        cacheManager.getCache("resultados").clear();
    }

    @Component
    @EnableAsync
    class LoteriaUpdateTask {
        @Autowired
        private Consumer consumer;

        @Autowired
        private ResultadoService resultadoService;

        @Async
        public void checkForUpdates(String loteria) throws Exception {
            Resultado latestResultado = consumer.getResultado(loteria, null);

            Resultado myLatestResultado = resultadoService.findLatest(loteria);

            if (myLatestResultado.getConcurso() == latestResultado.getConcurso()) {
                myLatestResultado.setData(latestResultado.getData());
                myLatestResultado.setLocal(latestResultado.getLocal());

                myLatestResultado.setPremiacoes(latestResultado.getPremiacoes());
                myLatestResultado.setEstadosPremiados(latestResultado.getEstadosPremiados());
                myLatestResultado.setAcumulou(latestResultado.isAcumulou());
                myLatestResultado.setDataProximoConcurso(latestResultado.getDataProximoConcurso());

                resultadoService.save(myLatestResultado);
            } else if (myLatestResultado.getConcurso() < latestResultado.getConcurso()) {
                System.out.println("COMECANDO " + loteria);

                Map<String, Integer> tentativasMap = new HashMap<>();

                for (int concurso = myLatestResultado.getConcurso() + 1; concurso <= latestResultado
                        .getConcurso(); concurso++) {
                    try {
                        Resultado resultado = consumer.getResultado(loteria, String.valueOf(concurso));
                        resultadoService.save(resultado);
                    } catch (Exception e) {
                        int total = tentativasMap.getOrDefault(loteria + "-" + concurso, 0);

                        if (total < 20) {
                            tentativasMap.put(loteria + "-" + concurso, ++total);
                            --concurso;

                            System.out.printf("ERRO: (%s, %d) - %s %s \n", loteria, concurso, e.getClass(),
                                    e.getMessage());
                        } else {
                            System.out.printf("PARANDO DE BUSCAR (%s, %d)\n", loteria, concurso);
                        }
                    }
                }

                System.out.println("TERMINANDO " + loteria);
            }
        }
    }

}
