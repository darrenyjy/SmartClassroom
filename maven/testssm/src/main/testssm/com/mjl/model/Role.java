package com.mjl.model;

import java.util.List;

/**
 * Created by jiangdongyu on 2016/12/4.
 */
public class Role {
    private int r_id;
    private String r_name;

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "r_id=" + r_id +
                ", r_name='" + r_name + '\'' +
                '}';
    }
}
