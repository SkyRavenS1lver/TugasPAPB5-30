package com.example.gmaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HallOfGames extends AppCompatActivity {
    private final static String RAW_VIDEO = "video1";
    private final static String RAW_VIDEO2 = "video2";
    private long mLastClickTime = 0;
    private boolean keyboardVisibility = false;
    public boolean isDayMode = false;
    public boolean clicked = false;
    private Animation rotateOpen;
    private Animation rotateClose;
    public FloatingActionButton optionsButton;
    View view;
    ConstraintLayout background;
    public static FloatingActionButton changeModeButton;

    private Animation fromBtm;
    private Animation fading;
    private Animation toBtm;
    private Button fadeBtn;
    private VideoView videoView;

    public static AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_games);
        view=findViewById(R.id.loading);
        videoView=findViewById(R.id.imageview);
        fadeBtn = findViewById(R.id.BTNfade);
        background = findViewById(R.id.bg);
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
        fading = AnimationUtils.loadAnimation(this, R.anim.fade);
        changeMode(changeModeButton);
        fadeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add fade animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
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
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);
        videoView.setOnClickListener(null);
        videoView.setOnTouchListener(null);
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
        videoView.setClickable(false);
        videoView.setFocusable(false);
    }


    public void changeMode(View v) {
        try {

            if (isDayMode) {

            }
            else {

            }

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
        if (isDayMode) {
            background.setBackgroundColor(getResources().getColor(R.color.white));
            changeModeButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            optionsButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            optionsButton.setColorFilter(getResources().getColor(R.color.black));
            changeModeButton.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.baseline_dark_mode_24));
        }
        else {
            background.setBackgroundColor(getResources().getColor(R.color.black));
            changeModeButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
            optionsButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
            optionsButton.setColorFilter(getResources().getColor(R.color.white));
            changeModeButton.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.baseline_wb_sunny_24));
        }
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
        }
        else {
//            ((FrameLayout)findViewById(R.id.screenFrag)).setAlpha(0.5f);
            changeModeButton.setVisibility(View.VISIBLE);

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