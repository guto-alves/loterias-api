package com.gutotech.loteriasapi.service;

import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.model.ResultadoId;
import com.gutotech.loteriasapi.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository repository;

    @Cacheable("resultados")
    public List<Resultado> findByLoteria(String loteria) {
        return repository.findById_Loteria(loteria) //
                .stream() //
                .sorted(Comparator.comparing(Resultado::getConcurso).reversed()) //
                .collect(Collectors.toList());
    }

    public Resultado findByLoteriaAndConcurso(String loteria, int concurso) {
        return repository.findById(new ResultadoId(loteria, concurso)).orElse(null);
    }

    public Resultado findLatest(String loteria) {
        return repository.findTopById_Loteria(loteria).orElse(null);
    }

    public void save(Resultado resultado) {
        repository.save(resultado);
    }

    public void saveAll(List<Resultado> resultados) {
        repository.saveAll(resultados);
    }

}
