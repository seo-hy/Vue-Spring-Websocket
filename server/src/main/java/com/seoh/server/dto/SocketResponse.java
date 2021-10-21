package com.seoh.server.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SocketResponse {
    private String message;

    private LocalDateTime createdAt;

    public SocketResponse(String message){
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }
}

