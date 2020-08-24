package com.covid19.recovereds.repository;

import com.covid19.recovereds.entity.Recovered;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveredRepository extends MongoRepository<Recovered, Integer> {
}
