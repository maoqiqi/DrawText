package com.codearms.maoqiqi.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView2 extends View {

    private final String TAG = this.getClass().getSimpleName();
    private static final float DESIGN_WIDTH = 1000f;
    private static final float DESIGN_HEIGHT = 1000f;

    private final Paint paint = new Paint();
    private final Paint strokePaint = new Paint();
    private final TextPaint textPaint = new TextPaint();

    private final Rect bounds = new Rect();

    public MyView2(Context context) {
        super(context);
        init();
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        strokePaint.setAntiAlias(true);
        strokePaint.setColor(Color.BLUE);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(100);
        textPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "width:" + getWidth() + ",height:" + getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制背景色
        canvas.drawColor(Color.LTGRAY);

        paint.setColor(Color.BLACK);
        // 绘制中间线
        canvas.drawLine(0, DESIGN_HEIGHT / 2f, DESIGN_WIDTH, DESIGN_HEIGHT / 2f, paint);
        canvas.drawLine(DESIGN_WIDTH / 2f, 0, DESIGN_WIDTH / 2f, DESIGN_HEIGHT, paint);

        canvas.save();
//        canvas.scale(getWidth() / DESIGN_WIDTH, getHeight() / DESIGN_HEIGHT);

        int areaWidth = 800;
        int areaHeight = 200;
        int centerX = 500;
        int centerY = 500;

        // 根据宽度、高度、中心点计算上下左右坐标
        float left = centerX - areaWidth / 2f;
        float top = centerY - areaHeight / 2f;
        float right = centerX + areaWidth / 2f;
        float bottom = centerY + areaHeight / 2f;

        textPaint.setLetterSpacing(0.2f);
        String text = "ˉˊˇˋdfgj,AB,中文";

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        Log.e(TAG, "fontMetrics->top:" + fontMetrics.top + ",ascent:" + fontMetrics.ascent + ",descent:" + fontMetrics.descent + ",bottom:" + fontMetrics.bottom);

        textPaint.getTextBounds(text, 0, text.length(), bounds);

        float textWidth = textPaint.measureText(text);
        Log.e(TAG, "textWidth:" + textWidth);
        // 用FontMetrics对象计算高度
        float textHeight = fontMetrics.bottom - fontMetrics.top;
        Log.e(TAG, "textHeight:" + textHeight);

        // 计算baseX,baseY
        float baseX = (textWidth - bounds.width()) / 2f; // LEFT情况下的baseX
        Log.e(TAG, "baseX:" + baseX);

        float b = -fontMetrics.bottom / 2 - fontMetrics.top / 2;
        Log.e(TAG, "b:" + b);
        float baseline3 = centerY - fontMetrics.bottom / 2 - fontMetrics.top / 2;
        float baseline4 = (bottom + top - fontMetrics.bottom - fontMetrics.top) / 2;
        Log.e(TAG, "baseline3:" + baseline3 + ",baseline4:" + baseline4);

        canvas.drawText(text, centerX - bounds.width() / 2f - baseX, baseline3, textPaint);

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        canvas.drawRect(centerX - textWidth / 2f, centerY - textHeight / 2f, centerX + textWidth / 2f, centerY + textHeight / 2f, strokePaint);

        paint.setColor(Color.RED);
        canvas.drawLine(0, baseline3 + fontMetrics.top, DESIGN_WIDTH, baseline3 + fontMetrics.top, paint);
        canvas.drawLine(0, baseline3 + fontMetrics.bottom, DESIGN_WIDTH, baseline3 + fontMetrics.bottom, paint);
        canvas.restore();
    }
}
