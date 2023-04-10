package com.github.imvsaurabh.cameljdbc.controller;

import com.github.imvsaurabh.cameljdbc.constant.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : saurabh
 * @create : 08-04-2023 11:10
 **/

@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class AppController {

    private final String inputQueue;

    private final ProducerTemplate template;

    public AppController(ProducerTemplate template,
                         @Value(AppConstant.APP_INPUT_QUEUE) String inputQueue) {
        this.template = template;
        this.inputQueue = inputQueue;
    }

    @GetMapping(path = "/inputToQueue")
    public String inputToQueue(@RequestBody String message) {
        template.sendBody(inputQueue, message);
        return message;
    }
}
