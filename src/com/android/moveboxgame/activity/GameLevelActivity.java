package com.android.moveboxgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.android.moveboxgame.R;
import com.android.moveboxgame.utils.GameLevels;

public class GameLevelActivity extends Activity {

	private String[] levelLists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_level);
		
		GridView gv_levels = (GridView) findViewById(R.id.gv_levels);
		        
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
        		R.layout.simple_list_item_,
        		GameLevels.getLevelList());
       
         gv_levels.setAdapter(arrayAdapter);
       
         gv_levels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GameLevelActivity.this, GameActivity.class);
                intent.putExtra(GameActivity.KEY_SELECTED_LEVEL, i + 1);
                startActivity(intent);
            }
        });

	}
}
