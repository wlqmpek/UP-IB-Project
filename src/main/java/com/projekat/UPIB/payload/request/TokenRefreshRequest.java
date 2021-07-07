package com.projekat.UPIB.payload.request;

import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
