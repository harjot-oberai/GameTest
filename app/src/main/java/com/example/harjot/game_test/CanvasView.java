package com.example.harjot.game_test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Harjot on 20-Nov-15.
 */
public class CanvasView extends View {
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;
    static public List<CirclesParam> circles;
    // private long circleRadius[]=new long[10000];
    int i = 0;
    int width = 0;
    int height = 0;
    final double g=3;
    CirclesParam main;
    /*CirclesParam c1;
    CirclesParam c2;*/
    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        setFocusable(true);
        setFocusableInTouchMode(true);
        circles = new ArrayList<CirclesParam>();
        main = new CirclesParam(500, 500, 20, "normal");
        main.vx = 3;
        main.vy = 0;
        main.drawPaint.setColor(Color.BLUE);
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new Runnable() {
            public void run() {
                postInvalidate();
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
        /*c1=new CirclesParam(500,700,40,"anti");
        c2=new CirclesParam(500,300,40,"anti");*/
    }

    double j;
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(main.x, main.y, 20, main.drawPaint);
        main.vy/=1.04;
        main.vx/=1.04;
        /*canvas.drawCircle(c1.x,c1.y,40,c1.drawPaint);
        canvas.drawCircle(c2.x,c2.y,40,c2.drawPaint);*/
       if (main.x >= (canvas.getWidth())&&main.vx>0) {
            main.vx *= -0.7;
           // main.vy *= -1;
        } else if (main.y >= (canvas.getHeight())&&main.vy>0) {
            main.vy *= -0.7;
           // main.vx *= -1;
        } else if (main.x <= 0&&main.vx<0) {
            main.vx *= -0.7;
           // main.vy *= -1;
        } else if (main.y <= 0&&main.vy<0) {
            main.vy *= -0.7;
           // main.vx *= -1;
        }
        main.x += main.vx;
        main.y += main.vy;
        if(main.y<canvas.getHeight()){
            main.vy+=g;
        }
        if(circles.size()>0){
            for (CirclesParam p : circles) {
                canvas.drawCircle(p.x, p.y, p.rad, p.drawPaint);
                canvas.drawLine(main.x,main.y,p.x,p.y,p.drawPaint);

                if (p.type.equals("normal")) {
                    //if ((Math.abs(p.x - main.x) > 20) && (Math.abs(p.y - main.y) > 20)) {
                    j = Math.sqrt(((p.x - main.x) * (p.x - main.x)) + ((p.y - main.y) * (p.y - main.y)));
                    main.vy += (((p.y - main.y) * j) / 100000);
                    main.vx += (((p.x - main.x) * j) / 100000);
                    /*if(j!=0) {
                        main.vy += (((p.y - main.y) / (j * j * j)) * 10000);
                        main.vx += (((p.x - main.x) / (j * j * j)) * 10000);
                    }*/
                    //}
               /* else {
                    main.vx=0;
                    main.vy=0;
                }*/
                }
                else {
                    /// if ((Math.abs(p.x - main.x) > 10) && (Math.abs(p.y - main.y) > 10)) {
                    j = Math.sqrt(((p.x - main.x) * (p.x - main.x)) + ((p.y - main.y) * (p.y - main.y)));
                    if(j!=0){
                        main.vy -= (((p.y - main.y) / (j * j * j)) * 10000);
                        main.vx -= (((p.x - main.x) / (j * j * j)) * 10000);
                    }

                    //}
               /* else{
                    main.vx=0;
                    main.vy=0;
                }*/
                }
            }
        }
    }

    long touchedTime = 0;
    long removedTime = 0;
    long cR = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        float prevX=touchX;
        float prevY=touchY;
        int flag=0;
        int flag1=0;
        //Point p=new Point(Math.round(touchX), Math.round(touchY));
        //CirclesParam cp;
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            for(int i=0;i<circles.size();i++){
                if(circles.size()>1&&Math.abs(touchX-circles.get(i).x)<40&&Math.abs(touchY-circles.get(i).y)<40){
                    circles.remove(i);
                    flag=1;
                    break;
                }
            }
            if(flag==0) {
                CirclesParam cp = new CirclesParam(touchX, touchY, 40, MainActivity.str);
                circles.add(cp);
            }
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE&&flag==0){
            /*for(int i=0;i<circles.size()-1;i++){
                if(Math.abs(touchX-circles.get(i).x)<5){
                    circles.remove(i);
                    flag1=1;
                }
            }*/

            /*circles.remove(circles.size()-1);

            CirclesParam cp = new CirclesParam(touchX, touchY, 40, MainActivity.str);
            circles.add(cp);
            invalidate();*/
        }
        if (event.getAction() == MotionEvent.ACTION_UP){
            //circles.clear();
        }
        /*if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchedTime = event.getDownTime();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            removedTime = event.getEventTime();
            cR = (removedTime - touchedTime) / 11;
            //Log.e("time", String.valueOf(cR));
            if (cR > 25) {
                CirclesParam cp = new CirclesParam(touchX, touchY, 40, "anti");
                circles.add(cp);
            } else {
                CirclesParam cp = new CirclesParam(touchX, touchY, 40, "normal");
                circles.add(cp);
            }
            invalidate();
        }*/
        return true;
    }
}
