package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:04 AM
 * @project qlsv
 */
@Getter
@AllArgsConstructor
public class BaseException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private final int status;
    private final String message;
    private final String title;
    private final ExceptionCode exceptionCode;
    private final Object[] args;
    private final String defaultMessage;

    /**
     * @author NienNQ
     */
    public BaseException(int status, String message, String title, String exceptionCode, Object[] args,
                         String defaultMessage) {
        this.status = status;
        this.message = message;
        this.title = title;
        this.exceptionCode = new ExceptionCode(exceptionCode);
        this.args = args;
        this.defaultMessage = defaultMessage;
    }
}
