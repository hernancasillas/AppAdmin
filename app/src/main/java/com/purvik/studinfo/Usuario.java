package com.purvik.studinfo;

public class Usuario {

    private int id_user;
    private String user_Name;
    private String user_Username;
    private String userEmail;
    private String userPassword;
    private String userPhone;
    private int userType;


    public Usuario(){}

    public Usuario(int id_user , String user_Name, String user_Username, String userEmail, String userPassword, String userPhone)
    {
        this.id_user = id_user;
        this.user_Name = user_Name;
        this.user_Username = user_Username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
    }

    public Usuario(String user_Name, String user_Username, String userEmail, String userPassword, String userPhone, int UserType)
    {
        this.user_Name = user_Name;
        this.user_Username = user_Username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userType = UserType;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Username() {
        return user_Username;
    }

    public void setUser_Username(String user_Username) {
        this.user_Username = user_Username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
