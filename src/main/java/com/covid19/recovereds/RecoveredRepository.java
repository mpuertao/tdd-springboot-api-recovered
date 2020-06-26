package com.covid19.recovereds;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveredRepository extends MongoRepository<Recovered, Integer> {
}
