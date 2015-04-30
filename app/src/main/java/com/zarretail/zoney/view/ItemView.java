package com.zarretail.zoney.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zarretail.zoney.R;

/**
 * Created by David on 4/5/2015.
 */
public class ItemView extends LinearLayout {
    private String question;
    private int width, height;
    private TextView textView;

    public ItemView(Context context, String question, int width, int height) {
        super(context);

        setOrientation(LinearLayout.HORIZONTAL);
        setLayoutParams(new LayoutParams(width, height));

        this.question = question;

        this.width = width / 2;
        this.height = height;
        LineView lineView = new LineView(context);
        addView(lineView, this.width, this.height);

        RelativeLayout rightLayout = new RelativeLayout(context);
        rightLayout.setLayoutParams(new LayoutParams(this.width, this.height));

        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView = new TextView(context);
        rightLayout.addView(textView, lParam);
        addView(rightLayout);

        textView.setText(question);
    }

    private class LineView extends View {

        private Paint paint;

        public LineView(Context context) {
            super(context);
            init();
        }

        private void init() {
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            paint.setAntiAlias(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
        }
    }
}
