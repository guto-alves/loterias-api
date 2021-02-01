package com.gutotech.loteriasapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.loteriasapi.model.Quina;

@Repository
public interface QuinaRepository extends MongoRepository<Quina, Integer> {

	Quina findTopByOrderByConcursoDesc();
}
