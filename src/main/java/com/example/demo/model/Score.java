package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.sql.Timestamp;

public class Score {
    private String email;
    private int scoreId;
    private String scale;
    private String dir;
    private int accuracy;
    private Timestamp c_time;
    public Score(@JsonProperty("email") String email
                ,@JsonProperty("scoreId") int scoreId
                ,@JsonProperty("scale") String scale
                ,String dir
                ,int accuracy
                ,Timestamp c_time) {
        this.email = email;
        this.scoreId = scoreId;
        this.scale = scale;
        this.dir = dir;
        this.accuracy = accuracy;
        this.c_time = c_time;
    }

    public String getEmail() {
        return email;
    }
    public int getScoreId() {
        return scoreId;
    }
    public String getScale() {
        return scale;
    }
    public String getDir() {
        return dir;
    }
    public int getAccuracy() {
        return accuracy;
    }
    public Timestamp getC_time() {return c_time;}
    public void setEmail(String email) {
        this.email = email;
    }
    public void setScoreId(int scoreId) {this.scoreId = scoreId;}
    public void setScale(String scale) {this.scale = scale;}
    public void setDir(String dir) {this.dir = dir;}
    public void setAccuracy(int accuracy) {this.accuracy = accuracy;}
    public void setC_time(Timestamp c_time) {this.c_time = c_time;}
}
