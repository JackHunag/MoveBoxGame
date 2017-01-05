package com.android.moveboxgame.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.moveboxgame.R;
import com.android.moveboxgame.activity.GameActivity;
import com.android.moveboxgame.utils.GameBitmaps;
import com.android.moveboxgame.utils.GameState;

public class GameView extends View {

	private float mCellWidth;// 单元格的宽度
	public static final int CELL_NUM_PER_LINE = 12;// 绘制布局的的线条数目
	private int mManRow = 7; // 搬运工当前所处的单元格的行号
	private int mManColumn = 5;// 搬运工当前所处的单元格的列号
	private Bitmap manBitmap = null;// 搬运工的图片
	private int mBoxRow =6; // 箱子一开始处在游戏区域中间位置
	private int mBoxColumn=5;
	private GameActivity mGameActivity;

	public GameView(Context context) {
		super(context);
		initManBitmap();
		mGameActivity = (GameActivity) context;
		GameBitmaps.loadGameBitmaps(getResources());
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initManBitmap();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initManBitmap();
	}

	private void initManBitmap() {
		GameBitmaps.loadGameBitmaps(getResources());// 加载图片。getResources()获取资源管理器对象
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// 计算每个单元格的宽度
		mCellWidth = w / CELL_NUM_PER_LINE;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 绘制游戏背景
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		// 绘制游戏区域
		Paint linePaint = new Paint();
		linePaint.setColor(Color.BLACK);

		for (int c = 0; c <= CELL_NUM_PER_LINE; c++) {
			// 绘制游戏区域的横线，
			canvas.drawLine(0, c * mCellWidth, getWidth(), c * mCellWidth,
					linePaint);
		}

		for (int r = 0; r <= CELL_NUM_PER_LINE; r++) {
			// 绘制游戏区域的竖线，
			canvas.drawLine(r * mCellWidth, 0, r * mCellWidth,
					CELL_NUM_PER_LINE * mCellWidth, linePaint);
		}

		// 绘制搬运工
		/*
		 * Rect srcRect = new Rect(0, 0, GameBitmaps.ManBitmap.getWidth(),
		 * GameBitmaps.ManBitmap.getHeight()); Rect destRect = getRect(mManRow,
		 * mManColumn); canvas.drawBitmap(GameBitmaps.ManBitmap, srcRect,
		 * destRect, null);
		 */

		// 根据游戏局面绘制游戏界面
		drawGameBoard(canvas);
	}

	private void drawGameBoard(Canvas canvas) {
		Rect srcRect;
		Rect destRect;

		GameState currentState = mGameActivity.getCurrentState();
		StringBuffer[] labelInCells = currentState.getLabelInCells();
		for (int r = 0; r < labelInCells.length; r++)
			// 对二维矩阵中的每一行
			for (int c = 0; c < labelInCells[r].length(); c++) { // 对一行中的每一列
				destRect = getRect(r, c); // 求出单元格(r, c)对应的屏幕区域
				switch (labelInCells[r].charAt(c)) {
				case 'W':
					srcRect = new Rect(0, 0, GameBitmaps.WallBitmap.getWidth(),
							GameBitmaps.WallBitmap.getHeight());
					canvas.drawBitmap(GameBitmaps.WallBitmap, srcRect,
							destRect, null);
					break;
				case 'B':
					
					if(mBoxColumn !=0&& mBoxRow!=0 ){
						destRect = getRect(mBoxRow,mBoxColumn );
					}
					srcRect = new Rect(0, 0, GameBitmaps.BoxBitmap.getWidth(),
							GameBitmaps.BoxBitmap.getHeight());
					canvas.drawBitmap(GameBitmaps.BoxBitmap, srcRect, destRect,
							null);
					   
					break;
				case 'F':
					srcRect = new Rect(0, 0, GameBitmaps.FlagBitmap.getWidth(),
							GameBitmaps.FlagBitmap.getHeight());
					canvas.drawBitmap(GameBitmaps.FlagBitmap, srcRect,
							destRect, null);
					break;
				case 'M':
					
					if(mManColumn!=0 && mManRow !=0){
						destRect = getRect(mManRow, mManColumn);
					}
					srcRect = new Rect(0, 0, GameBitmaps.ManBitmap.getWidth(),
							GameBitmaps.ManBitmap.getHeight());
					canvas.drawBitmap(GameBitmaps.ManBitmap, srcRect, destRect,
							null);
					
					break;

				// //搬运工 + 红旗
				case 'R':
					srcRect = new Rect(0, 0, GameBitmaps.FlagBitmap.getWidth(),
							GameBitmaps.FlagBitmap.getHeight());
					canvas.drawBitmap(GameBitmaps.FlagBitmap, srcRect,
							destRect, null);

					srcRect = new Rect(0, 0, GameBitmaps.ManBitmap.getWidth(),
							GameBitmaps.ManBitmap.getHeight());
					canvas.drawBitmap(GameBitmaps.ManBitmap, srcRect, destRect,
							null);

					break;

				// 箱子 + 红旗
				case 'X':
					srcRect = new Rect(0, 0, GameBitmaps.FlagBitmap.getWidth(),
							GameBitmaps.FlagBitmap.getHeight());
					canvas.drawBitmap(GameBitmaps.FlagBitmap, srcRect,
							destRect, null);

					srcRect = new Rect(0, 0, GameBitmaps.BoxBitmap.getWidth(),
							GameBitmaps.BoxBitmap.getHeight());
					canvas.drawBitmap(GameBitmaps.BoxBitmap, srcRect, destRect,
							null);
					break;
				}
			}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return true;
		}

		int touch_x = (int) event.getX(); // 触摸点的x坐标
		int touch_y = (int) event.getY(); // 触摸点的y坐标

		// 搬运工向下运动
		if (touch_blow_to_man(touch_x, touch_y, mManRow, mManColumn)) {

			handleDown();

		}
		// 搬运工向上运动
		if (touch_top_to_man(touch_x, touch_y, mManRow, mManColumn)) {
			handleUp();
		}
		// 搬运工向右运动
		if (touch_right_to_man(touch_x, touch_y, mManRow, mManColumn)) {
			handleRight();
		}
		// 搬运工向z左运动

		if (touch_left_to_man(touch_x, touch_y, mManRow, mManColumn)) {
			handleLeft();
		}

		/*
		 * 若搬运工下方有箱子，搬运工推着箱子向下移动； 若搬运工下方没有箱子，搬运工向下走一步。
		 */

		postInvalidate();
		return true;
	}

	/**
	 * 实现搬运工和箱子向下移动的功能方法
	 */
	private void handleDown() {

		if (isBoxBlowMan()) {
			if (mBoxRow + 1 < CELL_NUM_PER_LINE) {
				mBoxRow++;
				mManRow++;
			}
		} else if (mManRow + 1 < CELL_NUM_PER_LINE)
			mManRow++;

	}

	private void handleUp() {

		if (isBoxUpMan()) {
			if (mBoxRow - 1 > 0) {
				mBoxRow--;
				mManRow--;
			}
		} else if (mManRow - 1 > 0) {
			mManRow--;
		}

	}

	private void handleLeft() {

		if (isBoxLeftMan()) {
			if (mBoxColumn - 1 > 0) {
				mBoxColumn--;
				mManColumn--;
			}
		} else if (mManColumn - 1 > 0)
			mManColumn--;

	}

	private void handleRight() {

		if (isBoxRightMan()) {
			if (mBoxColumn + 1 < CELL_NUM_PER_LINE) {
				mBoxColumn++;
				mManColumn++;
			}
		} else if (mManColumn + 1 < CELL_NUM_PER_LINE)
			mManColumn++;

	}

	/**
	 * 搬运工是否落在搬运工的下方单元格
	 * 
	 * @param touch_x
	 *            触摸点的x坐标
	 * @param touch_y
	 *            触摸点的y坐标
	 * @param mManRow
	 *            搬运工当前所处的单元格的列号
	 * @param mManColumn
	 *            搬运工当前所处的单元格的行号
	 * @return 移动后的位置
	 */
	private boolean touch_blow_to_man(int touch_x, int touch_y, int mManRow,
			int mManColumn) {

		int belowRow = mManRow + 1;
		Rect belowRect = getRect(belowRow, mManColumn);
		return belowRect.contains(touch_x, touch_y);
	}

	/**
	 * 搬运工是否落在搬运工的上方单元格
	 * 
	 * @param touch_x
	 *            触摸点的x坐标
	 * @param touch_y
	 *            触摸点的y坐标
	 * @param mManRow
	 *            搬运工当前所处的单元格的列号
	 * @param mManColumn
	 *            搬运工当前所处的单元格的行号
	 * @return 移动后的位置
	 */
	private boolean touch_top_to_man(int touch_x, int touch_y, int mManRow,
			int mManColumn) {

		int belowRow = mManRow - 1;
		Rect belowRect = getRect(belowRow, mManColumn);
		return belowRect.contains(touch_x, touch_y);
	}

	/**
	 * 获取单元格的矩形区域
	 * 
	 * @param row
	 *            网格的列
	 * @param column
	 *            网格的行
	 * @return 返回单元格的矩形区域大小
	 */
	private Rect getRect(int row, int column) {
		int left = (int) (column * mCellWidth);
		int top = (int) (row * mCellWidth);
		int right = (int) ((column + 1) * mCellWidth);
		int bottom = (int) ((row + 1) * mCellWidth);
		return new Rect(left, top, right, bottom);

	}

	/**
	 * 搬运工向右走动功能方法
	 * 
	 * @param touch_x
	 *            触摸点的x坐标
	 * @param touch_y
	 *            触摸点的y坐标
	 * @param mManRow
	 *            搬运工当前所处的单元格的列号
	 * @param mManColumn
	 *            搬运工当前所处的单元格的行号
	 * @return 移动后的位置
	 */
	private boolean touch_right_to_man(int touch_x, int touch_y, int manRow,
			int manColumn) {

		int rightColumn = manColumn + 1; // 右侧单元格列号
		Rect rightRect = getRect(manRow, rightColumn); // 求右侧单元格的矩形区域
		return rightRect.contains(touch_x, touch_y); // 落在右侧单元格内吗？
	}

	/**
	 * 搬运工向左走动功能方法
	 * 
	 * @param touch_x
	 *            触摸点的x坐标
	 * @param touch_y
	 *            触摸点的y坐标
	 * @param mManRow
	 *            搬运工当前所处的单元格的列号
	 * @param mManColumn
	 *            搬运工当前所处的单元格的行号
	 * @return 移动后的位置
	 */
	private boolean touch_left_to_man(int touch_x, int touch_y, int manRow,
			int manColumn) {

		int rightColumn = manColumn - 1; // 右侧单元格列号
		Rect rightRect = getRect(manRow, rightColumn); // 求右侧单元格的矩形区域
		return rightRect.contains(touch_x, touch_y); // 落在右侧单元格内吗？

	}

	/**
	 * 判断箱子是否在搬运工下方
	 * 
	 * @return
	 */
	private boolean isBoxBlowMan() {
		return mBoxColumn == mManColumn && mBoxRow == mManRow + 1;
	}

	/**
	 * 判断箱子是否在搬运工上方
	 * 
	 * @return
	 */
	private boolean isBoxUpMan() {
		return mBoxColumn == mManColumn && mBoxRow == mManRow - 1;
	}

	/**
	 * 判断箱子是否在搬运工左方
	 * 
	 * @return
	 */
	private boolean isBoxLeftMan() {
		return mBoxColumn == mManColumn - 1 && mBoxRow == mManRow;
	}

	/**
	 * 判断箱子是否在搬运工左方
	 * 
	 * @return
	 */
	private boolean isBoxRightMan() {
		return mBoxColumn == mManColumn + 1 && mBoxRow == mManRow;
	}
}
