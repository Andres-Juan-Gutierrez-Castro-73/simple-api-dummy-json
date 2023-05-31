package com.example.api_dummy_json_users1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashActivity extends AppCompatActivity {
    //INSTANCIA DEL COUNT DOWN TIMER
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //DEFINICION DEL COUNTDOWN TIMER
        countDownTimer = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                //CUANDO EL CONTADOR TERMINE SE CAMBIA A LA ACTIVIDAD PRINCIPAL
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };

        //SE INICIA EL TEMPORIZADOR
        countDownTimer.start();
    }
}