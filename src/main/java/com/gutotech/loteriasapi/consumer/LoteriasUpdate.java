package com.gutotech.loteriasapi.consumer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void checkForUpdates() throws IOException {
		for (Loteria loteria : Loteria.values()) {
			loteriaUpdateTask.checkForUpdates(loteria.toString());
		}
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
				myLatestResultado.setAcumuladaProxConcurso(latestResultado.getAcumuladaProxConcurso());
				myLatestResultado.setDataProxConcurso(latestResultado.getDataProxConcurso());
				resultadoService.save(myLatestResultado);
			} else if (myLatestResultado.getConcurso() < latestResultado.getConcurso()) {
				for (int concurso = myLatestResultado.getConcurso() + 1; 
						concurso <= latestResultado.getConcurso(); concurso++) {
					try {
						Resultado resultado = consumer.getResultado(loteria, concurso);
						resultadoService.save(resultado);
					} catch (IOException e) {
						System.out.println("Erro: " + e.getMessage());
						--concurso;
					}
				}
			}
		}
	}

}
