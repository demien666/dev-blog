package com.demien.services.user.domain;

import java.util.Date;

public class Token {

    private String tokenId;
    private String userId;
    private Date created;

    public Token() {
    }

    public Token(String tokenId, String userId) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.created = new Date();
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
