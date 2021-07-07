package com.projekat.UPIB.web.dto.korisnik;

public class PasswordChangeDTO {

    private String password;
    private String currentPassword;

    public PasswordChangeDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}
