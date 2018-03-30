package com.app.zilla.jinrishi.BmobObj;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

/**
 * Created by 74434 on 2018/2/20.
 */

public class JrsUser extends BmobUser {
    private Campus school;
    private File profile_img;
    private Integer credit;
    private Integer monthly_budget;
    private ArrayList<String> likes;//记录dish的 ObjectID
    private UserFlavor flavor_pref;


    public Campus getSchool() {
        return school;
    }

    public void setSchool(Campus school) {
        this.school = school;
    }

    public File getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(File profile_img) {
        this.profile_img = profile_img;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getMonthly_budget() {
        return monthly_budget;
    }

    public void setMonthly_budget(Integer monthly_budget) {
        this.monthly_budget = monthly_budget;
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    public UserFlavor getFlavor_pref() {
        return flavor_pref;
    }

    public void setFlavor_pref(UserFlavor flavor_pref) {
        this.flavor_pref = flavor_pref;
    }

    public String getLevel(){
        if(this.credit<300){
            return "少年不谙食堂味";
        }else if (this.credit<1000){
            return "有了二三心头好";
        }else if(this.credit<3000){
            return "阿姨看你超眼熟";
        }else{
            return "读万卷品百味";
        }
    }
}
