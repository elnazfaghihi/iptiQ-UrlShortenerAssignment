package model;

public class UrlDTO {
    private String url;
    private String seoKeyword;
    private Integer urlValiditySeconds;

    public UrlDTO(String url, String seoKeyword, Integer urlValiditySeconds) {
        this.url = url;
        this.seoKeyword = seoKeyword;
        this.urlValiditySeconds = urlValiditySeconds;
    }

    public UrlDTO(String url, Integer urlValiditySeconds) {
        this.url = url;
        this.urlValiditySeconds = urlValiditySeconds;
    }

    public UrlDTO() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSeoKeyword() {
        return seoKeyword;
    }

    public void setSeoKeyword(String seoKeyword) {
        this.seoKeyword = seoKeyword;
    }

    public Integer getUrlValiditySeconds() {
        return urlValiditySeconds;
    }

    public void setUrlValiditySeconds(Integer urlValiditySeconds) {
        this.urlValiditySeconds = urlValiditySeconds;
    }
}
