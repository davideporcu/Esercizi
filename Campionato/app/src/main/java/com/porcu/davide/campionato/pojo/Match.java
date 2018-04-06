package com.porcu.davide.campionato.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("team1")
    @Expose
    private Team team1;
    @SerializedName("team2")
    @Expose
    private Team team2;
    @SerializedName("score1")
    @Expose
    private Integer score1;
    @SerializedName("score2")
    @Expose
    private Integer score2;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

}
