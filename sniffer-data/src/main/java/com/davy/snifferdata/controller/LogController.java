package com.davy.snifferdata.controller;

import com.davy.snifferdata.models.Log;
import com.davy.snifferdata.services.LogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public Log save(@RequestBody Log log){
        return logService.saveLog(log);
    }

    @GetMapping
    public List<Log> findAll(){
        return logService.findAll();
    }
}
