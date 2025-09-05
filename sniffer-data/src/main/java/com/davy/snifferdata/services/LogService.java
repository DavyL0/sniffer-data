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

    public LogRepository createLog(String source, String message, Date createdAt) {
        Log log = new Log();
        log.setSource(source);
        log.setMessage(message);
        log.setCreatedAt(createdAt);

        return (LogRepository) logRepository.save(log);
    }

    public List<Log> findBySource(String source) {
        return logRepository.findAll();
    }
}
