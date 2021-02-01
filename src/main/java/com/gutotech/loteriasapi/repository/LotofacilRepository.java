package com.gutotech.loteriasapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.loteriasapi.model.Lotofacil;

@Repository
public interface LotofacilRepository extends MongoRepository<Lotofacil, Integer> {

	Lotofacil findTopByOrderByConcursoDesc();
}
