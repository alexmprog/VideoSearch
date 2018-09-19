package com.renovavision.videosearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.vyng.videosearch.R;
import com.renovavision.videosearch.video_grid.VideoGridView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class RouterActivity extends AppCompatActivity {

    @BindView(R.id.controller_container)
    protected FrameLayout container;

    private Router router;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);
        ButterKnife.bind(this);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new VideoGridView()));
        }

    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }
}
