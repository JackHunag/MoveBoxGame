package com.android.moveboxgame.activity;

import android.app.Activity;
import android.os.Bundle;

import com.android.moveboxgame.utils.GameLevels;
import com.android.moveboxgame.utils.GameState;
import com.android.moveboxgame.view.GameView;

public class GameActivity extends Activity {

	public static final String KEY_SELECTED_LEVEL = "Selected_Level";
    private GameState mCurrentState;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int selected_level = getIntent().getIntExtra(KEY_SELECTED_LEVEL, 1);
				
		 //通过构造方法获取相应关卡数
		String[] level = GameLevels.getLevel(selected_level);
		mCurrentState = new GameState(level);
		GameView gameView = new GameView(this);
		setContentView(gameView);
		

		
	}
   
	
	//获取关卡状态
	public GameState getCurrentState(){
        return mCurrentState;
    }


}
