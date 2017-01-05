package com.android.moveboxgame.utils;

/**
 * 存储游戏局面的类
 * 
 * @author hsssf 2016-11-29
 */
public class GameState {
	
	private int mManRow; // 记住搬运工所在单元格的行号
	private int mManColumn; // 记住搬运工所在单元格的列号

	private static StringBuffer[] mLabelInCells; // 用StringBuffer数组实现表示局面的二维矩阵。

	// mLabelInCells是数组名字。数组中的一个元素对应二维矩阵的一行。
	
	public GameState(String[] initialState) {
		mLabelInCells = new StringBuffer[initialState.length]; // 为mLabelInCells数组分配存储空间
		for (int i = 0; i < initialState.length; i++)
		 {
			// 对于mLabelInCells数组的元素i，
			mLabelInCells[i]  =  new StringBuffer(initialState[i]); // 为mLabelInCells数组的元素i分配存储空间
		// 并且，用开局的第i行（i从0开始）来初始化mLabelInCells数组的元素i
		}
	}

	// getLabelInCells返回表示局面的二维矩阵
	public static StringBuffer[] getLabelInCells() {
		return mLabelInCells; // mLabelInCells数组的元素i对应矩阵的第i行（i从0开始）。
	}
   
}
