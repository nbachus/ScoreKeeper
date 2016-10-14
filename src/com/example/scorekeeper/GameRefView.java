package com.example.scorekeeper;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;


public class GameRefView extends Activity{

	Button btn_homeTeam, btn_awayTeam, btn_outs, btn_strike, btn_foul, btn_ball, btn_safe, btn_undo;
	Button btn_timer_start, btn_timer_reset, btn_submit;
	TextView txt_inning;
	Chronometer game_timer;
	Bundle options;
	Team home_team, away_team;
	Game cur_game;
	Game[] archive_game;
	LinkedList<Game> archive_games;
	LinkedList<Team> archive_team_home, archive_team_away;
	Team[][] archive_team;
	boolean b_start_not_pause;
	long timeWhenStopped = 0;
	String[] game_summary;
	int archive_i;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refmatch);
		cur_game = new Game();
		home_team = new Team();
		away_team = new Team();
		archive_game = new Game[100];
		archive_team = new Team[100][2];
		archive_games = new LinkedList<Game>();
		archive_team_home = new LinkedList<Team>();
		archive_team_away = new LinkedList<Team>();
		
		archive_i = 0;
		cur_game.addTeamToGame(home_team);
		cur_game.addTeamToGame(away_team);
		txt_inning = (TextView) findViewById(R.id.show_inning);
		btn_homeTeam = (Button) findViewById(R.id.btn_refHome);
		btn_awayTeam = (Button) findViewById(R.id.btn_refAway);
		btn_strike = (Button) findViewById(R.id.btn_strike);
		btn_foul = (Button) findViewById(R.id.btn_foul);
		btn_ball = (Button) findViewById(R.id.btn_ball);
		btn_outs = (Button) findViewById(R.id.btn_outs);
		btn_safe = (Button) findViewById(R.id.btn_safe);
		btn_undo = (Button) findViewById(R.id.btn_undo);
		game_timer = (Chronometer) findViewById(R.id.timer_game);
		btn_timer_start = (Button) findViewById(R.id.btn_timer_start);
		btn_timer_reset = (Button) findViewById(R.id.btn_timer_reset);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		options = getIntent().getExtras();
		b_start_not_pause = true;
		btn_timer_start.setText("Start Timer");
		setupGame();
		game_summary = new String[options.getInt("maxInnings") + 10];
		//archive_game[archive_i]= (Game) cur_game.clone();
		try {
		//archive_games.add((Game) cur_game.clone());
		//archive_team_home.add((Team) home_team.clone());
		//archive_team_away.add((Team) away_team.clone());
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
		}
		updateUI(true);
		//All initializations should be above here
		
		
		btn_homeTeam.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				home_team.teamScored();
				//btn_homeTeam.setText(home_team.getTeamName() + "\n" + home_team.getTeamScore());
				updateUI(true);
			}
		});
		
		btn_awayTeam.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				away_team.teamScored();
				//btn_awayTeam.setText(away_team.getTeamName() + "\n" + away_team.getTeamScore());
				updateUI(true);
			}
		});
		
		btn_outs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cur_game.batterOut();
				//btn_outs.setText(getString(R.string.str_outs) + "\n" + cur_game.getOutCount());
				//txt_inning.setText("Inning " + cur_game.getInningInfo());
				updateUI(true);
			}
		});
		
		btn_strike.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cur_game.batterStrike();
				updateUI(true);
				
			}
		});
		
		btn_foul.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cur_game.batterFoul();
				updateUI(true);
				
			}
		});

		btn_ball.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				cur_game.batterBall();
				updateUI(true);
				
			}
		});
		
		btn_safe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cur_game.batterSafe();
				updateUI(true);
				
			}
		});
		
		btn_undo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				archive_i--;
				//cur_game = archive_game[archive_i].clone();
				try {
				archive_games.removeLast();
				archive_team_home.removeLast();
				archive_team_away.removeLast();	
				cur_game = (Game) archive_games.getLast().clone();
				home_team = (Team) archive_team_home.getLast().clone();
				away_team = (Team) archive_team_away.getLast().clone();
			
				
				} catch (Exception e) {
					System.out.println("Exception: " + e.toString());
				}
				cur_game.printGame();
				updateUI(false);
			}
		});
		btn_timer_start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        	
		        	runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        	try{
                        		if (b_start_not_pause) {
                        			game_timer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                        			game_timer.start();
                        			btn_timer_start.setText("Pause");
                        			b_start_not_pause = false;
                        		} else {
                        			timeWhenStopped = game_timer.getBase() - SystemClock.elapsedRealtime();
                        			game_timer.stop();
                        			btn_timer_start.setText("Start");
                        			b_start_not_pause = true;
                        			
                        		}
				        	} catch (Exception e) {
				        	  System.out.println("Exception: " + e.toString());
				        	}
                        }
                    });
		        }
				
			}
		);
		
		btn_timer_reset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				game_timer.setBase(SystemClock.elapsedRealtime());
				timeWhenStopped = 0;
				game_timer.stop();
				b_start_not_pause = true;
				btn_timer_start.setText("Start Timer");
			}
		});
		
		btn_submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendResults();
			}
		});
	
	
	}
	
	protected void updateUI(boolean archive_it) {
		
		if (!txt_inning.getText().equals("Inning " + cur_game.getInningInfo())) {
			game_summary[cur_game.getInning() - 1] =  game_timer.getText() + " " + cur_game.getInningInfo() + ":  " + away_team.getTeamName() + ": " + away_team.getTeamScore() + " - " + home_team.getTeamName() + ": " + home_team.getTeamScore() + "\n";
		}
		btn_homeTeam.setText(home_team.getTeamName() + "\n" + home_team.getTeamScore());
		btn_awayTeam.setText(away_team.getTeamName() + "\n" + away_team.getTeamScore());
		btn_outs.setText(getString(R.string.str_outs) + "\n" + cur_game.getOutCount());
		txt_inning.setText("Inning " + cur_game.getInningInfo());
		btn_strike.setText("Strikes\n" + cur_game.getStrikeCount());
		btn_foul.setText("Fouls\n" + cur_game.getFoulCount());
		btn_ball.setText("Balls\n" + cur_game.getBallCount());
		if (archive_it) {
			try {
			archive_i++;
			//archive_game[archive_i]= (Game) cur_game.clone();
			//archive_team[archive_i][0] = (Team) home_team.clone();
			//archive_team[archive_i][1] = (Team) away_team.clone();
			archive_games.add((Game) cur_game.clone());
			archive_team_home.add((Team) home_team.clone());
			archive_team_away.add((Team) away_team.clone());

			} catch (Exception e) {
				
			}
			try {
				//archive_game[archive_i].printGame();
				//archive_games.getLast().printGame();
			} catch (Exception e) {
				
			}
			
		}
		
		if (archive_games.size() > 1)  {
			btn_undo.setEnabled(true);
		}else {
			btn_undo.setEnabled(false);
		}
		
	}
	
	protected void setupGame() {
		home_team.newTeam(options.getString("home_team_name"), true);
		away_team.newTeam(options.getString("away_team_name"), false);
		//cur_game.setMaxInnings(options.getInt("maxInnings"));
		cur_game.setMaxValues(options.getInt("maxInnings"), 
				options.getInt("outs"), 
				options.getInt("strikes"), 
				options.getInt("fouls"), 
				options.getInt("balls"));
	}
	
	protected void sendResults() {
		String str_game_summary = "";
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
	            "mailto","abc@gmail.com", null));
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
		for (int i = 0; i < cur_game.getInning(); i++) {
			if (game_summary[i] != null) {
			str_game_summary = str_game_summary + game_summary[i];
			}
		}
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, str_game_summary);
		System.out.println(str_game_summary);
		startActivity(Intent.createChooser(emailIntent, "Send email..."));
	}

	
}
