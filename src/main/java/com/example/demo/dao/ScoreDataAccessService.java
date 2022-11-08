package com.example.demo.dao;

import com.example.demo.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

// 악보 DB 제어
@Repository("postgres2")
public class ScoreDataAccessService implements ScoreDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScoreDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // 악보 생성 및 숙련도 측정
    @Override
    public String insertScore(Score score) throws IOException {
        //시간
        Timestamp t = new Timestamp(System.currentTimeMillis());
        score.setC_time(t);
        //파일 경로
        String path = System.getProperty("user.dir") + '\\' + score.getEmail() + '\\' + score.getScoreId();
        Path a = Paths.get(path);
        //폴더 생성 및 JPG 파일 생성
        Files.createDirectories(a);
        path = path + '\\' + t + ".JPG";
        File b = new File(path);
        score.setDir(path);

        //숙련도 구현
        score.setAccuracy(90);

        final String sql = "INSERT INTO score(email,scoreId,scale,dir,accuracy,c_time) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{score.getEmail()
                ,score.getScoreId()
                ,score.getScale()
                ,score.getDir()
                ,score.getAccuracy()
                ,score.getC_time()});

        return Integer.toString(score.getAccuracy());
    }
    // 모든 악보 출력
    @Override
    public List<Score> selectAllScore() {
        final String sql = "SELECT * FROM score";
        return jdbcTemplate.query(sql,(resultSet,i) -> {
            String email = resultSet.getString("email");
            int scoreId = resultSet.getInt("scoreId");
            String scale = resultSet.getString("scale");
            String dir = resultSet.getString("dir");
            int accuracy = resultSet.getInt("accuracy");
            Timestamp c_time = resultSet.getTimestamp("c_time");
            return new Score(email,scoreId,scale,dir,accuracy,c_time);
        } );
    }
    // 특정 회원의 모든 악보 출력
    @Override
    public List<Score> selectAllScoreByEmail(String email){
        final String sql = "SELECT * FROM score WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email} ,(resultSet, i) -> {
            int scoreId = resultSet.getInt("scoreId");
            String scale = resultSet.getString("scale");
            String dir = resultSet.getString("dir");
            int accuracy = resultSet.getInt("accuracy");
            Timestamp c_time = resultSet.getTimestamp("c_time");
            return new Score(email,scoreId,scale,dir,accuracy,c_time);
        });
    }
    // 특정 회원의 특정 악보 출력
    @Override
    public Optional<Score> selectScoreByEmailScoreId(String email, int scoreId){
        final String sql = "SELECT * FROM score WHERE email = ? AND scoreId = ?";
        Score t_score = jdbcTemplate.queryForObject(sql, new Object[]{email,scoreId}, (resultSet,i) ->{
            String scale = resultSet.getString("scale");
            String dir = resultSet.getString("dir");
            int accuracy = resultSet.getInt("accuracy");
            Timestamp c_time = resultSet.getTimestamp("c_time");
            return new Score(email,scoreId,scale,dir,accuracy,c_time);
        });
        return Optional.ofNullable(t_score);

    }
    // 특정 회원의 모든 악보 삭제
    @Override
    public int deleteAllScoreByEmail(String email){
        final String sql = "DELETE FROM score WHERE email = ?";
        jdbcTemplate.update(sql,new Object[]{email});
        return 0;
    }
    // 특정 회원의 특정 악보 삭제
    @Override
    public int deleteScoreByEmailScoreId(String email, int scoreId){
        final String sql = "DELETE FROM score WHERE email = ? AND scoreId = ?";
        jdbcTemplate.update(sql, new Object[]{email,scoreId});
        return 0;
    }
    // 악보 다운로드
    @Override
    public ResponseEntity<Resource> download(HttpServletRequest response, String email, int scoreId) throws FileNotFoundException {
        try {
            String filename = email + '_' + scoreId + ".JPG";
            String path = System.getProperty("user.dir") + '\\' + email + '\\' + scoreId + '\\' + filename;
            FileSystemResource resource = new FileSystemResource(path);
            if (!resource.exists()) {
                throw new FileNotFoundException();
            }
            HttpHeaders header = new HttpHeaders();
            Path filePath = Paths.get(path);
            header.add("Content-type",Files.probeContentType(filePath));
            return new ResponseEntity<Resource>(resource,header, HttpStatus.OK);
        }
        catch (Exception e) {
            throw new FileNotFoundException();
        }
    }
}
