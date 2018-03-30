package com.app.zilla.jinrishi.BmobObj;

import cn.bmob.v3.BmobObject;

/**
 * Created by 74434 on 2018/3/18.
 */

public class UserFlavor extends BmobObject {
    private Float sour;
    private Float sweet;
    private Float spicy;
    private Float salty;
    private Float surprise;
    private Float homely;
    private String hometown;

    public Float getSour() {
        return sour;
    }

    public void setSour(Float sour) {
        this.sour = sour;
    }

    public Float getSweet() {
        return sweet;
    }

    public void setSweet(Float sweet) {
        this.sweet = sweet;
    }

    public Float getSpicy() {
        return spicy;
    }

    public void setSpicy(Float spicy) {
        this.spicy = spicy;
    }

    public Float getSalty() {
        return salty;
    }

    public void setSalty(Float salty) {
        this.salty = salty;
    }

    public Float getSurprise() {
        return surprise;
    }

    public void setSurprise(Float surprise) {
        this.surprise = surprise;
    }

    public Float getHomely() {
        return homely;
    }

    public void setHomely(Float homely) {
        this.homely = homely;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    @Override
    public String toString() {
        return "酸、甜、辣、咸、猎奇、家常："+sour+"、"+sweet+"、"+spicy+"、"+salty+"、"+surprise+"、"+homely;
    }
}
