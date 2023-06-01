package com.example.demo.constant;

import java.util.Locale;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:07 AM
 * @project qlsv
 */
public class DtsConstant {

    // CONFIG DATASOURCE
    public static final String SPRING_DATASOURCE_DRIVER_CLASS_NAME = "spring.datasource.driver-class-name";
    public static final String SPRING_DATASOURCE_URL = "spring.datasource.url";
    public static final String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    public static final String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";
    public static final String SPRING_DATASOURCE_JNDI_NAME = "spring.datasource.jndi-name";

    // ENCODING
    public static final String UTF_8 = "UTF-8";

    // JCA QUARTZ CONFIG
    public static final String JCA_QUARTZ_CONFIG_OVERWRITE_EXISTING_JOBS = "jca-quartz.config.overwrite-existing-jobs";
    public static final String JCA_QUARTZ_CONFIG_SCHEDULER_NAME = "jca-quartz.config.scheduler-name";
    public static final String JCA_QUARTZ_CONFIG_WAIT_FOR_JOBS_TO_COMPLETE_ON_SHUTDOWN = "jca-quartz.config.wait-for-jobs-to-complete-on-shutdown";
    public static final String JCA_QUARTZ_CONFIG_AUTO_STARTUP = "jca-quartz.config.auto-startup";

    // NUMBER_FORMAT
    public static final String NUMBER_FORMAT = "###,###.##";

    // API - EXTERNAL
    public static final int API_EXTERNAL_TIMEOUT = 120000;
    /** Call api with service REST Full */
    public static final String API_PROTOCOL_REST_FULL = "REST_FULL";
    /** Call api with service SOAP */
    public static final String API_PROTOCOL_SOAP = "SOAP";

    // CHARACTER SPECIAL
    /** QUESTION_MARK */
    public static final String QUESTION_MARK = "?";
    public static final String EMPTY = "";
    public static final String UNDERLINED = "_";
    public static final String SLASH = "/";
    public static final String HYPHEN = "-";
    public static final String ASTERISK = "*";
    public static final String DOLLAR = "$";
    public static final String DOT = ".";
    public static final String AMPERSAND = "&";
    public static final String COMMA = ",";
    public static final String OPENING_CURLY_BRACE = "{";
    public static final String SEMI_COLON = ";";
    public static final String CLOSING_CURLY_BRACE = "}";
    /** HASHTAG */
    public static final String HASHTAG = "#";
    public static final String OPENING_BRACKET = "[";
    public static final String CLOSING_BRACKET = "]";
    public static final String SPACE = " ";
    public static final String DOUBLE_SLASH = "//";
    public static final String FOUR_BACK_SLASH = "\\\\";
    public static final String UNDERSCORE = "_";
    public static final char CHAR_DOUBLE_QUOTATION_MARK = '"';
    public static final char CHAR_IS_LESS_THAN = '<';
    public static final char CHAR_IS_MORE_THAN = '>';
    public static final char CHAR_AMPERSAND = '&';
    public static final String SHARP = "#";

    // EXTERNAL CONFIG DATA SOURCE HIKARI
    public static final String SPRING_DATASOURCE_POOL_NAME = "spring.datasource.hikari.poolName";
    public static final String SPRING_DATASOURCE_AUTO_COMMIT = "spring.datasource.hikari.auto-commit";
    public static final String SPRING_DATASOURCE_REGISTER_MBEANS = "spring.datasource.hikari.register-mbeans";
    public static final String SPRING_DATASOURCE_MINIMUM_IDLE = "spring.datasource.hikari.minimum-idle";
    public static final String SPRING_DATASOURCE_IDLE_TIMEOUT = "spring.datasource.hikari.idle-timeout";
    public static final String SPRING_DATASOURCE_CONNECTION_TIMEOUT = "spring.datasource.hikari.connection-timeout";
    public static final String SPRING_DATASOURCE_MAX_LIFETIME = "spring.datasource.hikari.max-lifetime";

    // FORMAT DATE
    public static final String DDMMYYYY_TIME_HYPHEN = "dd-MM-yyyy hh:mm:ss";
    public static final String DDMMYYYY_SLASH = "dd/MM/yyyy";
    public static final String YYYYMMDD_TIME = "yyyyMMddHHmmss";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String DDMMYYYY_TIME_SLASH = "dd/MM/yyyy hh:mm:ss";
    public static final String YYMMDD = "yyMMdd";

    // JWT
    public static final int JWT_LENGTH = 8;
    public static final String JWT_DEVICE_ID = "DEVICE-ID";
    public static final String JWT_TOKEN = "TOKEN";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_CONTENT_LANGUAGE = "Content-language";
    public static final String JWT_LANGUAGE_DEFAULT = "en";

    // NUMBER CONSTANT
    public static final int NUMBER_ONE = 1;
    public static final int NUMBER_ZERO = 0;
    public static final int NUMBER_TWO = 2;
    public static final int NUMBER_THREE = 3;
    public static final int NUMBER_FOUR = 4;

    // NUMBER FORMAT CHARACTER OR INT
    public static final String STR_ZERO = "0";
    public static final String STR_ONE = "1";
    public static final String STR_TWO = "2";
    public static final String STR_THREE = "3";
    public static final String STR_FOUR = "4";

    // NUMBER LONG CONSTANT
    public static final long NUMBER_ONE_L = 1L;
    public static final long NUMBER_ZERO_L = 0L;

    // REPONSE CODE - MESSAGE
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String INFO = "info";
    public static final String WARNING = "warning";
    public static final int RESULT_CODE_SYSTEM_ERROR = -99;
    public static final int SUCCESS_CODE = 200;

    // REQUEST METHOD
    public static final String REQUEST_POST = "POST";
    public static final String REQUEST_PUT = "PUT";
    public static final String REQUEST_DELETE = "DELETE";

    // SEQUENCE
    public static final String SEQ = "SEQ_";

    public static final String PASSWORD_MASK = "********";

    public static final String DEFAULT_LANGUAGE = "VI";
    public static final String DEFAULT_COUNTRY = "VN";
    public static final Locale DEFAULT_LOCALE = new Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY);

}
