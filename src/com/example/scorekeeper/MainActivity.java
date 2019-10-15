package com.example.scorekeeper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener {

	
	Button btn_startMatch, btn_home_color, btn_away_color;
	Spinner spin_preset;
	EditText home_team_name, away_team_name;
	NumberPicker strikesOut, foulsOut, ballsWalk, outsInning, inningsGame;
	int max_innings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		home_team_name = (EditText) findViewById(R.id.home_team_name);
		away_team_name = (EditText) findViewById(R.id.away_team_name);
		btn_startMatch = (Button) findViewById(R.id.goto_refmatch);
		btn_home_color = (Button) findViewById(R.id.btn_home_color);
		btn_away_color = (Button) findViewById(R.id.btn_away_color);
		spin_preset = (Spinner) findViewById(R.id.presetChoice);
		strikesOut = (NumberPicker) findViewById(R.id.strikesOut);
		foulsOut = (NumberPicker) findViewById(R.id.foulsOut);
		ballsWalk = (NumberPicker) findViewById(R.id.ballsWalk);
		outsInning = (NumberPicker) findViewById(R.id.outsHalf);
		inningsGame = (NumberPicker) findViewById(R.id.inningsGame);
		final String[] innings = {"WAKA", "Lonestar", "6", "7", "8" ,"9"};
		ArrayAdapter<String> adpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, innings);
		adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin_preset.setAdapter(adpt);
		spin_preset.setOnItemSelectedListener(this);
		setupDefaults();
		
		
		btn_startMatch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
				Class gameRefClass = Class.forName("com.example.scorekeeper.GameRefView");
				Intent refMatch = new Intent(MainActivity.this, gameRefClass);
				Bundle options = new Bundle();
				options.putInt("maxInnings", inningsGame.getValue());
				options.putInt("strikes", strikesOut.getValue());
				options.putInt("fouls", foulsOut.getValue());
				options.putInt("balls", ballsWalk.getValue());
				options.putInt("outs", outsInning.getValue());
				options.putString("home_team_name", home_team_name.getText().toString());
				options.putString("away_team_name", away_team_name.getText().toString());
				refMatch.putExtras(options);
				startActivity(refMatch);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		btn_home_color.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn_away_color.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
	public void setupDefaults() {
		strikesOut.setMaxValue(5);
		strikesOut.setMinValue(1);
		strikesOut.setValue(3);
		
		foulsOut.setMaxValue(10);
		foulsOut.setMinValue(1);
		foulsOut.setValue(4);
		
		ballsWalk.setMaxValue(6);
		ballsWalk.setMinValue(1);
		ballsWalk.setValue(4);
		
		outsInning.setMaxValue(5);
		outsInning.setMinValue(1);
		outsInning.setValue(3);
		
		inningsGame.setMaxValue(9);
		inningsGame.setMinValue(1);
		inningsGame.setValue(5);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void onPause() {
		super.onPause();
		finish();
	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
		System.out.println(spin_preset.getSelectedItem().toString());
		//max_innings = spin_preset.getSelectedItem().toString();
		//switch(spin_preset.getSelectedItemId()) {
		
		//}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
