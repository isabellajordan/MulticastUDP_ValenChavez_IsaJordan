package com.example.estudiante.multicastudp_valenchavez_isajordan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class DrawView extends View {
    ArrayList<Point>punticos;

    public DrawView(Context context) {
        super(context);
        punticos= new ArrayList<Point>();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        punticos= new ArrayList<Point>();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        punticos= new ArrayList<Point>();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        punticos= new ArrayList<Point>();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Point puntoA= new Point(200,200);
        Paint brocha= new Paint();
        brocha.setColor(Color.argb(255,255,0,0));
        brocha.setStrokeWidth(20);

        canvas.drawPoint(puntoA.x,puntoA.y,brocha);
        for (int i=0; i<punticos.size();i++){
            canvas.drawPoint(punticos.get(i).x,punticos.get(i).y,brocha);
        }
    }

    public void addPoint(float x, float y) {
        punticos.add(new Point((int) x,(int) y));
        //REFRESCA VIEW
        invalidate();
    }
}
