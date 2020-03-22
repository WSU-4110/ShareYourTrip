package com.example.shareyourtrip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PostView extends View {

    //Views that PostView is composed of
    TextView title;
    TextView author;
    TextView description;

    //Shapes/color used
    Rect rect = new Rect();
    Paint paint = new Paint();

    public PostView(Context context) {
        super(context);
        init(context, null);
    }

    public PostView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context, attrs);
    }

    public PostView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        //Initializing shape
        rect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);

        //Initializing textviews
        title = new TextView(context);
        author = new TextView(context);
        description = new TextView(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        //Creating Rectangle shape
        rect.left = 75;
        rect.top = 75;
        rect.right = canvas.getWidth() - 75;
        rect.bottom = rect.top + 300;

        //Creating our box
        canvas.drawRect(rect, paint);

        title.setText("Title");
        title.setTextColor(Color.BLACK);
        title.setTop(rect.top);
        title.setBottom(rect.bottom);
        title.setLeft(rect.left);
        title.setRight(rect.right);

    }
}
