package com.doron.watchvault.network.models;

public class LoginResponseModel {
    private boolean flag;
    private Long id;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
