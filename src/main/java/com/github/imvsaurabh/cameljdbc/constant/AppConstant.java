package com.github.imvsaurabh.cameljdbc.constant;

import org.springframework.stereotype.Component;

/**
 * @author : saurabh
 * @create : 08-04-2023 16:38
 **/

public abstract class AppConstant {

    private AppConstant() {}
    public static final String APP_INPUT_QUEUE = "${app.activemq.input}";
    public static final String APP_OUTPUT_QUEUE = "${app.activemq.output}";
    public static final String APP_ERROR_QUEUE = "${app.activemq.error}";
    public static final String MESSAGE_BODY = "messageBody";
    public static final String EXCEPTION = "exception";
    public static final String EXCEPTION_MESSAGE = "exceptionMessage";
    public static final String APPLICATION_NAME = "${spring.application.name}";
    public static final String APPLICATION_NAME_STR = "application";
    public static final String HAS_ERROR = "hasError";
}
