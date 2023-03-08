package com.codearms.maoqiqi.drawtext;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import org.jetbrains.annotations.NotNull;

public class TextParams {

    private final String TAG = this.getClass().getSimpleName();

    private final Paint.FontMetrics fontMetrics;

    @NotNull
    private Rect bounds = new Rect();

    private float centerX;

    private float centerY;

    private float left;

    private float top;

    private float right;

    private float bottom;

    public TextParams(@NotNull Paint.FontMetrics fontMetrics) {
        this.fontMetrics = fontMetrics;
//        Log.e(TAG, "FontMetrics->top:" + fontMetrics.top + ",ascent:" + fontMetrics.ascent + ",descent:" + fontMetrics.descent + ",bottom:" + fontMetrics.bottom);
    }

    public void setTextBounds(@NotNull Rect bounds) {
        this.bounds = bounds;
//        Log.e(TAG, "bounds:" + bounds.toShortString());
//        Log.e(TAG, "bounds.width():" + bounds.width() + ",bounds.height():" + bounds.height());
    }

    public void setCenter(float centerX, float centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void setRect(float left, float top, float right, float bottom) {
        this.centerX = (right - left) / 2;
        this.centerY = (bottom - top) / 2;
    }

    public float getRecommendedHeight() {
        return fontMetrics.descent - fontMetrics.ascent;
    }

    public float getBaseY() {
        return centerY - fontMetrics.descent / 2 - fontMetrics.ascent / 2;
    }

    public final float getLineBaseline(int line) {
        return (fontMetrics.descent - fontMetrics.ascent) * line + centerY - fontMetrics.descent / 2 - fontMetrics.ascent / 2;
    }

    public RectF getLineContentArea(int line) {
        return new RectF(left + bounds.left, getLineBaseline(line) + bounds.top, left + bounds.left + bounds.width(), getLineBaseline(line) + bounds.bottom);
    }

    public float getTop() {
        return getBaseY() + fontMetrics.top;
    }

    public float getAscent() {
        return getBaseY() + fontMetrics.ascent;
    }

    public float getDescent() {
        return getBaseY() + fontMetrics.descent;
    }

    public float getBottom() {
        return getBaseY() + fontMetrics.bottom;
    }
}
