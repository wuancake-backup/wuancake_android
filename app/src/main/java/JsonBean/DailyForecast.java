package JsonBean;

import JsonBean.dailyForecast.Astro;
import JsonBean.dailyForecast.Cond;
import JsonBean.dailyForecast.Tmp;
import JsonBean.dailyForecast.Wind;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DailyForecast {
    private Astro astro;
    private Cond cond;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    private Tmp tmp;
    private String vis;
    private Wind wind;

    public Astro getAstro() {
        return astro;
    }

    public Cond getCond() {
        return cond;
    }

    public String getDate() {
        return date;
    }

    public String getHum() {
        return hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public String getPop() {
        return pop;
    }

    public String getPres() {
        return pres;
    }

    public String getVis() {
        return vis;
    }

    public Tmp getTmp() {
        return tmp;
    }

    public Wind getWind() {
        return wind;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public void setTmp(Tmp tmp) {
        this.tmp = tmp;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
    }
}
