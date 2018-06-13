package com.android.ram.burdd;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class StepRecorder extends AccessibilityService {
    FrameLayout frameLayout;
    public StepRecorder() {
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        WindowManager windowManager=(WindowManager) getSystemService(WINDOW_SERVICE);
        frameLayout=new FrameLayout(this);
        WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
        layoutParams.type=WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        layoutParams.format= PixelFormat.TRANSLUCENT;
        layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width=WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity= Gravity.TOP;
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        layoutInflater.inflate(R.layout.accessibility_action_bar,frameLayout);
        windowManager.addView(frameLayout,layoutParams);

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED|AccessibilityEvent.TYPE_VIEW_LONG_CLICKED | AccessibilityEvent.TYPE_VIEW_FOCUSED
                |AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED| AccessibilityEvent.TYPE_WINDOWS_CHANGED|AccessibilityEvent.TYPE_VIEW_FOCUSED|AccessibilityEvent.TYPE_ANNOUNCEMENT;
        //info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        this.setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Toast.makeText(StepRecorder.this,"here :"+accessibilityEvent.getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInterrupt() {

    }
    private void configureExitButton(){
        Button exitButton=(Button)frameLayout.findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableSelf();
            }
        });

    }
    private void configureStartButton(){
        ToggleButton startButton=(ToggleButton)frameLayout.findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
