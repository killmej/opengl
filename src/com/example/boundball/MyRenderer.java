package com.example.boundball;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;

public class MyRenderer implements GLSurfaceView.Renderer {

	// コンテキスト
	private Context mContext;
	private int mWidth;
	private int mHeight;
	private int mOffsetX;
	private int mOffsetY;

	private MyGameThread mGameThread;

	public MyRenderer(Context context, MyGameThread gameThread) {
		this.mContext = context;
		this.mGameThread = gameThread;
	}

	public void renderMain(GL10 gl) {
		MyGameThread gameThread = mGameThread;
		synchronized (gameThread) {
			int target = gameThread.mBall.length;
			for (int i = 0; i < target; i++) {
				gameThread.mBall[i].draw(gl);
			}
		}
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glViewport(mOffsetX, mOffsetY, mWidth, mHeight);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(-1.0f, 1.0f, -1.5f, 1.5f, 0.5f, -0.5f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderMain(gl);
	}

	private void loadTextures(GL10 gl) {
		Resources res = mContext.getResources();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// 常に3:2で描画する
		int w = 0;
		int h = 0;
		while (w < width && h < height) {
			w += 2;
			h += 3;
		}
		this.mWidth = w;
		this.mHeight = h;
		this.mOffsetX = (width - w) / 2;
		this.mOffsetY = (height - h) / 2;

		Global.gl = gl;// GLコンテキストを保持する
		// テクスチャをロードする
		loadTextures(gl);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	}

}
