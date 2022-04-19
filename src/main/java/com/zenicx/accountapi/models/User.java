package com.zenicx.accountapi.models;

import com.zenicx.accountapi.types.UserType;

public class User {
    private UserType userType;
    private String value;

    public User(UserType userType, String value) {
        this.userType = userType;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setValue(String value) {
        this.value = value;
    }
}