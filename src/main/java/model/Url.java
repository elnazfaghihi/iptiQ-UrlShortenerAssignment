package model;

import java.util.Date;

public class Url {

    private String originalUrl;
    private Date expirationDate;

    public Url(String originalUrl, Date expirationDate) {
        this.originalUrl = originalUrl;
        this.expirationDate = expirationDate;
    }

    public Url() {
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
