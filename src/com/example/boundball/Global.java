package com.example.boundball;

import java.util.Random;
import javax.microedition.khronos.opengles.GL10;

public class Global {
	// MainActivity
	public static MainActivity mainActivity;

	//GL�R���e�L�X�g��ێ�����ϐ�
	public static GL10 gl;
	
	//�����_���Ȓl�𐶐�����
	public static Random rand = new Random(System.currentTimeMillis());
	
	//�f�o�b�N���[�h�ł��邩
	public static boolean isDebuggable;
}
