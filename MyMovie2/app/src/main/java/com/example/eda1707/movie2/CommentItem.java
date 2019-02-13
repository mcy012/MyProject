package com.example.eda1707.movie2;

public class CommentItem {

    String id;
    String time;
    String comment;
    int recommend;

    public CommentItem(String id, String time, String comment, int recommend) {
        this.id = id;
        this.time = time;
        this.comment = comment;
        this.recommend = recommend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

}
