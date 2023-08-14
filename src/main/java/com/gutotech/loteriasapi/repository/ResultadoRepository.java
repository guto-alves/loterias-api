package com.gutotech.loteriasapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.model.ResultadoId;

@Repository
public interface ResultadoRepository extends MongoRepository<Resultado, ResultadoId> {

    List<Resultado> findById_Loteria(String loteria);

    @Query(sort = "{ '_id.concurso' : -1 }")
    Optional<Resultado> findTopById_Loteria(String loteria);

}
