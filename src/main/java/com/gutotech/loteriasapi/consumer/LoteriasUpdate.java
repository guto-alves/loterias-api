package com.gutotech.loteriasapi.consumer;

import java.io.IOException;
import java.net.SocketTimeoutException;

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

    public void checkForUpdates() throws IOException {
	for (Loteria loteria : Loteria.values()) {
	    loteriaUpdateTask.checkForUpdates(loteria.toString());
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
	public void checkForUpdates(final String loteria) throws IOException {
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

		for (int concurso = myLatestResultado.getConcurso() + 1; concurso <= latestResultado
			.getConcurso(); concurso++) {
		    try {
			Resultado resultado = consumer.getResultado(loteria,
				String.valueOf(concurso));
			resultadoService.save(resultado);
		    } catch (SocketTimeoutException | java.net.ConnectException e) {
			System.out.printf("Erro (A): (%s, %d) - %s %s \n", loteria, concurso,
				e.getClass(), e.getMessage());
			--concurso;
		    } catch (IOException e) {
			System.out.printf("Erro (I): (%s, %d) - %s %s \n", loteria, concurso,
				e.getClass(), e.getMessage());
		    } catch (Exception e) {
			System.out.printf("Erro (E): (%s, %d) - %s %s \n", loteria, concurso,
				e.getClass(), e.getMessage());
			e.printStackTrace();
		    }
		}

		System.out.println("TERMINANDO " + loteria);
	    }
	}
    }

}
