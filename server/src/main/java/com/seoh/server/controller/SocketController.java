package com.seoh.server.controller;

import com.seoh.server.dto.SocketRequest;
import com.seoh.server.dto.SocketResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/receive")

    @SendTo("/send")

    public SocketResponse SocketHandler(SocketRequest socketRequest) {
        SocketResponse socketResponse = new SocketResponse(socketRequest.getMessage());
        return socketResponse;
    }
}
