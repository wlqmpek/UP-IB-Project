package com.projekat.UPIB.models;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class KorisnikovTokenStatus {

    private String accessToken;
    private Long expiresIn;

    public KorisnikovTokenStatus() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public KorisnikovTokenStatus(String accessToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
