package com.example.scorekeeper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Team implements Cloneable, Serializable{

private String team_name;
private int team_score;
private boolean b_is_home_team;

	public void newTeam(String name, boolean home_team) {
		team_name = name;
		b_is_home_team = home_team;
		team_score = 0;
	}
	
	public void teamScored() {
		team_score++;
	}
	
	public boolean isHomeTeam() {
		return b_is_home_team;
	}
	
	public void setName(String name) {
		team_name = name;
	}
	
	public int getTeamScore() {
		return team_score;
	}
	
	public String getTeamName() {
		return team_name;
	}
	
	@Override
    protected Team clone(){
		try {
	        final ByteArrayOutputStream baos = new ByteArrayOutputStream(256);
	        final ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(this);
	        oos.close();

	        final ObjectInputStream ois = new ObjectInputStream(
	                new ByteArrayInputStream(baos.toByteArray()));
	        final Team clone = (Team) ois.readObject();
	        return clone;
	    } catch (final Exception e) {
	    	System.out.println("Exception:" + e.toString());
	        throw new RuntimeException("Cloning failed");
	    }
    }



}
