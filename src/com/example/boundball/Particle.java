package com.example.boundball;

import javax.microedition.khronos.opengles.GL10;

public class Particle {
	public float mX;
	public float mY;
	public float mSize;
	
	public float mMoveX;//1�t���[���������X�������̈ړ���
	public float mMoveY;//1�t���[���������Y�������̈ړ���
	
	public boolean mIsActive;
	
	public int mFrameNumber;//��������̎���(�t���[����)
	public int mLifeSpan;//����(�t���[����)
 
	public Particle() {
		this.mX = 0.0f;
		this.mY = 0.0f;
		this.mSize = 1.0f;
		this.mIsActive = false;
		this.mMoveX = 0.0f;
		this.mMoveY = 0.0f;
		this.mFrameNumber = 0;
		this.mLifeSpan = 60;//������Ԃł͎�����60�t���[���ɂ���
	}

	public void draw(GL10 gl, int texture) {
		//���݂̃t���[�����A�����̊Ԃ̂ǂ̈ʒu�ɂ���̂����v�Z����
		float lifePercentage = (float)mFrameNumber / (float)mLifeSpan;
		float alpha = 1.0f;
		if (lifePercentage <= 0.5f) {//�����������ȏ�c���Ă���ꍇ
			alpha = lifePercentage * 2.0f;
		} else {
			alpha = 1.0f - (lifePercentage - 0.5f) * 2.0f;
		}
		GraphicUtil.drawTexture(gl, mX, mY, mSize, mSize, texture, 1.0f, 1.0f, 1.0f, alpha);
	}

	public void update() {
		mFrameNumber++;//�t���[�����J�E���g����
		if (mFrameNumber >= mLifeSpan) mIsActive = false;//�����ɒB���Ă������A�N�e�B�u�ɂ���
		mX += mMoveX;
		mY += mMoveY;
	}

}
