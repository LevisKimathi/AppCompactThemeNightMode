package com.example.levis.appcompactthemenightmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button button;
    private SQLiteTheme db2;
    private String themeid = "0";
    private String themeid2 = "2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Themes.getTheme(Integer.parseInt(themeid)));
        setContentView(R.layout.activity_login);

        db2 = new SQLiteTheme(getApplicationContext());

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db2.addUsertheme(LoginActivity.this, themeid2);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

