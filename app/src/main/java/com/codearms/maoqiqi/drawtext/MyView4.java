package com.codearms.maoqiqi.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView4 extends View {

    private final String TAG = this.getClass().getSimpleName();
    private static final float DESIGN_WIDTH = 1000f;
    private static final float DESIGN_HEIGHT = 1000f;

    private final Paint paint = new Paint();
    private final Paint strokePaint = new Paint();
    private final TextPaint textPaint = new TextPaint();

    private final Rect bounds = new Rect();

    private TextParams params;

    public MyView4(Context context) {
        super(context);
        init();
    }

    public MyView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        params = new TextParams(textPaint.getFontMetrics());
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

        int areaWidth = 400;
        int areaHeight = (int) (Math.ceil(params.getRecommendedHeight()));
        int centerX = areaWidth / 2;
        int centerY = areaHeight / 2;
        params.setCenter(centerX, centerY);

        textPaint.setLetterSpacing(0.2f);
        String text = "def,gj,ABC,中文";

        textPaint.getTextBounds(text, 0, text.length(), bounds);
        Log.e(TAG, "bounds:" + bounds.toShortString());
        params.setTextBounds(bounds);

        canvas.drawText(text, 0, params.getLineBaseline(0), textPaint);
//        canvas.drawRect(params.getLineContentArea(0), strokePaint);

        paint.setColor(Color.RED);
//        canvas.drawLine(0, calculate.getTop(), DESIGN_WIDTH, calculate.getTop(), paint);
//        canvas.drawLine(0, calculate.getAscent(), DESIGN_WIDTH, calculate.getAscent(), paint);
//        canvas.drawLine(0, calculate.getDescent(), DESIGN_WIDTH, calculate.getDescent(), paint);
//        canvas.drawLine(0, calculate.getBottom(), DESIGN_WIDTH, calculate.getBottom(), paint);

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        textPaint.setColor(Color.RED);
        StaticLayout layout = new StaticLayout(text, textPaint, 400, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
        layout.draw(canvas);
//        Log.e(TAG, "layout.getLineBaseline(0):" + layout.getLineBaseline(0));
//        Log.e(TAG, "layout.getLineBaseline(1):" + layout.getLineBaseline(1));
//        Log.e(TAG, "calculate.getLineBaseline(0):" + calculate.getLineBaseline(0));
//        Log.e(TAG, "calculate.getLineBaseline(1):" + calculate.getLineBaseline(1));


        text = ",AfC,中";
        textPaint.setColor(Color.GREEN);
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        Log.e(TAG, "bounds:" + bounds.toShortString());
        canvas.drawText(text, 0, params.getLineBaseline(1), textPaint);
//        canvas.drawRect(params.getLineContentArea(1), strokePaint);

        text = "中";
        textPaint.setColor(Color.YELLOW);
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        Log.e(TAG, "bounds:" + bounds.toShortString());
        canvas.drawText(text, 0, params.getLineBaseline(2), textPaint);
//        canvas.drawRect(params.getLineContentArea(2), strokePaint);

//        canvas.drawLine(0, areaHeight, DESIGN_WIDTH, areaHeight, paint);
//        canvas.drawLine(0, 2 * areaHeight, DESIGN_WIDTH, 2 * areaHeight, paint);
//        canvas.drawLine(0, 3 * areaHeight, DESIGN_WIDTH, 3 * areaHeight, paint);

//        canvas.drawLine(0, calculate.getAscent(), DESIGN_WIDTH, calculate.getAscent(), paint);
//        canvas.drawLine(0, calculate.getAscent() + areaHeight, DESIGN_WIDTH, calculate.getAscent() + areaHeight, paint);
//        canvas.drawLine(0, calculate.getAscent() + areaHeight + areaHeight, DESIGN_WIDTH, calculate.getAscent() + areaHeight + areaHeight, paint);

        paint.setColor(Color.RED);
        canvas.drawLine(0, params.getDescent(), DESIGN_WIDTH, params.getDescent(), paint);
        canvas.drawLine(0, params.getDescent() + areaHeight, DESIGN_WIDTH, params.getDescent() + areaHeight, paint);
        canvas.drawLine(0, params.getDescent() + areaHeight + areaHeight, DESIGN_WIDTH, params.getDescent() + areaHeight + areaHeight, paint);

        BoringLayout.Metrics boring = BoringLayout.isBoring("drawText", textPaint);

//        layout.getLineBounds()

        text = "def,gj,ABC,中文";
        Rect rect = new Rect();
        for (int i = 0; i < layout.getLineCount(); i++) {
            Log.e(TAG, "layout.getLineStart():" + layout.getLineStart(i));
            Log.e(TAG, "layout.getLineStart():" + layout.getLineEnd(i));
            float l = layout.getLineBaseline(i);
            textPaint.getTextBounds(text, layout.getLineStart(i), layout.getLineEnd(i), rect);
            Log.e(TAG, "rect:" + rect.toShortString());
            canvas.drawRect(rect.left, l + rect.top, rect.left + rect.width(), l + rect.bottom, strokePaint);
        }

        canvas.restore();
    }
}
