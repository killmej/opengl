package com.example.boundball;
import java.util.Random;

import android.util.Log;
public class MyGameThread extends Thread {
	private boolean mIsActive;
	public static final int TARGET_NUM = 10;//�~�̐�
	//�W�I
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
			// �x�e
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!mIsActive) {
				lastUpdateTime = System.currentTimeMillis();// ���A���ɍX�V������������s���Ȃ��悤�ɂ���
				continue;
			}
			// 1�b�Ԃ�60��X�V����
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
