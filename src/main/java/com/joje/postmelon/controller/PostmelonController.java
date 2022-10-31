package com.joje.postmelon.controller;

import com.joje.postmelon.model.dto.SongDto;
import com.joje.postmelon.model.vo.ResultVo;
import com.joje.postmelon.service.PostMelonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostmelonController {

    private final PostMelonService postMelonService;

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> search(@PathVariable("keyword") String keyword) throws Exception {

        log.debug("[keyword]=[{}]", keyword);
        String id = postMelonService.getSongIdByKeyword(keyword);

        if(id != null & id.length() > 0){
            SongDto song = postMelonService.getSongById(id);
            ResultVo resultVo = new ResultVo();
            log.info("[song]=[{}]", song);
            resultVo.put("target", song);
            return new ResponseEntity<>(resultVo, HttpStatus.OK);
        }else {
            throw new RuntimeException("Not Found Data : keyword=" + keyword);
        }
    }

    @GetMapping("/error/{type}")
    public ResponseEntity<?> error(@PathVariable("type") String type) throws Exception {
        log.debug("[type]=[{}]", type);
        if("".equals(type)){
            throw new RuntimeException();
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
