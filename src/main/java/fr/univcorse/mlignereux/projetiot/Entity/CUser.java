package fr.univcorse.mlignereux.projetiot.entity;

/**
 * Created by asus on 29/09/2015.
 */
public class CUser {


    private String email;

    private String pwd;

    public enum Status {ATHLETE,COACH}

    private Status status;

    public CUser(){}

    public CUser(String pEmail, String pPwd, Status pStatus){
        this.email = pEmail;
        this.pwd = pPwd;
        this.status = pStatus;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return pwd;
    }

    public Status getStatus(){
         return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String pwd) {
        this.pwd = pwd;
    }
}
