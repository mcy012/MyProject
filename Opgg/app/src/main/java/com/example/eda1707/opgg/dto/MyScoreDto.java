package com.example.eda1707.opgg.dto;

public class MyScoreDto {
    private String gameType;
    private String gameResult;
    private String doChampion;
    private String kill;
    private String death;
    private String assist;

    public MyScoreDto(String gameType, String gameResult, String doChampion, String kill, String death, String assist) {
        this.gameType = gameType;
        this.gameResult = gameResult;
        this.doChampion = doChampion;
        this.kill = kill;
        this.death = death;
        this.assist = assist;
    }

    public String getDoChampion() {
        return doChampion;
    }

    public void setDoChampion(String doChampion) {
        this.doChampion = doChampion;
    }

    public String getKill() {
        return kill;
    }

    public void setKill(String kill) {
        this.kill = kill;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getAssist() {
        return assist;
    }

    public void setAssist(String assist) {
        this.assist = assist;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameResult() {
        return gameResult;
    }

    public void setGameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public String toString() {
        return "MyScoreDto{" +
                "gameType='" + gameType + '\'' +
                ", gameResult='" + gameResult + '\'' +
                ", doChampion='" + doChampion + '\'' +
                ", kill='" + kill + '\'' +
                ", death='" + death + '\'' +
                ", assist='" + assist + '\'' +
                '}';
    }
}
