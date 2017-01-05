package com.android.moveboxgame.activity;

import com.android.moveboxgame.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_brief;
	private Button btn_close;
	private Button btn_start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();
	}

	private void initUI() {

		btn_brief = (Button) findViewById(R.id.btn_brief);
		btn_close = (Button) findViewById(R.id.btn_close);
		btn_start = (Button) findViewById(R.id.btn_start);

		btn_brief.setOnClickListener(this);
		btn_close.setOnClickListener(this);
		btn_start.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_start:
			startActivity(new Intent(getApplicationContext(), GameLevelActivity.class));
			break;
		case R.id.btn_brief:          
         
			startActivity(new Intent(getApplicationContext(), GameIntroActivity.class));
			
			break;

		case R.id.btn_close:

			 finish();
			break;

		default:
			break;
		}

	}
  
	@Override
    protected void onDestroy() {
	
	super.onDestroy();
}
}
