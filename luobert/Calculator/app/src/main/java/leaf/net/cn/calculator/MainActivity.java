package leaf.net.cn.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText editText;
    int[] numArr = new int[]{R.id.btn_num_zero,R.id.btn_num_one,R.id.btn_num_two,R.id.btn_num_three,R.id.btn_num_four,R.id.btn_num_fives,R.id.btn_num_six,R.id.btn_num_seven,R.id.btn_num_eight,R.id.btn_num_nine,R.id.btn_tag_float};
    int[] tagArr = new int[]{R.id.btn_clear,R.id.btn_division,R.id.btn_del,R.id.btn_multiplication,R.id.btn_add,R.id.btn_less,R.id.btn_equal};
    private String firstNum = "",operator = "",lastNum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数字按键控件
        for (int i = 0; i < numArr.length; i++) {
            Button btn = (Button)findViewById(numArr[i]);
            //设置click 事件
            btn.setOnClickListener(this);
        }
        //初始化数字操作控件
        for (int i = 0; i < tagArr.length; i++) {
            Button btn = (Button)findViewById(tagArr[i]);
            //设置click 事件
            btn.setOnClickListener(this);
        }
        editText = (EditText)findViewById(R.id.et_showView);
    }

    @Override
    public void onClick(View view) {
        int nodeId = view.getId();
        String text = ((Button)view).getText().toString();
        System.out.println("on click "+nodeId + "--"+text);
        if(Arrays.toString(numArr).contains(String.valueOf(nodeId))){//数字按钮
            addToNum(text);
        }else if(Arrays.toString(tagArr).contains(String.valueOf(nodeId))){//操作按钮
            operator(text);
        }
        show();
    }

    /**
     * 数字追加
     * 追加前是否有点击操作符 来取需要追加的数字
     * @param append
     */
    private void addToNum(String append){
        String operNum = "".equals(operator.trim())?firstNum.trim():lastNum.trim();
        int numLen = operNum.length();
        if(operNum.contains(".") && ".".equals(append)) {
        }else if(operNum.length()>=10){
        }else if(numLen == 0 && ".".equals(append))operNum = "0.";
        else if(numLen == 1 && ("0".equals(operNum) || "-".equals(operNum))) {
            if ("0".equals(operNum) && ".".equals(append)) operNum += append;
            else if("-".equals(operNum) && ".".equals(append))operNum += ("0"+append);
            else if("-".equals(operNum))operNum += append;
            else operNum = append;
        }else{
            operNum += append;
        }
        if("".equals(operator.trim()))firstNum = operNum;
        else lastNum = operNum;
    }

    /**
     * 清空显示
     */
    private void clearEditView(){
        firstNum = "";
        operator = "";
        lastNum = "";
    }

    /**
     * 删除一个字符
     */
    private void delChar(){
        if("".equals(operator.trim())){
            firstNum = firstNum.length()>0?firstNum.substring(0,firstNum.length()-1):firstNum;
        }else{
            lastNum = lastNum.length()>0?lastNum.substring(0,lastNum.length()-1):lastNum;
        }
    }

    /**
     * 计算数值
     */
    private void calculate(String cha){

        if(!"".equals(lastNum)){
            BigDecimal first = new BigDecimal(firstNum);
            BigDecimal end = new BigDecimal(lastNum);
            BigDecimal result = BigDecimal.valueOf(0.0);
            if("+".equals(operator))result = first.add(end);
            else if("-".equals(operator))result = first.subtract(end);
            else if("×".equals(operator))result = first.multiply(end);
            else if("÷".equals(operator)){
                String replace = firstNum.replaceAll("\\.","").replaceAll("0","");
                if(!"".equals(replace))
                    result =first.divide(end,5,BigDecimal.ROUND_HALF_DOWN);
            }
            if("=".equals(cha)){
                clearEditView();
            }else{
                operator = cha;
                lastNum = "";
            }
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            String value = String.valueOf(result);
            value = value.indexOf("E")>-1?decimalFormat.format(result):value;
            String floatValue = value.substring(value.indexOf(".")+1);
            floatValue = floatValue.replaceAll("0","");
            if("".equals(floatValue.trim()))value = value.substring(0,value.indexOf("."));
            firstNum = value;
        }else{
            if("".equals(firstNum) && "-".equals(cha))firstNum+=cha;
            else operator = "";
        }
    }

    /**
     * 点击操作
     * @param oper 操作符
     */
    private void operator(String oper){
        oper = oper.trim().toUpperCase();
        if("DEL".equals(oper))delChar();
        else if("C".equals(oper))clearEditView();
        else if("=".equals(oper))calculate(oper);
        else{
            if(!"".equals(firstNum) && ("".equals(operator) || "".equals(lastNum)))operator = oper;
            else calculate(oper);
        }
    }

    /**
     * 显示数字以及操作符
     */
    private void show(){
        String show = firstNum + "\n"+ operator + "\n"+lastNum;
        editText.setText(show);
    }
}
