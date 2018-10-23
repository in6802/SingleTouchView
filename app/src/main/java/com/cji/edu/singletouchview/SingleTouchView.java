package com.cji.edu.singletouchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();//지나가는 경로 좌표를 저장하는 변수

    // paint 속성 주기
    public SingleTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    // 화면에 그리기
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    // 터치 좌표값 얻어서 경로에 저장
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 터치 좌표 얻어옴
        float eventX = event.getX();
        float eventY = event.getY();

        // 경로에 좌표값 넣어줌
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(eventX, eventY); break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY); break;
            case MotionEvent.ACTION_UP: break;
            default: return false;
        }
        // 그리기
        invalidate();
        return true;
    }
}
