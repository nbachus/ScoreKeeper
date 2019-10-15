package com.example.scorekeeper;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class Game implements Cloneable, Serializable{
	
	private int inning, outs, strikes, balls, fouls;
	private int max_inning, max_outs, max_strikes, max_fouls, max_balls;
	private boolean b_top_inning;
	private String str_home_team, str_away_team;
	private Team home_team, away_team;
	
	public Game() {
		inning = 1;
		outs = 0;
		strikes = 0;
		balls = 0;
		fouls = 0;
		max_inning = 9;
		b_top_inning = true;
		
	}
	
	public void setMaxValues(int inning, int out, int strike, int foul, int ball) {
		max_inning = inning;
		max_outs = out;
		max_strikes = strike;
		max_fouls = foul;
		max_balls = ball;
	}
	
	public int getScore(Team team) {
		if (team.isHomeTeam()) {
			return home_team.getTeamScore();
		}else {
			return away_team.getTeamScore();
		}
	}
	
	public void addTeamToGame(Team team) {
		if (team.isHomeTeam()) {
			home_team = team;
		}else {
			away_team = team;
		}
	}
	
	public void setMaxInnings(int i) {
		max_inning = i;
	}
	
	
	
	public void nextInningHalf() {
		if (b_top_inning) {
			b_top_inning = false;
			outs = 0;
			strikes = 0;
			balls = 0;
			fouls = 0;
		} else {
			b_top_inning = true;
			inning++;
			outs = 0;
			strikes = 0;
			balls = 0;
			fouls = 0;
			
		}
		
	}
	
	public void batterSafe() {
		strikes = 0;
		balls = 0;
		fouls = 0;
		
	}
	
	public void batterOut() {
		outs++;
		strikes = 0;
		balls = 0;
		fouls = 0;
		if (outs >= max_outs) {
			nextInningHalf();
		}
			}
	
	public void batterStrike() {
		if (!(strikes >= max_strikes - 1)) {
			strikes++;
		} else {
			batterOut();
		}
	}
	
	public void batterBall() {
		if (balls < max_balls - 1) {
			balls++;
		}else {
			batterSafe();
		}
		
	}
	public void batterFoul() {
		if (fouls < max_fouls -1) {
			fouls++;
		} else {
			batterOut();
		}
	}
	
	//Getting info from game
	public int getOutCount() {
		return outs;
	}
	public int getStrikeCount() {
		return strikes;
	}
	public int getBallCount() {
		return balls;
	}
	public int getFoulCount() {
		return fouls;
	}
	public boolean isTopInning() {
		return b_top_inning;
	}
	public String getInningInfo() {
		if (b_top_inning) {
			return "Top " + inning;
		} else {
			return "Bottom " + inning;
		}
	}
	public int getInning() {
		return inning;
	}
	
	public void printGame() {
		/*private int inning, outs, strikes, balls, fouls;
		private int max_inning, max_outs, max_strikes, max_fouls, max_balls;
		private boolean b_top_inning;
		private String str_home_team, str_away_team;
		private Team home_team, away_team;
		*/
		System.out.println("Print Game:");
		System.out.println("Strikes: " + strikes);
		System.out.println("fouls: " + fouls);
		System.out.println("balls: " + balls);
	}
	

	@Override
    protected Game clone(){
		try {
	        final ByteArrayOutputStream baos = new ByteArrayOutputStream(256);
	        final ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(this);
	        oos.close();

	        final ObjectInputStream ois = new ObjectInputStream(
	                new ByteArrayInputStream(baos.toByteArray()));
	        final Game clone = (Game) ois.readObject();
	        return clone;
	    } catch (final Exception e) {
	    	System.out.println("Exception:" + e.toString());
	        throw new RuntimeException("Cloning failed");
	    }
    }
	

}
