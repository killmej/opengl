package com.example.boundball;
import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private MyGameThread mGameThread;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // インスタンスを保持させる
		Global.mainActivity = this;
 
		// ゲームスレッドの生成
		this.mGameThread = new MyGameThread();
        
		// フルスクリーン、タイトルバーの非表示
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		MyRenderer renderer = new MyRenderer(this, mGameThread);
		MyGLSurfaceView glSurfaceView = new MyGLSurfaceView(this, mGameThread);
		glSurfaceView.setRenderer(renderer);
		setContentView(glSurfaceView);

		// ゲームスレッドの開始
		mGameThread.start();
    }
    
    @Override
    public void onResume() { 
    	super.onResume();
    	mGameThread.resumeGameThread();// ゲームを復帰する
    }
     
    @Override
    public void onPause() {
    	super.onPause();
    	mGameThread.pauseGameThread();// ゲームを停止する
    	
    	TextureManager.deleteAll(Global.gl);
    }
}
