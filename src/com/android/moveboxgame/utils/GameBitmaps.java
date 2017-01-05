package com.android.moveboxgame.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.android.moveboxgame.R;

/**
 * 加载和管理图片资源的辅助类
 * @author hsssf
 * 2016-11-15
 */
public class GameBitmaps {
	
	public static  Bitmap WallBitmap ;
	public static Bitmap BoxBitmap ;
	public static  Bitmap FlagBitmap;
	public static Bitmap ManBitmap ; // 需要为每一幅图片安排一个static变量

	public static void loadGameBitmaps(Resources res) {
		if (ManBitmap == null) // 如果为null加载图片；否则说明已经加载过了。
			ManBitmap = BitmapFactory.decodeResource(res,
					R.drawable.eggman_48x48);
		
		if (BoxBitmap == null) // 如果为null加载图片；否则说明已经加载过了。
			BoxBitmap = BitmapFactory.decodeResource(res,
					R.drawable.boxbitmap);
		

		if (FlagBitmap == null) // 如果为null加载图片；否则说明已经加载过了。
			FlagBitmap = BitmapFactory.decodeResource(res,
					R.drawable.flagbitmap);
		

		if (WallBitmap == null) // 如果为null加载图片；否则说明已经加载过了。
			WallBitmap = BitmapFactory.decodeResource(res,
					R.drawable.wallbitmap);
	}
  
	 
	// 释放图片对象占据的内存
	public static void releaseGameBitmaps() {
		if (ManBitmap != null) {
			ManBitmap.recycle();
			ManBitmap = null;
		}
	}
}