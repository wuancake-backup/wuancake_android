package com.example.administrator.niweather;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/9/16.
 */
public class MyTempLine extends View {
    /**
     * 温度文本
     */
    private String mTemperature;
    /**
     * 文本大小、画笔、颜色
     */
    private int mTextSize ;
    private Paint mPaint ;
    private Rect mBound;
    private int textColor ;
    /**
     * 线条颜色、大小、画笔、透明度
     */
    private int lineColor ;
    private int lineSize = 4;
    /**
     * 圆点颜色大小、画笔
     */
    private int pointColor ;
    private int pointRadius = 10;
    /**
     * 背景颜色、画笔
     */
    private int mBackgroundColor;
    /**
     * 温度、坐标数组
     */
    private float[] TempX;
    private float[] TempY;
    private int[] T = new int[]{0};
    public MyTempLine(Context context) {
        super(context);
    }
    public MyTempLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,R.styleable.TempLine,0,0);
        int n = typedArray.getIndexCount();
        for (int i= 0 ; i < n;i++){
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.TempLine_temperature:
                    mTemperature = typedArray.getString(attr);
                    break;
                case R.styleable.TempLine_textSize:
                    mTextSize = typedArray.getDimensionPixelSize(attr,15);
                    break;
                case R.styleable.TempLine_textColor:
                    textColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TempLine_lineColor:
                    lineColor = typedArray.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.TempLine_lineSize:
                    lineSize = typedArray.getInt(attr,4);
                    break;
                case R.styleable.TempLine_pointColor:
                    pointColor = typedArray.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.TempLine_pointRadius:
                    pointRadius = typedArray.getInt(attr,10);
                    break;
                case R.styleable.TempLine_mBackgroundColor:
                    mBackgroundColor = typedArray.getColor(attr,Color.WHITE);
            }
        }
        typedArray.recycle();
        /**
         * 获得绘制文本的大小
         */
        mPaint = new Paint();
        mBound = new Rect();
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds("00°",0,"00°".length(), mBound);
    }
    public MyTempLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,R.styleable.TempLine,defStyleAttr,0);
        int n = typedArray.getIndexCount();
        for (int i= 0 ; i < n;i++){
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.TempLine_temperature:
                    mTemperature = typedArray.getString(attr);
                    break;
                case R.styleable.TempLine_textSize:
                    mTextSize = typedArray.getDimensionPixelSize(attr,15);
                    break;
                case R.styleable.TempLine_textColor:
                    textColor = typedArray.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.TempLine_lineColor:
                    lineColor = typedArray.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.TempLine_lineSize:
                    lineSize = typedArray.getInt(attr,4);
                    break;
                case R.styleable.TempLine_pointColor:
                    pointColor = typedArray.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.TempLine_pointRadius:
                    pointRadius = typedArray.getInt(attr,10);
                    break;
                case R.styleable.TempLine_mBackgroundColor:
                    mBackgroundColor = typedArray.getColor(attr,Color.WHITE);
            }
        }
        typedArray.recycle();
        /**
         * 获得绘制文本的大小
         */
        mPaint = new Paint();
        mBound = new Rect();
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mTemperature,0,mTemperature.length(), mBound);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取各温度点坐标
        float maxT,minT;
        TempX = new float[T.length];
        TempY = new float[T.length];
        float withUnit = getMeasuredWidth()/T.length;
        //x轴坐标
        for (int i = 0;i<T.length;i++){
            TempX[i] = withUnit*(i+1);
        }
        for (int i=0;i<T.length;i++){
            TempX[i] = TempX[i]-withUnit/2;
        }
        //确定y轴坐标
        maxT = minT = T[0];
        for (int i = 0;i<T.length;i++){
            if (T[i]>maxT){
                maxT = T[i];
            }
            if (T[i]<minT){
                minT = T[i];
            }
        }
        float yRange = maxT - minT;
        float heightUnit = (getMeasuredHeight()-mBound.height()*2-pointRadius)/yRange;
        for (int i = 0;i <T.length;i++){
            TempY[i] = (maxT-T[i])*heightUnit*5/10+mBound.height()*2;
        }
        //绘制背景
        mPaint.setColor(mBackgroundColor);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(), mPaint);
        //绘制温度文本
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(textColor);
        mPaint.setTextSize(mTextSize);
        for (int i = 0;i<T.length;i++){
            canvas.drawText(""+T[i]+"°", TempX[i]-mBound.width()/2, TempY[i]-mBound.height(), mPaint);
        }
        //绘制圆点
        mPaint.setColor(pointColor);
        RectF rectF = new RectF();
        for (int i = 0;i<T.length;i++){
            rectF.set(TempX[i]-pointRadius, TempY[i]-pointRadius, TempX[i]+pointRadius, TempY[i]+pointRadius);
            canvas.drawOval(rectF, mPaint);
        }
        //绘制线条
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(lineSize);
        mPaint.setColor(lineColor);
        Path path = new Path();
        path.moveTo(TempX[0], TempY[0]);
        for (int i =1;i<T.length;i++ ){
            path.lineTo(TempX[i], TempY[i]);
        }
        canvas.drawPath(path, mPaint);
    }
    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        invalidate();
    }

    public void settPaint(Paint tPaint) {
        this.mPaint = tPaint;
        invalidate();
    }

    public void settBound(Rect tBound) {
        this.mBound = tBound;
        invalidate();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidate();
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        invalidate();
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
        invalidate();
    }

    public void setlPaint(Paint lPaint) {
        this.mPaint = lPaint;
        invalidate();
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
        invalidate();
    }

    public void setPointRadius(int pointRadius) {
        this.pointRadius = pointRadius;
        invalidate();
    }

    public void setmBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
        invalidate();
    }

    public void setT(int[] t) {
        T = t;
        invalidate();
    }
}
