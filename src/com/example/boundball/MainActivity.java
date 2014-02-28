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
        // �C���X�^���X��ێ�������
		Global.mainActivity = this;
 
		// �Q�[���X���b�h�̐���
		this.mGameThread = new MyGameThread();
        
		// �t���X�N���[���A�^�C�g���o�[�̔�\��
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		MyRenderer renderer = new MyRenderer(this, mGameThread);
		MyGLSurfaceView glSurfaceView = new MyGLSurfaceView(this, mGameThread);
		glSurfaceView.setRenderer(renderer);
		setContentView(glSurfaceView);

		// �Q�[���X���b�h�̊J�n
		mGameThread.start();
    }
    
    @Override
    public void onResume() { 
    	super.onResume();
    	mGameThread.resumeGameThread();// �Q�[���𕜋A����
    }
     
    @Override
    public void onPause() {
    	super.onPause();
    	mGameThread.pauseGameThread();// �Q�[�����~����
    	
    	TextureManager.deleteAll(Global.gl);
    }
}
