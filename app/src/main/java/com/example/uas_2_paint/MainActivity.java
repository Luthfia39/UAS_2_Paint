package com.example.uas_2_paint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();
    int vWidth, vHeight;

    private AnimatorSet mAnimatorSet = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);

        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));

        ObjectAnimator show = ObjectAnimator.ofFloat(mImgView, "alpha",
                1);
        show.setDuration(2000);
        ObjectAnimator rotateY = ObjectAnimator.ofFloat(mImgView, "rotationY",
                180);
        rotateY.setDuration(2000);
        ObjectAnimator hidden = ObjectAnimator.ofFloat(mImgView, "alpha",
                0);
        hidden.setDuration(2000);
        hidden.setStartDelay(2000);

        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimatorSet.play(show).before(rotateY).before(hidden);
                mAnimatorSet.start();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        vWidth = mImgView.getWidth();
        vHeight = mImgView.getHeight();

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        drawHead();
        drawRightEye();
        drawLeftEye();
        drawEyeConnector();
    }

    private void drawEyeConnector() {
        mCanvas.drawRect(vWidth/2 + 170, vHeight/2 + 12, vWidth/2 - 170,
                vHeight/2 - 12, mCirclePaint);
    }

    private void drawLeftEye() {
        // mata kiri
        mCanvas.drawCircle(vWidth/2 + 170, vHeight/2, 50, mCirclePaint);
    }

    private void drawRightEye() {
        // mata kanan
        mCanvas.drawCircle(vWidth/2 - 170, vHeight/2, 50, mCirclePaint);
    }

    private void drawHead() {
        mCanvas.drawOval(new RectF(vWidth/2 + 350, vHeight/2 + 220, vWidth/2 - 350,
                vHeight/2 - 220), mHeadPaint);
    }
}