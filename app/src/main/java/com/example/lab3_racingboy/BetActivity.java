package com.example.lab3_racingboy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class BetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBet;
    CheckBox cbx1, cbx2, cbx3;
    TextView txtCoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        Binding();
        btnBet.setOnClickListener(this);

    }


    private int isChose() {
        if (cbx1.isChecked()) {
            return 1;
        }
        if (cbx2.isChecked()) {
            return 2;
        }
        if (cbx3.isChecked()) {
            return 3;
        }
        return 0;
    }

    private void Binding() {
        cbx1 = (CheckBox) findViewById(R.id.checkBox);
        cbx2 = (CheckBox) findViewById(R.id.checkBox2);
        cbx3 = (CheckBox) findViewById(R.id.checkBox3);
        btnBet = (Button) findViewById(R.id.btnBet);
        txtCoin = (TextView) findViewById(R.id.txtCoin);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String txtMainCoin = extras.getString("txtCoin");
            txtCoin.setText(txtMainCoin);
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnBet) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("cbx1", cbx1.isChecked());
            intent.putExtra("cbx2", cbx2.isChecked());
            intent.putExtra("cbx3", cbx3.isChecked());
            intent.putExtra("txtCoin", txtCoin.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}