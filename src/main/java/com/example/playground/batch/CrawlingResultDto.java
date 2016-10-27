package com.example.playground.batch;

import java.util.List;

public class CrawlingResultDto {
    
    private String url;
    private List<String> famousPeople;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getFamousPeople() {
        return famousPeople;
    }

    public void setFamousPeople(List<String> famousPeople) {
        this.famousPeople = famousPeople;
    }

    @Override
    public String toString() {
        return "CrawlingResultDto{" + "url=" + url + ", famousPeople=" + famousPeople + '}';
    }
}
