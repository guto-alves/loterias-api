package com.gutotech.loteriasapi.consumer;

import com.gutotech.loteriasapi.model.Loteria;
import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.service.ResultadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Component
public class LoteriasUpdate {
    private static final Logger logger = LoggerFactory.getLogger(LoteriasUpdate.class);

    @Autowired
    private LoteriaUpdateTask loteriaUpdateTask;

    @Autowired
    private CacheManager cacheManager;

    public void checkForUpdates() {
        for (Loteria loteria : Loteria.values()) {
            try {
                loteriaUpdateTask.checkForUpdates(loteria.getNome());
            } catch (Exception e) {
                logger.error("Error updating {}", loteria.getNome(), e);
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
            int myLatestConcurso = nonNull(myLatestResultado) ? myLatestResultado.getConcurso() : 0;

            if (myLatestConcurso == latestResultado.getConcurso()) {
                myLatestResultado.setData(latestResultado.getData());
                myLatestResultado.setLocal(latestResultado.getLocal());

                myLatestResultado.setPremiacoes(latestResultado.getPremiacoes());
                myLatestResultado.setEstadosPremiados(latestResultado.getEstadosPremiados());
                myLatestResultado.setAcumulou(latestResultado.isAcumulou());
                myLatestResultado.setDataProximoConcurso(latestResultado.getDataProximoConcurso());

                resultadoService.save(myLatestResultado);
                logger.info("{} is up to date at concurso {}", loteria, myLatestResultado.getConcurso());
            } else if (myLatestConcurso < latestResultado.getConcurso()) {
                logger.info("Updating {} from {} to {}", loteria, myLatestConcurso, latestResultado.getConcurso());

                Map<String, Integer> tentativasMap = new HashMap<>();

                for (int concurso = myLatestConcurso + 1; concurso <= latestResultado.getConcurso(); concurso++) {
                    try {
                        Resultado resultado = consumer.getResultado(loteria, String.valueOf(concurso));
                        resultadoService.save(resultado);
                        logger.info("Saved result for {} {}", loteria, concurso);
                    } catch (Exception e) {
                        int total = tentativasMap.getOrDefault(loteria + "-" + concurso, 0);

                        if (total < 20) {
                            tentativasMap.put(loteria + "-" + concurso, ++total);
                            logger.error("Error fetching result for {} {}, attempt {}", loteria, concurso, total, e);
                            --concurso;
                        } else {
                            logger.error("Giving up fetching result for {} {} after {} attempts",
                                    loteria, concurso, total);
                        }
                    }
                }

                logger.info("Finished updating {}", loteria);
            }
        }
    }

}
