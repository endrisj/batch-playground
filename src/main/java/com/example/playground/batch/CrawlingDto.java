package com.example.playground.batch;

import java.util.List;

public class CrawlingDto {
    private String firstCall;
    private SecondCall secondCall;
    private ThirdCall thirdCall;

    public String getFirstCall() {
        return firstCall;
    }

    public void setFirstCall(String firstCall) {
        this.firstCall = firstCall;
    }

    public SecondCall getSecondCall() {
        return secondCall;
    }

    public void setSecondCall(SecondCall secondCall) {
        this.secondCall = secondCall;
    }

    public ThirdCall getThirdCall() {
        return thirdCall;
    }

    public void setThirdCall(ThirdCall thirdCall) {
        this.thirdCall = thirdCall;
    }

    @Override
    public String toString() {
        return "CrawlingDto{" + "firstCall=" + firstCall + ", secondCall=" + secondCall + ", thirdCall=" + thirdCall + '}';
    }
    
    public static class SecondCall {
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
            return "SecondCall{" + "url=" + url + ", famousPeople=" + famousPeople + '}';
        }
    }
    
    public static class ThirdCall {
        private String url;
        private String repositoryKey;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRepositoryKey() {
            return repositoryKey;
        }

        public void setRepositoryKey(String repositoryKey) {
            this.repositoryKey = repositoryKey;
        }

        @Override
        public String toString() {
            return "ThirdCall{" + "url=" + url + ", repositoryKey=" + repositoryKey + '}';
        }
    }
}
