package com.example.boundball;

import javax.microedition.khronos.opengles.GL10;
import android.util.Log;

public class TargetBall {

	public Vector2D mBallPos; //座標
	public Vector2D mBallPower; //慣性力
	public float mBallRadius; //半径
	
	public TargetBall (float x, float y, float px, float py, float radius) {
		this.mBallPos = new Vector2D(x,y);
		this.mBallPower = new Vector2D(px,py);
		this.mBallRadius = radius;
	}
	
	//標的を移動させます
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
	//ポイントが当たり判定の範囲内かを返します
	public boolean isPointInside(float x, float y) {
		return false;
	}
	
	//標的を描画します
	public void draw(GL10 gl) {
		GraphicUtil.drawCircle(gl, mBallPos.mX, mBallPos.mY, 16, mBallRadius, 1.0f, 1.0f, 1.0f, 1.0f);
	}
}
