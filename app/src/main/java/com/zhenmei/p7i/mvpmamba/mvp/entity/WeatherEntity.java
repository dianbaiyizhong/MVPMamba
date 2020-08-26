package com.zhenmei.p7i.mvpmamba.mvp.entity;

public class WeatherEntity {
    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "wendu='" + wendu + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    private String wendu;
    private String ganmao;
    private String city;
}
