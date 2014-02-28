package com.example.boundball;

public class MyGameThread extends Thread {
	private boolean mIsActive;
	public Vector2D mBallPos; //���W
	public Vector2D mBallPower; //������
	public float mBallRadius; //���a
	
	public ParticleSystem mParticleSystem;
	
	public MyGameThread() {
		this.mBallPos = new Vector2D();
		this.mBallPower = new Vector2D(0.1f,0.1f);
		this.mBallRadius = 0.2f;
	}

	private void update() {
		Vector2D ballPos = mBallPos;
		Vector2D ballPower = mBallPower;
		float ballRadius = mBallRadius;
		
		mBallPos.mX += mBallPower.mX;
		mBallPos.mY += mBallPower.mY;
		
		float distanceU = 1.5f - ballPos.mY;
		if (distanceU <= ballRadius) {
			if (ballPower.mY > 0.0f) {
				ballPower.mY = -ballPower.mY;
			}
		}
		float distanceD = -(-1.5f - ballPos.mY);
		if (distanceD <= ballRadius) {
			if (ballPower.mY < 0.0f) {
				ballPower.mY = -ballPower.mY;
			}
		}
		float distanceR = 1.0f - ballPos.mX;
		if (distanceR <= ballRadius) {
			if (ballPower.mX > 0.0f) {
				ballPower.mX = -ballPower.mX;
			}
		}
		float distanceL = -(-1.0f - ballPos.mX);
		if (distanceL <= ballRadius) {
			if (ballPower.mX < 0.0f) {
				ballPower.mX = -ballPower.mX;
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
