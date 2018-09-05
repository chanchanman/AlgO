package com.happyballoons.saket.algon.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.happyballoons.saket.algon.R;
import com.happyballoons.saket.algon.fragments.Sorting;

public class MainActivity extends Activity {

    final Context context = this;

    TextView tv_main;
    Button btn_ds, btn_algo, btn_sort, btn_search, btn_quiz;
    //ImageButton btn_share;
    ImageView img_share;

    FloatingActionButton fab;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        tv_main = (TextView) findViewById(R.id.tv_main);
        btn_ds = (Button) findViewById(R.id.btn_ds);

        btn_search = (Button) findViewById(R.id.btn_main_search);
        btn_sort = (Button) findViewById(R.id.btn_main_sort);
        btn_quiz = (Button) findViewById(R.id.btn_quiz);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        Typeface tf1 = Typeface.createFromAsset(getAssets(), "fonts/san.ttf");
        tv_main.setTypeface(tf1, Typeface.BOLD);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        btn_ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DSActivity.class);
                startActivity(i);
            }
        });

        btn_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SortingActivity.class);
                startActivity(i);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SearchingActivity.class);
                startActivity(i);
            }
        });

        btn_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_TEXT, "Hey! Wanna revise Algorithm Complexities? Check out Saket's new app AlgO(n)! Here: https://drive.google.com/open?id=0B6icN2B_efKRMU94NUdiZjQ1VDQ");
                i.setType("text/plain");
                startActivity(i);
            }
        });
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MyDialogTheme);
        builder.setMessage("Are you sure you want to stop learning Algos?");
        builder.setCancelable(true);
        builder.setNegativeButton("I wanna learn!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Don't Bother Me!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
