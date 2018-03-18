package com.app.zilla.jinrishi.BmobObj;

import cn.bmob.v3.BmobObject;

/**
 * Created by 74434 on 2018/3/18.
 */

public class UserFlavor extends BmobObject {
    private Integer sour;
    private Integer sweet;
    private Integer spicy;
    private Integer salty;
    private Integer surprise;
    private Integer homely;
    private String hometown;

    public Integer getSour() {
        return sour;
    }

    public void setSour(Integer sour) {
        this.sour = sour;
    }

    public Integer getSweet() {
        return sweet;
    }

    public void setSweet(Integer sweet) {
        this.sweet = sweet;
    }

    public Integer getSpicy() {
        return spicy;
    }

    public void setSpicy(Integer spicy) {
        this.spicy = spicy;
    }

    public Integer getSalty() {
        return salty;
    }

    public void setSalty(Integer salty) {
        this.salty = salty;
    }

    public Integer getSurprise() {
        return surprise;
    }

    public void setSurprise(Integer surprise) {
        this.surprise = surprise;
    }

    public Integer getHomely() {
        return homely;
    }

    public void setHomely(Integer homely) {
        this.homely = homely;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}
