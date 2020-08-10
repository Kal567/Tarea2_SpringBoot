package com.webAvanzada.Tarea2_SpringBoot.entities;

public class UserInfo {

    private int idUserInfo;
    private String nombreUserInfo;

    public static int idIfo = 0;
    public static String usernameInfo = "";

    public UserInfo(){
        super();
    }

    public UserInfo(int idUserInfo, String nombreUserInfo){
        this.idUserInfo = idUserInfo;
        this.nombreUserInfo = nombreUserInfo;
    }

    public int getIdUserInfo() {
        return idIfo;
    }

    public void setIdUserInfo(int idUserInfo) {
        idIfo = idUserInfo;
    }

    public String getNombreUserInfo() {
        return usernameInfo;
    }

    public void setNombreUserInfo(String nombreUserInfo) {
        usernameInfo = nombreUserInfo;
    }
}
