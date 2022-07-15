package com.juhwan.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button stopButton;
    private TextView minute;
    private TextView second;
    private boolean isStart = false;
    private boolean isStop = false;

    private NumberPicker minutePicker, secondPicker;
    Dialog timeDialog;
    private int m, s; // setText 할 분, 초 값이 들어있는 문자열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        minute = findViewById(R.id.minute);
        second = findViewById(R.id.second);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStart == false) {
                    showTimeDialog();

                    isStart = true;
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStart == true && isStop == false) {
                    stopButton.setText("CLEAR");
                    isStop = true;
                }
                else if (isStart == true && isStop == true) {
                    clearTimer();
                }
            }
        });
    }

    public void startTimer() { // start 버튼을 누를 경우 // 타이머가 생성됨
        Timer timer = new Timer();
        TimerTask timerTask;

        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isStop == true) {

                } else {
                    if (s > 0) {
                        s --;
                    }
                    if (m != 0 && s == 0) {
                        m --;
                        s = 59;
                    }

                    minute.post(new Runnable() {
                        @Override
                        public void run() {

                            if (m < 10) {
                                minute.setText("0" + m);
                            } else {
                                minute.setText(m + "");
                            }
                        }
                    });
                    second.post(new Runnable() {
                        @Override
                        public void run() {
                            if (s < 10) {
                                second.setText("0" + s);
                            } else {
                                second.setText(s + "");
                            }
                        }
                    });
                    if (m == 0 && s == 0) {
                        timer.cancel();
                    }
                }
            }
        };
        timer.schedule(timerTask,1000,1000);
    }

    public void stopTimer() { // stop 버튼을 누를 경우

    }

    public void clearTimer() { // 타이머 초기화
        m = 0;
        s = 0;

        stopButton.setText("STOP");

        minute.setText("00");
        second.setText("00");

        isStart = false;
        isStop = false;
    }

    public void showTimeDialog() {
        timeDialog = new Dialog(MainActivity.this);
        timeDialog.setContentView(R.layout.time_dialog);

        timeDialog.setCanceledOnTouchOutside(false);

        minutePicker = timeDialog.findViewById(R.id.minutePicker);
        secondPicker = timeDialog.findViewById(R.id.secondPicker);

        Button okButton = timeDialog.findViewById(R.id.okButton);

        minutePicker.setMaxValue(60);
        minutePicker.setMinValue(0);
        secondPicker.setMaxValue(59);
        secondPicker.setMinValue(1);
        timeDialog.show();
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m = Integer.parseInt(minutePicker.getValue() + "");
                s = Integer.parseInt(secondPicker.getValue() + "");

                if (m < 10) {
                    minute.setText("0" + m);
                } else {
                    minute.setText(Integer.toString(m));
                }
                if (s < 10) {
                    second.setText("0" + s);
                } else {
                    second.setText(Integer.toString(s));
                }

                timeDialog.dismiss();
                startTimer();
            }
        });
    }
}

/*
Toast t = Toast.makeText(getApplicationContext(), m + "  " + s, Toast.LENGTH_LONG);
        t.show();
*/