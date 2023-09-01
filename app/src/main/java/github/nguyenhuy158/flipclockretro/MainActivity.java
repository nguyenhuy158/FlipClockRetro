package github.nguyenhuy158.flipclockretro;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.animation.AnimatorSet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private TextView secondTextView0;
    private TextView secondTextView1;
    private Handler handlerSecond = new Handler(Looper.getMainLooper());
    private Handler handlerSecond1 = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondTextView0 = findViewById(R.id.second0);
        secondTextView1 = findViewById(R.id.second1);

        AnimatorSet flipAnimation0 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_animation);
        AnimatorSet flipAnimation1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_animation);
        flipAnimation1.setTarget(secondTextView1);
        flipAnimation0.setTarget(secondTextView0);
        secondTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipAnimation0.start();
            }
        });


        Runnable updateTimeRunnable0 = new Runnable() {
            @Override
            public void run() {
                updateClock0();
                flipAnimation0.start();
                handlerSecond.postDelayed(this, 1000);
            }
        };
        handlerSecond.postDelayed(updateTimeRunnable0, 0);

        Runnable updateTimeRunnable1 = new Runnable() {
            @Override
            public void run() {
                updateClock1();
                flipAnimation1.start();
                handlerSecond.postDelayed(this, 10000 - 50);
            }
        };
        handlerSecond.postDelayed(updateTimeRunnable1, 0);
    }

    private void updateClock0() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        sdf = new SimpleDateFormat("ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        String s = String.valueOf(currentTime.charAt(1));
        secondTextView0.setText(s);
    }

    private void updateClock1() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        sdf = new SimpleDateFormat("ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        String s = String.valueOf(currentTime.charAt(0));
        secondTextView1.setText(s);
    }
}