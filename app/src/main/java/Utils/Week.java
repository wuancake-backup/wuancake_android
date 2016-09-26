package Utils;

/**
 * Created by Administrator on 2016/9/17.
 */
public class Week {
    private  int weekCode;
    private String week;
    public int getWeekCode(String week){
        if (week.equals("周一")){
            weekCode = 0;
        }else if (week.equals("周二")){
            weekCode = 1;
        }else if (week.equals("周三")){
            weekCode = 2;
        }else if (week.equals("周四")){
            weekCode = 3;
        }else if (week.equals("周五")){
            weekCode = 4;
        }else if (week.equals("周六")){
            weekCode = 5;
        }else if (week.equals("周日")){
            weekCode = 6;
        }
        return weekCode;
    }
    public String setWeek(int weekCode){
        if (weekCode > 6){
            weekCode = weekCode-7;
        }
        if (weekCode == 0){
            week = "周一";
        }else if (weekCode == 1){
            week = "周二";
        }else if (weekCode == 2){
            week = "周三";
        }else if (weekCode == 3){
            week = "周四";
        }else if (weekCode == 4){
            week = "周五";
        }else if (weekCode == 5){
            week = "周六";
        }else if (weekCode == 6){
            week = "周日";
        }
        return week;
    }
}
