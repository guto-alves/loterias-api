package com.gutotech.loteriasapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.model.ResultadoId;

@Repository
public interface ResultadoRepository extends MongoRepository<Resultado, ResultadoId> {

	List<Resultado> findById_Loteria(String loteria);

}
