package com.example.boundball;

import java.util.Hashtable;
import java.util.Map;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Button;

public class MyGLSurfaceView extends GLSurfaceView {	 
	private MyGameThread mGameThread; 
	public MyGLSurfaceView(Context context, MyGameThread gameThread) {
		super(context);
		this.mGameThread = gameThread;
		setFocusable(true);// �^�b�`�C�x���g���󂯎���悤�ɂ���
	}
	// �擾�����C�x���g��ID���L�����Ă���
	private Map<Integer, Button> mEventIdMemory = new Hashtable<Integer, Button>();
 
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}
}
