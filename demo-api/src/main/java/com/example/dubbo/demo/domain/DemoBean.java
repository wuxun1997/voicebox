package com.example.dubbo.demo.domain;

import java.io.Serializable;

public class DemoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    private String passwd;

}
