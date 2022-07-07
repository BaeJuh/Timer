package com.juhwan.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button stopButton;
    private TextView minute;
    private TextView second;
    private boolean isStart = false;
    private boolean isStop;

    private NumberPicker minutePicker, secondPicker;
    Dialog timeDialog;
    String m, s; // 대화상자에서 지정한 분, 초 값이 들어있는 문자열

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
                    // 시간 선택
                    isStart = true;
                }
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {

                    }
                };
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
                    stopButton.setText("STOP");
                    minute.setText("00");
                    second.setText("00");
                    isStart = false;
                    isStop = false;
                }
            }
        });
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
                minute.setText(minutePicker.getValue() + "");
                second.setText(secondPicker.getValue() + "");
                if (Integer.parseInt(minute.getText().toString()) < 10) {
                    m = minute.getText().toString();
                    minute.setText("0" + m);
                }
                if (Integer.parseInt(second.getText().toString()) < 10) {
                    s = second.getText().toString();
                    second.setText("0" + s);
                }

                timeDialog.dismiss();
            }
        });
    }
}