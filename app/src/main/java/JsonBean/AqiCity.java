package JsonBean;

/**
 * Created by Administrator on 2016/9/13.
 */
public class AqiCity {
    private String aqi;
    private String pm10;
    private String pm25;
    private String qlty;

    public String getQlty() {
        return qlty;
    }

    public String getPm25() {
        return pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }
}
