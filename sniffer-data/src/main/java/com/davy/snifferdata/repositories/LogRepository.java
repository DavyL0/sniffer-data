package com.davy.snifferdata.repositories;

import com.davy.snifferdata.models.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<Log,String> {
}
