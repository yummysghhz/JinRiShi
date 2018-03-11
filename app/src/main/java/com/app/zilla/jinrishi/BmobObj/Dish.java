package com.app.zilla.jinrishi.BmobObj;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by 74434 on 2018/3/9.
 */

public class Dish extends BmobObject {
    private String dish_name;
    private String type;
    private Flavor flavor_description;
    private float price;
    private Window windowIn;
    private File dish_image;
    private String dish_description;
    private int times;
    private int like_times;

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Flavor getFlavor_description() {
        return flavor_description;
    }

    public void setFlavor_description(Flavor flavor_description) {
        this.flavor_description = flavor_description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Window getWindowIn() {
        return windowIn;
    }

    public void setWindowIn(Window windowIn) {
        this.windowIn = windowIn;
    }

    public File getDish_image() {
        return dish_image;
    }

    public void setDish_image(File dish_image) {
        this.dish_image = dish_image;
    }

    public String getDish_description() {
        return dish_description;
    }

    public void setDish_description(String dish_description) {
        this.dish_description = dish_description;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getLike_times() {
        return like_times;
    }

    public void setLike_times(int like_times) {
        this.like_times = like_times;
    }
}
