package com.t3h.musicplayer.activities;

import android.content.Intent;
import android.os.Handler;

import com.t3h.musicplayer.R;
import com.t3h.musicplayer.base.BaseActivity;
import com.t3h.musicplayer.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    @Override
    protected void initAct() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        },500);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
