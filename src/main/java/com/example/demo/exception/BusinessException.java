package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Locale;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:04 AM
 * @project qlsv
 */
@Getter
public class BusinessException extends BaseException {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public static final int STATUS = HttpStatus.BAD_REQUEST.value();

    public static final String MESSAGE = HttpStatus.BAD_REQUEST.name();

    public static final String TITLE = "Business Exception";

    public BusinessException(ExceptionCode exceptionCode) {
        super(STATUS, MESSAGE, TITLE, exceptionCode, null, null);
    }

    public BusinessException(String exceptionCode) {
        super(STATUS, MESSAGE, TITLE, exceptionCode, null, null);
    }

    public BusinessException(String exceptionCode, Object[] args, String defaultMessage, Locale locale) {
        super(STATUS, MESSAGE, TITLE, exceptionCode, args, defaultMessage);
    }

}
