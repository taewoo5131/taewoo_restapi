package com.api.project.exception;

import com.api.project.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {

    /**
     * 머글체크 , 기숙사 선정 유무 체크
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("this is ExceptionController.illegalArgumentExceptionHandler {} " , e.getMessage());
        if (e.getMessage().equals("MUGGLE")) {
            return new ResponseEntity(ResultEnum.MUGGLE,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(ResultEnum.NO_DORMITORY,HttpStatus.BAD_REQUEST);
    }
}