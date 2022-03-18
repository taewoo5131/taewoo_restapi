package com.api.project.exceptionHandler;

import com.api.project.token.JwtTokenProvider;
import com.api.project.token.exception.TokenException;
import com.api.project.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

/**
 * Dormitory ExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class TokenCheckInterceptorExceptionHandler {
    /**
     * token체크 인터셉터에서 throw한 예외 처리 handler
     * Access token , Refresh Token 체크
     *
     * @return
     */
    @ExceptionHandler(TokenException.class)
    public ResponseEntity tokenExceptionHandler(TokenException e) {
        log.error("this is TokenCheckInterceptorExceptionHandler.tokenExceptionHandler {} ", e.getMessage());
        return new ResponseEntity(ResultEnum.NO_TOKEN, HttpStatus.UNAUTHORIZED);
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {
        log.error("this is ExceptionController.illegalArgumentExceptionHandler {} " , e.getMessage());
        return new ResponseEntity(ResultEnum.SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}