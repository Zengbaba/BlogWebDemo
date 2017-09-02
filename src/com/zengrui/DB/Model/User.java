package com.zengrui.DB.Model;
import com.zengrui.DB.Inerface.Model;

/**
 * Created by Zeng Rui on 2017/8/21.
 */
public class User implements Model {
    private String name;
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}
}
