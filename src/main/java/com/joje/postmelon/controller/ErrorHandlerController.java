package com.joje.postmelon.controller;

import com.joje.postmelon.common.constants.StatusType;
import com.joje.postmelon.model.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerController {

    private static final HttpHeaders HTTP_HEADERS = new HttpHeaders(); // 공통 헤더
    public ErrorHandlerController() {
        HTTP_HEADERS.add("Content-Type", "application/json;charset=UTF-8");
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        e.printStackTrace();
        e.printStackTrace();

        ResultVo resultVo = new ResultVo(StatusType.SERVER_ERROR);
        return new ResponseEntity<>(resultVo, HTTP_HEADERS, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ResultVo> exceptionHandler(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();

        ResultVo resultVo = new ResultVo(StatusType.SERVER_ERROR);

        return new ResponseEntity<>(resultVo, HTTP_HEADERS, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
