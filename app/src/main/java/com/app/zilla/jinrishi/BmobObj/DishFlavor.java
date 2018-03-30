package com.app.zilla.jinrishi.BmobObj;

import cn.bmob.v3.BmobObject;

/**
 * Created by 74434 on 2018/3/18.
 */

public class DishFlavor extends BmobObject {
    private Float sour;
    private Float sweet;
    private Float spicy;
    private Float salty;
    private Float surprise;
    private Float homely;

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
}
