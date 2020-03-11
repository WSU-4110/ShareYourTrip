package com.example.shareyourtrip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PostView extends View {

    private static final int SQR_SZ = 100;

    Rect rect = new Rect();
    Paint paint = new Paint();

    public PostView(Context context) {
        super(context);
        init(null);
    }

    public PostView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(attrs);
    }

    public PostView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        rect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        //Creating Rectangle shape
        Rect rect = new Rect();
        rect.left = 100;
        rect.top = 100;
        rect.right = rect.left+100;
        rect.bottom = rect.top+100;

        //Creating green paint
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        //Creating our box
        canvas.drawRect(rect, paint);
    }
}
