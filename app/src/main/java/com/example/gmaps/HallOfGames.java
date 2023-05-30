package com.example.gmaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.URLUtil;
import android.widget.Button;

import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HallOfGames extends AppCompatActivity {
    private String current = "";
    private final static String RAW_VIDEO = "video1";
    private final static String RAW_VIDEO2 = "video2";
    private final static String RAW_VIDEO3 = "video3";
    private long mLastClickTime = 0;
    private boolean isPlaying = true;
    public boolean isDayMode = false;
    public boolean clicked = false;
    public int currentColor;
    public int currentTextColor;
    public int greyColor = com.google.android.libraries.places.R.color.quantum_grey;
    private Animation rotateOpen;
    private Animation rotateClose;
    public FloatingActionButton optionsButton;
    View view;
    ConstraintLayout background;
    public static FloatingActionButton changeModeButton;

    private Animation fromBtm;
    private Animation fading;
    private Animation toBtm;
    private Button Btn1;
    private Button Btn2;
    private Button Btn3;
    private VideoView videoView;

    public static AlertDialog dialog;
    public TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_games);
        view=findViewById(R.id.loading);
        videoView=findViewById(R.id.imageview);
        Btn1 = findViewById(R.id.btn1);
        Btn2 = findViewById(R.id.btn2);
        Btn3 = findViewById(R.id.btn3);
        title = findViewById(R.id.title);
        background = findViewById(R.id.bg);
        current = "btn1";
        makeLoading();
//        dialog.show();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appearButton(view);
            }
        });
        changeModeButton = findViewById(R.id.changeModeButton);
        optionsButton = findViewById(R.id.optionsButton);
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close);
        fromBtm = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
        toBtm = AnimationUtils.loadAnimation(this, R.anim.to_bottom);
//        fading = AnimationUtils.loadAnimation(this, R.anim.fade);
        changeMode(changeModeButton);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add fade animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                current = "btn1";
                Btn1.setEnabled(false);
                Btn2.setEnabled(true);
                Btn3.setEnabled(true);
                Btn1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(greyColor)));
                Btn1.setTextColor(getResources().getColor(R.color.white));
                Btn2.setTextColor(getResources().getColor(currentTextColor));
                Btn3.setTextColor(getResources().getColor(currentTextColor));
                Btn2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentColor)));
                Btn3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentColor)));
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        releaseVideo();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        initializePlayer(RAW_VIDEO);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                videoView.startAnimation(animation);
            }
        });
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add fade animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                current = "btn2";
                Btn2.setEnabled(false);
                Btn1.setEnabled(true);
                Btn3.setEnabled(true);
                Btn2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(greyColor)));
                Btn1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentColor)));
                Btn3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentColor)));
                Btn2.setTextColor(getResources().getColor(R.color.white));
                Btn1.setTextColor(getResources().getColor(currentTextColor));
                Btn3.setTextColor(getResources().getColor(currentTextColor));
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        releaseVideo();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        initializePlayer(RAW_VIDEO2);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                videoView.startAnimation(animation);
            }
        });
        Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add fade animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                current = "btn3";
                Btn3.setEnabled(false);
                Btn2.setEnabled(true);
                Btn1.setEnabled(true);
                Btn3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(greyColor)));
                Btn2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentColor)));
                Btn1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentColor)));
                Btn3.setTextColor(getResources().getColor(R.color.white));
                Btn2.setTextColor(getResources().getColor(currentTextColor));
                Btn1.setTextColor(getResources().getColor(currentTextColor));
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        releaseVideo();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        initializePlayer(RAW_VIDEO3);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                videoView.startAnimation(animation);
            }
        });
        videoView.setOnTouchListener(new View.OnTouchListener() {
                                         @Override
                                         public boolean onTouch(View view, MotionEvent motionEvent) {
                                             if (isPlaying){videoView.pause();}
                                             else {videoView.start();}
                                             isPlaying = !isPlaying;
                                             return false;
                                         }
                                     });
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        videoView.seekTo(1);
                        videoView.start();
                    }
                });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
    }
    public Uri getMedia(String mediaName) {
        if (URLUtil.isValidUrl(mediaName)) {
            return Uri.parse(mediaName);
        } else {
            return Uri.parse("android.resource://"
                    + getPackageName() + "/raw/" + mediaName);
        }
    }
    public void initializePlayer(String medianame){
        releaseVideo();
        Uri videoUri = getMedia(medianame);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }


    public void changeMode(View v) {
        try {


            isDayMode = !isDayMode;
            changeColorBtn(v.getContext());
        }
        catch(Resources.NotFoundException e){
            return;
        }
    }
    public void releaseVideo(){
        videoView.stopPlayback();
    }
    public void changeColorBtn(Context ctx){
        int from;int to;
        if (isDayMode) {
            currentColor = R.color.black;
            currentTextColor =R.color.white;
            from = Color.BLACK;
            to = Color.WHITE;
            changeModeButton.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.baseline_dark_mode_24));
        }
        else {
            currentColor = R.color.white;
            currentTextColor =R.color.black;
            from = Color.WHITE;
            to = Color.BLACK;
            changeModeButton.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.baseline_wb_sunny_24));
        }
        ObjectAnimator colorAnim = (ObjectAnimator.ofInt(title, "textColor",
                to, from)).setDuration(1000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.start();
        colorAnim = (ObjectAnimator.ofInt(background, "backgroundColor",
                from, to)).setDuration(500);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.start();
        changeModeButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentTextColor)));
        optionsButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentTextColor)));
        optionsButton.setColorFilter(getResources().getColor(currentColor));
        if (current!="btn1"){
        Btn1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentColor)));
        Btn1.setTextColor(getResources().getColor(currentTextColor));}
        if (current!="btn2"){
        Btn2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentColor)));
        Btn2.setTextColor(getResources().getColor(currentTextColor));}
        if (current!="btn3"){
        Btn3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(currentColor)));
        Btn3.setTextColor(getResources().getColor(currentTextColor));}
    }
    public void appearButton(View v){
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked;
        if (clicked){
        view.setVisibility(View.VISIBLE);
        view.bringToFront();
        }
        else{view.setVisibility(View.GONE);}
    }


    private void setAnimation(boolean clicked) {
    if (clicked){
        changeModeButton.startAnimation(toBtm);
        optionsButton.startAnimation(rotateOpen);
    }
    else {
        changeModeButton.startAnimation(fromBtm);
        optionsButton.startAnimation(rotateClose);
       }
    }

    private void setVisibility(boolean clicked) {
        if (clicked){
//            ((FrameLayout)findViewById(R.id.screenFrag)).setAlpha(1f);
            changeModeButton.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
        }
        else {
//            ((FrameLayout)findViewById(R.id.screenFrag)).setAlpha(0.5f);
            changeModeButton.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);

        }
    }

    public void makeLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HallOfGames.this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.loading_bar,null);
        builder.setView(view);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer(RAW_VIDEO);
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            videoView.pause();
        }
    }
}