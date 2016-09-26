package JsonBean;

import JsonBean.suggestion.Comf;
import JsonBean.suggestion.Cw;
import JsonBean.suggestion.Drsg;
import JsonBean.suggestion.Flu;
import JsonBean.suggestion.Sport;
import JsonBean.suggestion.Trav;
import JsonBean.suggestion.Uv;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Suggestion {
    private Comf comf;
    private Cw cw;
    private Drsg drsg;
    private Flu flu;
    private Sport sport;
    private Trav trav;
    private Uv uv;

    public Comf getComf() {
        return comf;
    }

    public Cw getCw() {
        return cw;
    }

    public Drsg getDrsg() {
        return drsg;
    }

    public Flu getFlu() {
        return flu;
    }

    public Sport getSport() {
        return sport;
    }

    public Trav getTrav() {
        return trav;
    }

    public Uv getUv() {
        return uv;
    }

    public void setComf(Comf comf) {
        this.comf = comf;
    }

    public void setUv(Uv uv) {
        this.uv = uv;
    }

    public void setTrav(Trav trav) {
        this.trav = trav;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public void setFlu(Flu flu) {
        this.flu = flu;
    }

    public void setDrsg(Drsg drsg) {
        this.drsg = drsg;
    }

    public void setCw(Cw cw) {
        this.cw = cw;
    }
}
