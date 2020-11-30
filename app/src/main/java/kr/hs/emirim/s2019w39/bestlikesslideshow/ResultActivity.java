package kr.hs.emirim.s2019w39.bestlikesslideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class ResultActivity extends AppCompatActivity {
    ViewFlipper flipper;
    float downX, upX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        flipper = findViewById(R.id.flipper);
        Button btnPrev = findViewById(R.id.btn_prev);
        Button btnNext = findViewById(R.id.btn_next);
        btnPrev.setOnClickListener(btnListener);
        btnNext.setOnClickListener(btnListener);
        flipper.setOnTouchListener(touchListener);
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent e) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                downX = e.getX();
            } else if (e.getAction() == MotionEvent.ACTION_UP) {
                upX = e.getX();
                if (downX > upX) {
                    flipper.showNext();
                } else if(downX < upX) {
                    flipper.showPrevious();
                }
            }
            return false;   // 기본 행동 취소
        }
    };

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_prev:
                    flipper.setFlipInterval(1000);
                    flipper.startFlipping();
                    break;
                case R.id.btn_next:
                    flipper.stopFlipping();
                    break;
            }
        }
    };
}