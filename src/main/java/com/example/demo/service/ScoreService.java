package com.example.demo.service;

import com.example.demo.dao.ScoreDao;
import com.example.demo.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    private final ScoreDao scoreDao;

    @Autowired
    public ScoreService(@Qualifier("postgres2") ScoreDao scoreDao){this.scoreDao = scoreDao;}

    public String insertScore(Score score) throws IOException {
        return scoreDao.insertScore(score);
    }

    public List<Score> selectAllScoreByEmail(String email){
        return scoreDao.selectAllScoreByEmail(email);
    }

    public List<Score> selectAllScore(){return scoreDao.selectAllScore();}

    public Optional<Score> selectScoreByEmailScoreId(String email, int scoreId){
        return scoreDao.selectScoreByEmailScoreId(email,scoreId);
    }

    public int deleteAllScoreByEmail(String email){
        return scoreDao.deleteAllScoreByEmail(email);
    }

    public int deleteScoreByEmailScoreId(String email, int scoreId){
        return scoreDao.deleteScoreByEmailScoreId(email,scoreId);
    }

    public ResponseEntity<Resource> download(HttpServletRequest response,String email,int scoreId) throws FileNotFoundException {
        return scoreDao.download(response,email,scoreId);
    }
}
