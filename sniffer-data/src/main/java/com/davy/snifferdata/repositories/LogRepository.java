package com.davy.snifferdata.repositories;

import com.davy.snifferdata.models.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log,String> {
}
