package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Score {
    @NotBlank
    private String email;
    @NotBlank
    private int scoreId;
    private String scale;
    private String rhythm;

    public Score(@JsonProperty("email") String email
                ,@JsonProperty("scoreId") int scoreId
                ,@JsonProperty("scale") String scale
                ,@JsonProperty("rhythm") String rhythm) {
        this.email = email;
        this.scoreId = scoreId;
        this.scale = scale;
        this.rhythm = rhythm;
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
    public String getRhythm() {
        return rhythm;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setScoreId(int scoreId) {this.scoreId = scoreId;}
    public void setScale(String scale) {this.scale = scale;}
    public void setRhythm(String rhythm) {
        this.rhythm = rhythm;
    }

}
