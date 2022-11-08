package com.example.demo.api;

import com.example.demo.model.Score;
import com.example.demo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v2/score")
@RestController
public class ScoreController {

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService){this.scoreService = scoreService;};
    // 서버에 FFT 정보 저장
    @PostMapping
    public String insertScore(@Valid @NonNull @RequestBody Score score) throws IOException {return scoreService.insertScore(score);}
    // 모든 정보 출력
    @GetMapping
    public List<Score> selectAllScore() {return scoreService.selectAllScore();}
    // 특정 회원의 정보들 출력
    @GetMapping(path = "/{email}")
    public List<Score> selectAllScoreByEmail(@PathVariable("email") String email){return scoreService.selectAllScoreByEmail(email);}
    // 특정 회원의 특정 정보 출력
    @GetMapping(path = "/{email}/{scoreId}")
    public Score selectScoreByEmailScoreId(@PathVariable("email")String email,@PathVariable("scoreId")int scoreId){
        return scoreService.selectScoreByEmailScoreId(email,scoreId).orElse(null);
    }
    // 특정 회원의 모든 정보 삭제
    @DeleteMapping(path = "/{email}")
    public void deleteAllScoreByEmail(@PathVariable("email") String email){scoreService.deleteAllScoreByEmail(email);}
    // 특정 회원의 특정 정보 삭제
    @DeleteMapping(path = "/{email}/{scoreId}")
    public void deleteScoreByEmailScoreId(@PathVariable("email")String email,@PathVariable("scoreId")int scoreId){
        scoreService.deleteScoreByEmailScoreId(email,scoreId);
    }
    // 특정 회원의 특정 정보로 그린 악보 다운로드
    @PostMapping(path= "/{email}/{scoreId}")
    public ResponseEntity<Resource> download(HttpServletRequest response, @PathVariable("email")String email,@PathVariable("scoreId")int scoreId) throws FileNotFoundException {
        return scoreService.download(response,email,scoreId);
    }
}
