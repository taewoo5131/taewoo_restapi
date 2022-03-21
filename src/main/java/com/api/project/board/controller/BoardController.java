package com.api.project.board.controller;

import com.api.project.board.service.BoardService;
import com.api.project.result.ResultEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 리스트 조회
     */
    @GetMapping
    public ResponseEntity getList(@RequestParam(required = false) String pageNo, HttpServletRequest request) {
        try{
            ResponseEntity list = boardService.getList(pageNo);
            log.info("getList success");
            return list;
        }catch(Exception e){
            log.error(e.toString());
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 게시글 단건 조회
     */
    @GetMapping("/{boardNo}")
    public ResponseEntity getBoard(@PathVariable(value = "boardNo",required = false) String boardNo) {
        try{
            ResponseEntity board = boardService.getBoard(boardNo);
            log.info("getBoard success");
            return board;
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.toString());
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 게시글 등록
     */
    @PostMapping
    public ResponseEntity postBoard(@RequestBody Map<String, String> paramMap) {
        ResponseEntity response = boardService.insertBoard(paramMap);
        return response;
    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/{boardNo}")
    public void patchBoard(@PathVariable("boardNo") String boardNo) {
        System.out.println("patchBoard >> " + boardNo);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{boardNo}")
    public void deleteBoard(@PathVariable("boardNo") String boardNo) {
        System.out.println("deleteBoard >> " + boardNo);
    }
}
