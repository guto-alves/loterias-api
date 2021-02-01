package com.gutotech.loteriasapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.loteriasapi.model.MegaSena;

@Repository
public interface MegaSenaRepository extends MongoRepository<MegaSena, Integer> {

	MegaSena findTopByOrderByConcursoDesc();

}
