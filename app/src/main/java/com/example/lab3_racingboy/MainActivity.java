package com.example.lab3_racingboy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtCoin, txtPlayer;
    Button btnStartRace, btnStartBet;
    SeekBar pig1, pig2, pig3;
    Boolean cbx1, cbx2, cbx3;
    String txtMainCoin;
    Boolean isWin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Binding();
        int chose = 1;

        final CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long l) {
                Random random = new Random();
                int goal = pig1.getMax();
                pig1.setProgress(pig1.getProgress() + random.nextInt(10));
                pig2.setProgress(pig2.getProgress() + random.nextInt(10));
                pig3.setProgress(pig3.getProgress() + random.nextInt(10));

                int chose = isChose();
                if (pig1.getProgress() >= goal && chose == 1) {

                    Toast.makeText(MainActivity.this, "PIG 1 WIN!!!\n You're the winner!", Toast.LENGTH_LONG).show();
                    txtPlayer.setText("You win");
                    this.onFinish();
                }

                if (pig2.getProgress() >= goal && chose == 2) {

                    Toast.makeText(MainActivity.this, "PIG 2 WIN!!!\n You're the winner!", Toast.LENGTH_LONG).show();
                    txtPlayer.setText("You win");
                    this.onFinish();
                }

                if (pig3.getProgress() >= goal && chose == 3) {

                    Toast.makeText(MainActivity.this, "PIG 3 WIN!!!\n You're the winner!", Toast.LENGTH_LONG).show();
                    txtPlayer.setText("You win");
                    this.onFinish();
                }

                if ((pig1.getProgress() >= goal && chose != 1) || (pig2.getProgress() >= goal && chose != 2) || (pig3.getProgress() >= goal && chose != 3)) {

                    Toast.makeText(MainActivity.this, "You lose", Toast.LENGTH_LONG).show();
                    txtPlayer.setText("You win");
                    isWin = false;

                    this.onFinish();
                }


            }

            @Override
            public void onFinish() {
                checkWin(10);
                backBetActivity();
                this.cancel();
            }
        };

        btnStartRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chose == 1) {
                    if (Integer.parseInt(txtCoin.getText().toString()) <= 0) {
                        Toast.makeText(MainActivity.this, "You lost all of your point\n This is new game", Toast.LENGTH_SHORT).show();
                        txtCoin.setText("100");
                    }
                    btnStartRace.setVisibility(View.INVISIBLE);

                    countDownTimer.start();

                } else {
                    Toast.makeText(MainActivity.this, "Please choose a pokemon", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        btnStartBet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(this, MainActivity.class);
//            }
//        });

    }

    private void checkWin(int betCoin) {
        int result = Integer.parseInt(txtMainCoin);
        if (isWin) {
            result += betCoin;
        } else {
            result -= betCoin;
        }
        txtMainCoin = String.valueOf(result);
    }

    private void backBetActivity(){
        Intent intent = new Intent(this, BetActivity.class);
        intent.putExtra("txtCoin", txtMainCoin);
        startActivity(intent);
        finish();
    }


    private int isChose() {
        if (cbx1) {
            return 1;
        }
        if (cbx2) {
            return 2;
        }
        if (cbx3) {
            return 3;
        }
        return 0;
    }

    private void Binding() {
        Bundle extras = getIntent().getExtras();
        txtCoin = (TextView) findViewById(R.id.txtCoin);
        txtPlayer = (TextView) findViewById(R.id.txtplayer);
        btnStartRace = (Button) findViewById(R.id.btnStart);
        btnStartBet = (Button) findViewById(R.id.button2);
        pig1 = (SeekBar) findViewById(R.id.pig1);
        pig2 = (SeekBar) findViewById(R.id.pig2);
        pig3 = (SeekBar) findViewById(R.id.pig3);
        if (extras != null) {
            cbx1 = extras.getBoolean("cbx1");
            cbx2 = extras.getBoolean("cbx2");
            cbx3 = extras.getBoolean("cbx3");
            txtMainCoin = extras.getString("txtCoin");
            txtCoin.setText(txtMainCoin);
        }

    }

}