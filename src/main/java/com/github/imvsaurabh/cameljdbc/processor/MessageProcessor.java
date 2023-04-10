package com.github.imvsaurabh.cameljdbc.processor;

import com.github.imvsaurabh.cameljdbc.constant.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static net.logstash.logback.argument.StructuredArguments.value;

/**
 * @author : saurabh
 * @create : 08-04-2023 11:06
 **/

@Slf4j
@Component
public class MessageProcessor implements Processor {
    private String applicationName;

    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            log.info("Message Processor Started",
                    value(AppConstant.MESSAGE_BODY, exchange.getIn().getBody().toString()),
                    value(AppConstant.APPLICATION_NAME_STR, applicationName));
            throw new NullPointerException("Found a null value");
        } catch (RuntimeException exception) {
            log.error("Exception occurred in MessageProcessor",
                    value(AppConstant.EXCEPTION_MESSAGE, ExceptionUtils.getMessage(exception)),
                    value(AppConstant.EXCEPTION, ExceptionUtils.getStackTrace(exception)),
                    value(AppConstant.APPLICATION_NAME_STR, applicationName));
            DefaultMessage defaultMessage = new DefaultMessage(exchange);
            defaultMessage.setBody(ExceptionUtils.getStackTrace(exception));
            defaultMessage.setHeader(AppConstant.HAS_ERROR, Boolean.TRUE);
            exchange.setException(exception);
            exchange.setMessage(defaultMessage);
        }
    }
}
