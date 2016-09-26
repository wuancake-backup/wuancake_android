package JsonBean;

import JsonBean.dailyForecast.Wind;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Now {
    private NowCond cond;
    private String fl;
    private String hum;
    private String pcpn;
    private String pres;
    private String tmp;
    private String vis;
    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    public NowCond getCond() {
        return cond;
    }

    public String getFl() {
        return fl;
    }

    public String getHum() {
        return hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public String getPres() {
        return pres;
    }

    public String getTmp() {
        return tmp;
    }

    public String getVis() {
        return vis;
    }

    public void setCond(NowCond cond) {
        this.cond = cond;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }
}
