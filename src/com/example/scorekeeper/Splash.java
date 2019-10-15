package com.example.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Thread timer = new Thread(){
			
			public void run() {
				try {
					sleep(5000);
					
				} catch (Exception e) {
					
				}
				finally {
					Intent mainMenu = new Intent("com.example.scorekeeper.MAINACTIVITY");
					startActivity(mainMenu);
				}
			}
			
			
			
		};
		timer.start();
		
	}
	
	protected void onPause() {
		super.onPause();
		finish();
		
	}
	
	

}
