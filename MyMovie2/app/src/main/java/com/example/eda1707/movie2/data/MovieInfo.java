package com.example.eda1707.movie2.data;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieInfo implements Parcelable {

    private int id;
    private String title;
    private String title_eng;
    private String data;
    private float user_rating;
    private float audience_rating;
    private float reviewer_rating;
    private float reservation_rate;
    private int reservation_grade;
    private int grade;
    private String thumb;
    private String image;

    public MovieInfo() {
    }

    public MovieInfo(Parcel in) {
        Object[] data = new Object[12];
        this.id = (int) data[0];
        this.title = (String) data[1];
        this.title_eng = (String) data[2];
        this.data = (String) data[3];
        this.user_rating = (float) data[4];
        this.audience_rating = (float) data[5];
        this.reviewer_rating = (float) data[6];
        this.reservation_rate = (float) data[7];
        this.reservation_grade = (int) data[8];
        this.grade = (int) data[9];
        this.thumb = (String) data[10];
        this.image = (String) data[11];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_eng() {
        return title_eng;
    }

    public void setTitle_eng(String title_eng) {
        this.title_eng = title_eng;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(float user_rating) {
        this.user_rating = user_rating;
    }

    public float getAudience_rating() {
        return audience_rating;
    }

    public void setAudience_rating(float audience_rating) {
        this.audience_rating = audience_rating;
    }

    public float getReviewer_rating() {
        return reviewer_rating;
    }

    public void setReviewer_rating(float reviewer_rating) {
        this.reviewer_rating = reviewer_rating;
    }

    public float getReservation_rate() {
        return reservation_rate;
    }

    public void setReservation_rate(float reservation_rate) {
        this.reservation_rate = reservation_rate;
    }

    public int getReservation_grade() {
        return reservation_grade;
    }

    public void setReservation_grade(int reservation_grade) {
        this.reservation_grade = reservation_grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", title_eng='" + title_eng + '\'' +
                ", data='" + data + '\'' +
                ", user_rating=" + user_rating +
                ", audience_rating=" + audience_rating +
                ", reviewer_rating=" + reviewer_rating +
                ", reservation_rate=" + reservation_rate +
                ", reservation_grade=" + reservation_grade +
                ", grade=" + grade +
                ", thumb='" + thumb + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(new Object[] {
                this.id,
                this.title,
                this.title_eng,
                this.data,
                this.user_rating,
                this.audience_rating,
                this.reviewer_rating,
                this.reservation_rate,
                this.reservation_grade,
                this.grade,
                this.thumb,
                this.image
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };
}
