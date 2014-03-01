package com.example.boundball;
import java.util.Random;

import android.util.Log;
public class MyGameThread extends Thread {
	private boolean mIsActive;
	public static final int TARGET_NUM = 10;//円の数
	//標的
	public TargetBall[] mBall = new TargetBall[TARGET_NUM];
	public ParticleSystem mParticleSystem;
	
	public MyGameThread() {
		Random rand = Global.rand;
		for (int i = 0; i < TARGET_NUM; i++) {
			float x = rand.nextFloat() * 2.0f - 1.0f;
			float y = rand.nextFloat() * 3.0f - 1.5f;
			mBall[i] = new TargetBall(x, y, 0.02f, 0.02f,0.05f);
		}
	}
	private void update() {
		synchronized (this) {
			for (int i = 0; i < TARGET_NUM; i++) {
				mBall[i].move();
			}
		}
	}
	@Override
	public void run() {
		long lastUpdateTime = System.currentTimeMillis();
		while (true) {
			// 休憩
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!mIsActive) {
				lastUpdateTime = System.currentTimeMillis();// 復帰時に更新処理が複数回行われないようにする
				continue;
			}
			// 1秒間に60回更新する
			long nowTime = System.currentTimeMillis();
			long difference = nowTime - lastUpdateTime;
			while (difference >= 17) {
				difference -= 17;
				update();
			}
			lastUpdateTime = nowTime - difference;
		}
	}

	public void resumeGameThread() {
		mIsActive = true;
	}

	public void pauseGameThread() {
		mIsActive = false;
	}
}
