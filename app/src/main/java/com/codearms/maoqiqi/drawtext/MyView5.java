package com.codearms.maoqiqi.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView5 extends View {

    private final String TAG = this.getClass().getSimpleName();
    private static final float DESIGN_WIDTH = 1000f;
    private static final float DESIGN_HEIGHT = 1000f;

    private final Paint paint = new Paint();
    private final Paint strokePaint = new Paint();
    private final TextPaint textPaint = new TextPaint();

    private final Rect bounds = new Rect();

    public MyView5(Context context) {
        super(context);
        init();
    }

    public MyView5(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView5(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyView5(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        strokePaint.setAntiAlias(true);
        strokePaint.setColor(Color.GREEN);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(100);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(1);
//        textPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "width:" + getWidth() + ",height:" + getHeight());
        // x0：表示渐变的起始点x坐标；
        //y0：表示渐变的起始点y坐标；
        //x1：表示渐变的终点x坐标；
        //y1：表示渐变的终点y坐标；
        //colors：表示渐变的颜色数组；
        //positions：用来指定颜色数组的相对位置；
        // color0： 表示渐变开始颜色；
        //color1： 表示渐变结束颜色；
        //tile：表示平铺方式

        // Shader.TileMode有3种参数可供选择，分别为CLAMP、REPEAT和MIRROR：
        //1，CLAMP：如果渲染器超出原始边界范围，则会复制边缘颜色对超出范围的区域进行着色；
        //2，REPEAT：在横向和纵向上以平铺的形式重复渲染位图；
        //3，MIRROR：在横向和纵向上以镜像的方式重复渲染位图
        //
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 400, new int[]{0xFF00FF00, 0xFFFF0000}, null, Shader.TileMode.REPEAT);
        textPaint.setShader(linearGradient);
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

        paint.setColor(Color.DKGRAY);
        // 绘制矩形区域
        canvas.drawRect(left, top, right, bottom, paint);

        textPaint.setLetterSpacing(0.2f);
        String text = "defg,ABC,中文";
        // 以矩形上边绘制文字
        canvas.drawText(text, left, top, textPaint);

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        Log.e(TAG, "fontMetrics->top:" + fontMetrics.top + ",ascent:" + fontMetrics.ascent + ",descent:" + fontMetrics.descent + ",bottom:" + fontMetrics.bottom);

        paint.setColor(Color.WHITE);
        // 绘制top
        canvas.drawLine(0, top + fontMetrics.top, DESIGN_WIDTH, top + fontMetrics.top, paint);

        paint.setColor(Color.YELLOW);
        // 绘制ascent
        canvas.drawLine(0, top + fontMetrics.ascent, DESIGN_WIDTH, top + fontMetrics.ascent, paint);

        paint.setColor(Color.BLUE);
        // 绘制descent
        canvas.drawLine(0, top + fontMetrics.descent, DESIGN_WIDTH, top + fontMetrics.descent, paint);

        paint.setColor(Color.RED);
        // 绘制bottom
        canvas.drawLine(0, top + fontMetrics.bottom, DESIGN_WIDTH, top + fontMetrics.bottom, paint);
        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // ========================================================================================================================================================================
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        Log.e(TAG, "bounds:" + bounds.toShortString());
        Log.e(TAG, "bounds.width():" + bounds.width() + ",bounds.height():" + bounds.height());
        bounds.offset((int) left, (int) top);
        canvas.drawRect(bounds, strokePaint);
        // ========================================================================================================================================================================

        float textWidth = textPaint.measureText(text);
        Log.e(TAG, "textWidth:" + textWidth);
        float textSize = 80f;
        // 用FontMetrics对象计算高度
        float textHeight = fontMetrics.bottom - fontMetrics.top;
        Log.e(TAG, "textHeight:" + textHeight);

        // 计算baseX,baseY
        float baseX = (textWidth - bounds.width()) / 2f; // LEFT情况下的baseX
        Log.e(TAG, "baseX:" + baseX);

        // 2种方式计算中线到bottom的距离
        // 1.(ascent+descent)/2 = descent+中线到baseline的距离
        // 公式推导
        // 中线到baseline的距离 = (ascent + descent) / 2 - descent
        // 由于ascent是负值,所以
        // 中线到baseline的距离 = (descent - ascent) / 2 - descent
        //                    = -descent/2 - ascent/2
        float a = -fontMetrics.descent / 2 - fontMetrics.ascent / 2;
        Log.e(TAG, "a:" + a);
        float baseline1 = centerY - fontMetrics.descent / 2 - fontMetrics.ascent / 2;
        float baseline2 = (bottom + top - fontMetrics.descent - fontMetrics.ascent) / 2;
        Log.e(TAG, "baseline1:" + baseline1 + ",baseline2:" + baseline2);

        // 2.(top+bottom)/2 = bottom+中线到baseline的距离
        // 同上
        // 中线到baseline的距离 = (bottom - top) / 2 - bottom
        //                    = -bottom/2 - top/2
        float b = -fontMetrics.bottom / 2 - fontMetrics.top / 2;
        Log.e(TAG, "b:" + b);
        float baseline3 = centerY - fontMetrics.bottom / 2 - fontMetrics.top / 2;
        float baseline4 = (bottom + top - fontMetrics.bottom - fontMetrics.top) / 2;
        Log.e(TAG, "baseline3:" + baseline3 + ",baseline4:" + baseline4);

        // 通过FontMetrics和Rect两种方式计算的baseline还是有一点差别的

        canvas.drawText(text, centerX - bounds.width() / 2f - baseX, baseline1, textPaint);
        // canvas.drawText(text, centerX - bounds.width() / 2f - baseX, baseline3, textPaint);

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        canvas.drawRect(centerX - textWidth / 2f, centerY - textHeight / 2f, centerX + textWidth / 2f, centerY + textHeight / 2f, strokePaint);
        canvas.drawRect(centerX - bounds.width() / 2f, centerY - bounds.height() / 2f, centerX + bounds.width() / 2f, centerY + bounds.height() / 2f, strokePaint);

//        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//        Log.e(TAG, "fontMetrics->top:" + fontMetrics.top + ",ascent:" + fontMetrics.ascent + ",descent:" + fontMetrics.descent + ",bottom:" + fontMetrics.bottom);
//
//        paint.setColor(Color.WHITE);
//        // 绘制top
//        canvas.drawLine(0, baseline1 + fontMetrics.top, DESIGN_WIDTH, baseline1 + fontMetrics.top, paint);
//
//        paint.setColor(Color.YELLOW);
//        // 绘制ascent
        canvas.drawLine(0, baseline1 + fontMetrics.ascent, DESIGN_WIDTH, baseline1 + fontMetrics.ascent, paint);
//
//        paint.setColor(Color.BLUE);
//        // 绘制descent
        canvas.drawLine(0, baseline1 + fontMetrics.descent, DESIGN_WIDTH, baseline1 + fontMetrics.descent, paint);
//
//        paint.setColor(Color.RED);
//        // 绘制bottom
//        canvas.drawLine(0, baseline1 + fontMetrics.bottom, DESIGN_WIDTH, baseline1 + fontMetrics.bottom, paint);
//        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        canvas.restore();
    }
}
