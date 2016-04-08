package com.simple.wuan_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private int POS = 0;
    private int STYLE = 0;
    private boolean getResult = false;
    private boolean havePoint = false;
    @Bind(R.id.C)
    RelativeLayout C;
    @Bind(R.id.seven)
    RelativeLayout seven;
    @Bind(R.id.four)
    RelativeLayout four;
    @Bind(R.id.one)
    RelativeLayout one;
    @Bind(R.id.baseview)
    LinearLayout baseview;
    @Bind(R.id.chu)
    RelativeLayout chu;
    @Bind(R.id.eight)
    RelativeLayout eight;
    @Bind(R.id.five)
    RelativeLayout five;
    @Bind(R.id.two)
    RelativeLayout two;
    @Bind(R.id.cheng)
    RelativeLayout cheng;
    @Bind(R.id.nine)
    RelativeLayout nine;
    @Bind(R.id.six)
    RelativeLayout six;
    @Bind(R.id.three)
    RelativeLayout three;
    @Bind(R.id.zero)
    RelativeLayout zero;
    @Bind(R.id.point)
    RelativeLayout point;
    @Bind(R.id.del)
    RelativeLayout del;
    @Bind(R.id.jia)
    RelativeLayout jia;
    @Bind(R.id.jian)
    RelativeLayout jian;
    @Bind(R.id.dengyu)
    RelativeLayout dengyu;
    @Bind(R.id.rightview)
    LinearLayout rightview;
    @Bind(R.id.first)
    TextView first;
    @Bind(R.id.second)
    TextView second;
    @Bind(R.id.result)
    TextView result;
    @Bind(R.id.lable)
    TextView lable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void setNumber(int number) {
        if (getResult) {
            C();
            getResult = false;
        }
        if (first.getText().toString().isEmpty() && number == 0) {
        } else {
            if (POS == 0) {
                first.setText(first.getText().toString() + number + "");
            } else {
                second.setText(second.getText().toString() + number + "");
            }
        }
    }

    private void getResult() {
        switch (STYLE) {
            case 1:
                result.setText(Float.valueOf(first.getText().toString()) + Float.valueOf(second.getText().toString()) + "");
                getResult = true;
                break;
            case 2:
                result.setText(Float.valueOf(first.getText().toString()) - Float.valueOf(second.getText().toString()) + "");
                getResult = true;
                break;
            case 3:
                result.setText(Float.valueOf(first.getText().toString()) * Float.valueOf(second.getText().toString()) + "");
                getResult = true;
                break;
            case 4:
                result.setText(Float.valueOf(first.getText().toString()) / Float.valueOf(second.getText().toString()) + "");
                getResult = true;
                break;
        }
    }

    @OnClick(R.id.zero)
    void zero() {
        setNumber(0);
    }

    @OnClick(R.id.one)
    void one() {
        setNumber(1);
    }

    @OnClick(R.id.two)
    void two() {
        setNumber(2);
    }

    @OnClick(R.id.three)
    void three() {
        setNumber(3);
    }

    @OnClick(R.id.four)
    void four() {
        setNumber(4);
    }

    @OnClick(R.id.five)
    void five() {
        setNumber(5);
    }

    @OnClick(R.id.six)
    void six() {
        setNumber(6);
    }

    @OnClick(R.id.seven)
    void seven() {
        setNumber(7);
    }

    @OnClick(R.id.eight)
    void eight() {
        setNumber(8);
    }

    @OnClick(R.id.nine)
    void nine() {
        setNumber(9);
    }

    @OnClick(R.id.jia)
    void jia() {
        if (getResult) {
            C();
            getResult = false;
        }
        if (!first.getText().toString().isEmpty()) {
            havePoint = false;
            POS = 1;
            STYLE = 1;
            lable.setText("+");
        }
    }

    @OnClick(R.id.jian)
    void jian() {
        if (getResult) {
            C();
            getResult = false;
        }
        if (!first.getText().toString().isEmpty()) {
            havePoint = false;
            POS = 1;
            STYLE = 2;
            lable.setText("-");
        }
    }

    @OnClick(R.id.cheng)
    void cheng() {
        if (getResult) {
            C();
            getResult = false;
        }
        if (!first.getText().toString().isEmpty()) {
            havePoint = false;
            POS = 1;
            STYLE = 3;
            lable.setText("x");
        }
    }

    @OnClick(R.id.chu)
    void chu() {
        if (getResult) {
            C();
            getResult = false;
        }
        if (!first.getText().toString().isEmpty()) {
            havePoint = false;
            POS = 1;
            STYLE = 4;
            lable.setText("÷");
        }
    }

    @OnClick(R.id.point)
    void point() {
        if (!havePoint) {
            if (first.getText().toString().isEmpty()) {
            } else {
                havePoint = true;
                if (POS == 0) {
                    first.setText(first.getText().toString() + ".");
                } else {
                    second.setText(second.getText().toString() + ".");
                }
            }
        }
    }

    @OnClick(R.id.dengyu)
    void dengyu() {
        getResult();
    }

    @OnClick(R.id.C)
    void C() {
        POS = 0;
        STYLE = 0;
        havePoint = false;
        first.setText("");
        second.setText("");
        result.setText("");
        lable.setText("");
    }

    @OnClick(R.id.del)
    void del() {
        if (!getResult) {
            if (POS == 0) {
                String result = "";
                String[] strings = first.getText().toString().split("");
                for (int i = 1; i < strings.length - 1; i++) {
                    result += strings[i];
                }
                first.setText(result);
            } else {
                String result = "";
                String[] strings = second.getText().toString().split("");
                if (strings.length == 1) {
                    lable.setText("");
                    POS = 0;
                    STYLE = 0;
                } else {
                    for (int i = 1; i < strings.length - 1; i++) {
                        result += strings[i];
                    }
                    second.setText(result);
                }
            }
        } else {
            C();
        }
    }

    private void initView() {
        ViewTreeObserver vto = baseview.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                baseview.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                ViewGroup.LayoutParams params = rightview.getLayoutParams();
                params.width = baseview.getWidth();
                rightview.setLayoutParams(params);
            }
        });
    }
}
