package com.example.karma;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class DrawLineWithFinger extends View {
    final static String TAG = "at.fhooe.mc.karma DrawLineWithFinger";


    private Paint mPaint;
    private Path mPath;

    private float mX;
    private float mY;
    private int size = 170;

    protected int[] mPixelX;
    protected int[] mPixelY;

    public static int width;
    public static int height;

    private static final float TOUCH_TOLERANCE = 1;
    public static Activity mActivity;

    public DrawLineWithFinger(Context _context, AttributeSet _attrs){
        super(_context, _attrs);
        mPath = new Path();
        mPaint = new Paint(Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(size);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPixelY = new int[height];
        mPixelX = new int[width];

        for(int i = 0; i < width; i++){
            mPixelX[i] = 0;
        }

        for(int i = 0; i < height; i++){
            mPixelY[i] = 0;
        }
    }


    @Override
    protected void onDraw(Canvas _canvas) {
        _canvas.drawPath(mPath,mPaint);
        super.onDraw(_canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent _event) {

        float x  = _event.getX();
        float y  = _event.getY();

        switch (_event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                mPath.moveTo(x,y);
                mX = x;
                mY = y;
                invalidate();
            }break;
            case MotionEvent.ACTION_MOVE: {
                int intY = (int) y;
                int intX = (int) x;

                int indexX;
                int indexY;
                for(int i = 0; i < size; i++){
                    if(i < size/2) {
                        indexX = intX + i;
                        indexY = intY + i;
                    } else {
                        indexX = intX - i;
                        indexY = intY - i;
                    }
                    if(indexY < height && indexY > -1 && indexX < width && indexX > -1) {
                        mPixelY[indexY] = 1;
                        mPixelX[indexX] = 1;
                    }
                }
                float dx = Math.abs(x - mX);
                float dy = Math.abs(y - mY);
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                    mX = x;
                    mY = y;
                }
                invalidate();
            }break;
            case MotionEvent.ACTION_UP:{
                if(isScreenFull()){
                    LevelCompleteDialog d = new LevelCompleteDialog(mActivity);
                    d.show();
                }
            }break;
        }

        return true;
    }

    private boolean isScreenFull(){
        for(int i = 0; i < mPixelX.length; i++){
            if(i % 10 == 0 && mPixelX[i] == 0){
                return false;
            }
        }
        for(int i = 0; i < mPixelY.length; i++){
            if(i % 10 == 0 && mPixelY[i] == 0){
                return false;
            }
        }
        return true;
    }
}
