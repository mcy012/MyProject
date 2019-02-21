package com.example.eda1707.opgg.dto;

import java.util.ArrayList;
import java.util.List;

public class MyInfoDto {

    private String nickName;
    private String tierRank;
    private String tierInfo;
    private String winRatio;
    private List<MyScoreDto> myScoreDtoList = new ArrayList<>();

    public MyInfoDto(String nickName, String tierRank, String tierInfo, String winRatio) {
        this.nickName = nickName;
        this.tierRank = tierRank;
        this.tierInfo = tierInfo;
        this.winRatio = winRatio;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTierRank() {
        return tierRank;
    }

    public void setTierRank(String tierRank) {
        this.tierRank = tierRank;
    }

    public String getTierInfo() {
        return tierInfo;
    }

    public void setTierInfo(String tierInfo) {
        this.tierInfo = tierInfo;
    }

    public String getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(String winRatio) {
        this.winRatio = winRatio;
    }

    public List<MyScoreDto> getMyScoreDtoList() {
        return myScoreDtoList;
    }

    public void setMyScoreDtoList(List<MyScoreDto> myScoreDtoList) {
        this.myScoreDtoList = myScoreDtoList;
    }

    public void addMyScore(MyScoreDto myScoreDto) {
        this.myScoreDtoList.add(myScoreDto);
    }

    @Override
    public String toString() {
        return "MyInfoDto{" +
                "nickName='" + nickName + '\'' +
                ", tierRank='" + tierRank + '\'' +
                ", tierInfo='" + tierInfo + '\'' +
                ", winRatio='" + winRatio + '\'' +
                ", myScoreDtoList=" + myScoreDtoList +
                '}';
    }


}
