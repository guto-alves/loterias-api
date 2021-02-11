package com.gutotech.loteriasapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.loteriasapi.model.Lotomania;

@Repository
public interface LotomaniaRepository extends MongoRepository<Lotomania, Integer> {

	Lotomania findTopByOrderByConcursoDesc();
}
