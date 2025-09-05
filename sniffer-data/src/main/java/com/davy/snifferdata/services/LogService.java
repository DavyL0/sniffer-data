package com.davy.snifferdata.services;

import com.davy.snifferdata.models.Log;
import com.davy.snifferdata.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;
    private final NotifierService notifier;

    public LogService(LogRepository logRepository, NotifierService notifier) {
        this.logRepository = logRepository;
        this.notifier = notifier;
    }

    public Log saveLog(Log log) {
        if ("ERROR".equalsIgnoreCase(log.getLevel()) || "CRITICAL".equalsIgnoreCase(log.getLevel())) {
            notifier.alert("Novo log crítico: " + log.getMessage());
        }
        return logRepository.save(log);
    }

    public List<Log> findAll() {
        return logRepository.findAll();
    }
}
