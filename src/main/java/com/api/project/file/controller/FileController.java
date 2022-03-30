package com.api.project.file.controller;

import com.api.project.exception.FileUploadFailException;
import com.api.project.file.service.FileService;
import com.api.project.result.ResultEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {



    private final FileService fileService;

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam String boardSeqId){
        try {
            ResponseEntity response = fileService.uploadFile(file, boardSeqId);
            return response;
        } catch (FileUploadFailException e) {
            log.error("[FileController] [uploadFile] > {}", e.getMessage());
            return new ResponseEntity(ResultEnum.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("[FileController] [uploadFile] > {}", e.getMessage());
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{fileName}")
    public ResponseEntity downloadFile(@PathVariable String fileName, HttpServletResponse response) {
        try {
            ResponseEntity result = fileService.downloadFile(fileName, response);
            System.out.println("Controller " + result.toString());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(ResultEnum.SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
