package com.example.demo.dao;

import com.example.demo.model.Score;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ScoreDao {

    String insertScore(Score score) throws IOException;

    List<Score> selectAllScore();

    List<Score> selectAllScoreByEmail(String email);

    Optional<Score> selectScoreByEmailScoreId(String email, int scoreId);

    int deleteAllScoreByEmail(String email);

    int deleteScoreByEmailScoreId(String email, int scoreId);

    ResponseEntity<Resource> download(HttpServletRequest response,String email,int scoreId) throws FileNotFoundException;

}
