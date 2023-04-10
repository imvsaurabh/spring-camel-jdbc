package com.github.imvsaurabh.cameljdbc.route;

import com.github.imvsaurabh.cameljdbc.processor.MessageProcessor;
import com.github.imvsaurabh.cameljdbc.constant.AppConstant;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : saurabh
 * @create : 08-04-2023 10:41
 **/

@Component
public class ActiveMQRoutes extends RouteBuilder {

    private final String inputQueue;
    private final String outputQueue;
    private final String errorQueue;

    public ActiveMQRoutes(@Value(AppConstant.APP_INPUT_QUEUE) String appInputQueue,
                          @Value(AppConstant.APP_OUTPUT_QUEUE) String appOutputQueue,
                          @Value(AppConstant.APP_ERROR_QUEUE) String appErrorQueue) {
        this.inputQueue = appInputQueue;
        this.outputQueue = appOutputQueue;
        this.errorQueue = appErrorQueue;
    }

    @Override
    public void configure() throws Exception {
        onException(RuntimeException.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        log.info("Exception stackTrace sent to error queue");
                    }
                })
                .to(errorQueue).handled(true);

        from(inputQueue)
                .log("Receiving message from input queue")
                .process(new MessageProcessor())
                .log("Sending message to output queue")
                .to(outputQueue)
                .log("Sent message to output queue");
    }
}
