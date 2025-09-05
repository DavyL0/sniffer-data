package com.davy.snifferdata.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "logs")
public class Log {
    @Id
    private String id;
    private String source;
    private String message;
    private String level;
    private Date createdAt = new Date();
}
