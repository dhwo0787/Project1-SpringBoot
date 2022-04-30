package com.example.demo.dao;

import com.example.demo.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres2")
public class ScoreDataAccessService implements ScoreDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScoreDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int insertScore(Score score) {
        final String sql = "INSERT INTO score VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{score.getEmail(),score.getScoreId(),score.getScale(),score.getRhythm()});
        return 0;
    }
    @Override
    public List<Score> selectAllScoreByEmail(String email){
        final String sql = "SELECT * FROM score WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email} ,(resultSet, i) -> {
            int scoreId = resultSet.getInt("scoreId");
            String scale = resultSet.getString("scale");
            String rhythm = resultSet.getString("rhythm");
            return new Score(email,scoreId,scale,rhythm);
        });
    }
    @Override
    public Optional<Score> selectScoreByEmailScoreId(Score score){
        final String sql = "SELECT * FROM score WHERE email = ? AND scoreId = ?";
        Score t_score = jdbcTemplate.queryForObject(sql, new Object[]{score.getEmail(),score.getScoreId()}, (resultSet,i) ->{
            String email = resultSet.getString("email");
            int scoreId = resultSet.getInt("scoreId");
            String scale = resultSet.getString("scale");
            String rhythm = resultSet.getString("rhythm");
            return new Score(email,scoreId,scale,rhythm);
        });
        return Optional.ofNullable(t_score);

    }
    @Override
    public int deleteAllScoreByEmail(String email){
        final String sql = "DELETE FROM score WHERE email = ?";
        jdbcTemplate.update(sql,new Object[]{email});
        return 0;
    }
    @Override
    public int deleteScoreByEmailScoreId(Score score){
        final String sql = "DELETE FROM score WHERE email = ? AND scoreId = ?";
        jdbcTemplate.update(sql, new Object[]{score.getEmail(),score.getScoreId()});
        return 0;
    }
    @Override
    public int updateScoreByEmailScoreId(Score score){
        return 0;
    }
}
