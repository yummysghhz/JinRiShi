package com.app.zilla.jinrishi.BmobObj;

import cn.bmob.v3.BmobUser;

/**
 * Created by 74434 on 2018/2/20.
 */

public class JrsUser extends BmobUser {
    private Campus school; //01 ecnu
    private Integer flavorPrefNo;
    private String profile_url;
    private Integer credit;
    private Integer mealBudget;

    public Campus getSchool() {
        return school;
    }

    public void setSchool(Campus school) {
        this.school = school;
    }

    public Integer getFlavorPrefNo() {
        return flavorPrefNo;
    }

    public void setFlavorPrefNo(Integer flavorPrefNo) {
        this.flavorPrefNo = flavorPrefNo;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getMealBudget() {
        return mealBudget;
    }

    public void setMealBudget(Integer mealBudget) {
        this.mealBudget = mealBudget;
    }
}
