package JsonBean;

import JsonBean.dailyForecast.Wind;

/**
 * Created by Administrator on 2016/9/12.
 */
public class HourlyForecast {
    private String date;
    private String hum;
    private String pop;
    private String pres;
    private String tmp;
    private Wind wind;

    public String getDate() {
        return date;
    }

    public Wind getWind() {
        return wind;
    }

    public String getTmp() {
        return tmp;
    }

    public String getPres() {
        return pres;
    }

    public String getPop() {
        return pop;
    }

    public String getHum() {
        return hum;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
