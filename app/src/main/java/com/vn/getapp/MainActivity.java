package com.vn.getapp;

import android.annotation.TargetApi;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 *Sample code to get details of any installed app
 * Version code
 * Version name
 * App name
 * Icon
 */


public class MainActivity extends AppCompatActivity {

    private static final String LOG = MainActivity.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView appversion = (TextView) findViewById(R.id.textView);

        List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
        ArrayList<AppInfo> result = new ArrayList<>();

        for (int i = 0; i < apps.size(); i++) {
            PackageInfo p = apps.get(i);
            AppInfo newInfo = new AppInfo();
            newInfo.pname = p.packageName;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = p.versionCode;
            result.add(newInfo);
        }


        String popcorn = "com.vn.popcorn"; //the app whose version number we want to retrieve

        for (AppInfo name : result) {
            Log.v(LOG,name.pname);
            if (Objects.equals(popcorn, name.pname)){
                Log.v(LOG,"Popcorn version name is --------------------> " + name.versionName);
                String message = popcorn + " -->" + name.versionName;
                appversion.setText(message);
            }
        }

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
