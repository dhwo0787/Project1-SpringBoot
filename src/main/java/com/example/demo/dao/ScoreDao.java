package com.example.demo.dao;

import com.example.demo.model.Score;

import java.util.List;
import java.util.Optional;

public interface ScoreDao {

    int insertScore(Score score);

    List<Score> selectAllScoreByEmail(String email);

    Optional<Score> selectScoreByEmailScoreId(Score score);

    int deleteAllScoreByEmail(String email);

    int deleteScoreByEmailScoreId(Score score);

    int updateScoreByEmailScoreId(Score score);


}
