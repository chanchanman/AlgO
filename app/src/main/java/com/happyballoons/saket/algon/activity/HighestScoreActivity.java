package com.happyballoons.saket.algon.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.happyballoons.saket.algon.R;

import org.w3c.dom.Text;

import java.io.File;

public class HighestScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;

    ImageView btn_score_share;

    private ImageView img_shot;
    private LinearLayout rootContent;

    int score;
    int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        btn_score_share = (ImageView) findViewById(R.id.btn_score_share);

        //img_shot = (ImageView) findViewById(R.id.img_shot);

        rootContent = (LinearLayout) findViewById(R.id.root_content);
        btn_score_share.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView txtScore = (TextView) findViewById(R.id.textScore);
        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);
        // receive the score from last activity by Intent
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        // display current score
        txtScore.setText("Your score: " + score);

        // use Shared preferences to save the best score
        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        highscore = mypref.getInt("highscore",0);
        if(highscore>= score){
            txtHighScore.setText("High score: "+highscore);
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(300);

            final MediaPlayer mp = MediaPlayer.create(this, R.raw.lost);
            mp.start();
        }


        else
        {
            txtHighScore.setText("New highscore: "+score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.commit();

            final MediaPlayer mp = MediaPlayer.create(this, R.raw.victory);
            mp.start();
        }

    }

    public void onRepeatClick(View view) {
        Intent intent = new Intent(HighestScoreActivity.this, QuizActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_score_share:
                takeScreenshot(ScreenshotType.FULL);
                break;
        }
    }

    /*  Method which will take screenshot on Basis of Screenshot Type ENUM  */
    private void takeScreenshot(ScreenshotType screenshotType) {
        Bitmap b = null;
        switch (screenshotType) {
            case FULL:
                //If Screenshot type is FULL take full page screenshot i.e our root content.
                b = ScreenshotUtils.getScreenShot(rootContent);
                break;
            case CUSTOM:
                //If Screenshot type is CUSTOM

                btn_score_share.setVisibility(View.INVISIBLE);//set the visibility to INVISIBLE of first button


                b = ScreenshotUtils.getScreenShot(rootContent);

                //After taking screenshot reset the button and view again
                btn_score_share.setVisibility(View.VISIBLE);//set the visibility to VISIBLE of first button again


                //NOTE:  You need to use visibility INVISIBLE instead of GONE to remove the view from frame else it wont consider the view in frame and you will not get screenshot as you required.
                break;
        }

        //If bitmap is not null
        if (b != null) {
            //showScreenShotImage(b);//show bitmap over imageview

            File saveFile = ScreenshotUtils.getMainDirectoryName(this);//get the path to save screenshot
            File file = ScreenshotUtils.store(b, "screenshot" + screenshotType + ".jpg", saveFile);//save the screenshot to selected path
            shareScreenshot(file);//finally share screenshot
        } else
            //If bitmap is null show toast message
            Toast.makeText(this, "Failed to take SS", Toast.LENGTH_SHORT).show();

    }

//    /*  Show screenshot Bitmap */
//    private void showScreenShotImage(Bitmap b) {
//        img_shot.setImageBitmap(b);
//    }

    /*  Share Screenshot  */
    private void shareScreenshot(File file) {
        Uri uri = Uri.fromFile(file);//Convert file path into Uri for sharing
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Heyo! My new highscore is " + highscore + " \n Download this app here: https://drive.google.com/open?id=0B6icN2B_efKRMU94NUdiZjQ1VDQ");
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
    }
}
