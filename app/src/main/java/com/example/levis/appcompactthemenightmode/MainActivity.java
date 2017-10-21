package com.example.levis.appcompactthemenightmode;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Switch button;
    private SQLiteTheme db2;
    private String themeid;
    private String themenumber = "2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db2 = new SQLiteTheme(getApplicationContext());
        HashMap<String, String> user2 = db2.getUserTheme();
        themeid = user2.get("userthemeid");
        setTheme(Themes.getTheme(Integer.parseInt(themeid)));

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (themeid.equals(themenumber)) {
            Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(),R.mipmap.ic_launcher);
            ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription("AppCompactThemeNightMode",bitmap, ContextCompat.getColor(MainActivity.this, R.color.colorPrimary2));
            MainActivity.this.setTaskDescription(taskDescription);
        }else {
            Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(),R.mipmap.ic_launcher);
            ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription("AppCompactThemeNightMode",bitmap, ContextCompat.getColor(MainActivity.this, R.color.colorPrimary1));
            MainActivity.this.setTaskDescription(taskDescription);
        }

        button = (Switch) findViewById(R.id.switch1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (themeid.equals(themenumber)){
                    db2.deleteUsertheme();
                    String themeid2 = "3";
                    db2.addUsertheme(MainActivity.this,themeid2);
                    recreate();
                }else {
                    db2.deleteUsertheme();
                    String themeid3 = "2";
                    db2.addUsertheme(MainActivity.this,themeid3);
                    recreate();
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
