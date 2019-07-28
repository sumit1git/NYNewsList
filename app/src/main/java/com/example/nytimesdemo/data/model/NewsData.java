package com.example.nytimesdemo.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsData implements Parcelable {
    public NewsData(String title, String subHeading, String publishedDate,String imgUrl) {
        this.title = title;
        this.subHeading = subHeading;
        this.publishedDate = publishedDate;
        this.imgUrl =imgUrl;
    }

    private String title;
    private String subHeading;
    private String publishedDate;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
