package hhu.propra.bati5a.domain.service;

public class Link {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String text;
    private String  url;
}
