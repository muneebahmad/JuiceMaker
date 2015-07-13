/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muneebahmad.microsun.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Director.IDirectorLifecycleListener;
import com.wiyun.engine.opengl.WYGLSurfaceView;

/**
 *
 * @author muneebahmad
 */
public abstract class MicroSunSupportBaseActivity 
             extends Activity implements IDirectorLifecycleListener {
    
    /**
     *
     */
    protected WYGLSurfaceView mGLSurfaceView;
    private PowerManager.WakeLock wakeLock;
    
    static {
                System.loadLibrary("wiskia");
		System.loadLibrary("xml2");
		System.loadLibrary("wiengine");
		System.loadLibrary("wiengine_binding");
		System.loadLibrary("lua");
		System.loadLibrary("chipmunk");
		System.loadLibrary("box2d");
		System.loadLibrary("wisound");
    }
    
    /**
     *
     * @return
     */
    protected String checkPrecondition() {
        return null;
    }
    
    /**
     *
     * @return
     */
    protected boolean isTransparent() {
        return false;
    }
    
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.mGLSurfaceView = new WYGLSurfaceView(this, isTransparent());
        setContentView(this.mGLSurfaceView);
        Director.getInstance().addLifecycleListener(this);
    }
    
    /**
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Director.getInstance().end();
    }
    
    /**
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        Director.getInstance().pause();
        this.wakeLock.release();
    }
    
    /**
     *
     */
    @Override
    protected void onResume() {
        Director.getInstance().resume();
        super.onResume();
        this.wakeLock = ((PowerManager) getSystemService("power")).
                newWakeLock(PowerManager.FULL_WAKE_LOCK, 
                "MicroSunSupportBaseActivity");
        this.wakeLock.acquire();
    }
    
    /**
     *
     */
    @Override
    public void onSurfaceCreated() {
        
    }    
    
    /**
     *
     * @param scale
     * @param width
     * @param height
     */
    protected void setBaseSize(int scale, int width, int height) {
        Director.getInstance().setScaleMode(scale);
        Director.getInstance().setBaseSize(width, height);
    }
}//end class
