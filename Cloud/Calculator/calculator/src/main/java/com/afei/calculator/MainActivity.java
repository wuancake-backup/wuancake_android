package com.afei.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @BindView(R.id.et_result)
    public EditText result;
    @BindView(R.id.btn_c)
    public Button btnc; //清除按钮【C】
    @BindView(R.id.btn_pm)
    public Button btnPm; //正负号按钮【+/-】
    @BindView(R.id.btn_per)
    public Button btnPer; //百分号按钮【%】
    @BindView(R.id.btn_add)
    public Button btnAdd; //加号
    @BindView(R.id.btn_sub)
    public Button btnSub; //减号
    @BindView(R.id.btn_mul)
    public Button btnMul;//乘号
    @BindView(R.id.btn_div)
    public Button btnDiv;//除号
    @BindView(R.id.btn_equ)
    public Button btnEqu; //等号
    @BindView(R.id.btn_dot)
    public Button btnDot; //点
    @BindViews({R.id.btn_0,R.id.btn_1,R.id.btn_2,
    R.id.btn_3,R.id.btn_4,R.id.btn_5,
    R.id.btn_6,R.id.btn_7,R.id.btn_8,R.id.btn_9})
    public List<Button>btnList;
    private float x1,x2;
    private final int MIN_DISTANCE=150;
    @BindView(R.id.ll)
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        result.setKeyListener(null);
        result.setOnTouchListener(this);
    }

    @OnClick({R.id.btn_0,R.id.btn_1,R.id.btn_2,
            R.id.btn_3,R.id.btn_4,R.id.btn_5,
            R.id.btn_6,R.id.btn_7,R.id.btn_8,
            R.id.btn_9, R.id.btn_c,R.id.btn_pm,
            R.id.btn_per, R.id.btn_div,R.id.btn_mul,R.id.btn_add,
            R.id.btn_sub, R.id.btn_dot,R.id.btn_equ})
    public void onClick(View v) {
        String[] arrNum=new String[]{"0","1","2","3",
                    "4","5","6","7","8","9"};
        String[] arrChar=new String[]{"+","-","×","÷"};
        Button btn=(Button)v;
        int vId=v.getId();
        String btnText=btn.getText().toString();
        String resText=result.getText().toString();
        if(Arrays.binarySearch(arrNum,btnText)>=0)
            result.setText(resText+btnText);
        if(Arrays.binarySearch(arrChar,btnText)>=0 || btnText.equals(".")) {
            if(TextUtils.isEmpty(resText))      //不能为空
                return;
            //判断text最后一位是不是数字
            if(Arrays.binarySearch(arrNum,resText.substring(resText.length()-1,resText.length()))<0)
                return;
            result.setText(resText + btnText);
        }
        switch (v.getId())
        {
            case R.id.btn_c:result.setText(""); //清除
                break;
            case R.id.btn_equ:clacu();
                break;
        }
    }
    //计算
    public void clacu()
    {
        String ss=result.getText().toString();
        Symbols s=new Symbols();
        try {
            double res=s.eval(ss);
            result.setText(res+"");
        } catch (SyntaxException e) {
            Toast.makeText(this, "错误!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        String etText=result.getText().toString();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:x1=event.getX();
                break;
            case MotionEvent.ACTION_UP: x2=event.getX();
                float deltax=x2-x1;
                if(Math.abs(deltax)>MIN_DISTANCE)
                {
                    if(!TextUtils.isEmpty(etText))
                    {
                        result.setText(etText.substring(0,etText.length()-1));
                    }
                }
                break;
        }
        return false;
    }
}
