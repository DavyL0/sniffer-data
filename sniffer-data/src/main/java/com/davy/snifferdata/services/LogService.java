package com.davy.snifferdata.services;

import com.davy.snifferdata.models.Log;
import com.davy.snifferdata.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log saveLog(Log log) {
        return logRepository.save(log);
    }

    public List<Log> findAll() {
        return logRepository.findAll();
    }
}
