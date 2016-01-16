package com.example.harjot.game_test;

import android.graphics.Color;
import android.graphics.Paint;

import java.lang.Math;

/**
 * Created by Harjot on 21-Nov-15.
 */
public class CirclesParam {
    float x;
    float y;
    double vx;
    double vy;
    long rad;
    int paintColor;
    Paint drawPaint;
    String type;

    CirclesParam(float x, float y, long rad, String type) {
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.type = type;
        /*vx = (int) (Math.random() * 10);
        vy = (int) (Math.random() * 10);*/
        if (type.equals("anti")) {
            paintColor = Color.RED;
        } else {
            paintColor = Color.BLACK;
        }
        //paintColor= Color.rgb((int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200));
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    void randomizevx() {

        vx = (int) (Math.random() * 10);
    }

    void randomizevy() {

        vy = (int) (Math.random() * 10);
    }
}
