package com.doron.watchvault.network;

public class AuthModel {
    private Long uid;
    private String uname;
    private String uemail;
    private String upassword;
    private boolean flag;

    public String getUemail() {return uemail;}

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public boolean isFlag() {
        return flag;
    }

}
