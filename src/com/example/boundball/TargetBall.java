package com.example.boundball;

import javax.microedition.khronos.opengles.GL10;
import android.util.Log;

public class TargetBall {

	public Vector2D mBallPos; //���W
	public Vector2D mBallPower; //������
	public float mBallRadius; //���a
	
	public TargetBall (float x, float y, float px, float py, float radius) {
		this.mBallPos = new Vector2D(x,y);
		this.mBallPower = new Vector2D(px,py);
		this.mBallRadius = radius;
	}
	
	//�W�I���ړ������܂�
	public void move() {
		mBallPos.mX += mBallPower.mX;
		mBallPos.mY += mBallPower.mY;
		checkConflict();
	}
	public void checkConflict() {
		float distanceU = 1.5f - mBallPos.mY;
		if (distanceU <= mBallRadius) {
			if (mBallPower.mY > 0.0f) {
				mBallPower.mY = -mBallPower.mY;
			}
		}
		float distanceD = -(-1.5f - mBallPos.mY);
		if (distanceD <= mBallRadius) {
			if (mBallPower.mY < 0.0f) {
				mBallPower.mY = -mBallPower.mY;
			}
		}
		float distanceR = 1.0f - mBallPos.mX;
		if (distanceR <= mBallRadius) {
			if (mBallPower.mX > 0.0f) {
				mBallPower.mX = -mBallPower.mX;
			}
		}
		float distanceL = -(-1.0f - mBallPos.mX);
		if (distanceL <= mBallRadius) {
			if (mBallPower.mX < 0.0f) {
				mBallPower.mX = -mBallPower.mX;
			}
		}
	}
	//�|�C���g�������蔻��͈͓̔�����Ԃ��܂�
	public boolean isPointInside(float x, float y) {
		return false;
	}
	
	//�W�I��`�悵�܂�
	public void draw(GL10 gl) {
		GraphicUtil.drawCircle(gl, mBallPos.mX, mBallPos.mY, 16, mBallRadius, 1.0f, 1.0f, 1.0f, 1.0f);
	}
}
