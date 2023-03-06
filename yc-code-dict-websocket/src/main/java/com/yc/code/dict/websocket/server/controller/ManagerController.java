package com.yc.code.dict.websocket.server.controller;

import com.yc.code.dict.websocket.server.ws.LivePlayEndPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    @GetMapping("/killById")
    public void killById(@RequestParam String id) throws IOException {
        LivePlayEndPoint.killById(id);
    }
}
