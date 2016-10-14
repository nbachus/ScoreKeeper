require "sqlite3"
local insertQuery
local btn_submit = display.newImage("submit.JPG",50,200)

 function openMatchScreen( event )
    --display.setDefault( "background", 0, 0, 255)
    local btn_submit = display.newImage("submit.JPG",50,200)
    home_team_id = 3
    away_team_id = 4
    home_score = 0
    away_score = 0
    winner_team_id = -2
    local change_amt = 0
    
    local home_score_add = display.newImage(pics[4], 150, 80)
    home_score_add:addEventListener("tap", onHomeTeamScorePlus)
    home_score_add.score = home_score
    local away_score_add = display.newImage(pics[4], 150, 140)
    away_score_add.score = away_score
    away_score_add:addEventListener("tap", onAwayTeamScorePlus)
    
    local home_score_add = display.newImage(pics[5], 250, 80)
    home_score_add:addEventListener("tap", onHomeTeamScoreMinus)
    local away_score_add = display.newImage(pics[5], 250, 140)
    away_score_add:addEventListener("tap", onAwayTeamScoreMinus)
    
    local txt_title = display.newText("Match: ", 50, 50, "Arial", 14)
    txt_title:setFillColor(1, 0, 0)
    txt_home_score = display.newText("Home: "..home_score, 50, 100, "Arial", 14)
    txt_home_score:setFillColor( 1, 0, 0 )
    txt_away_score = display.newText("Away: "..away_score, 50, 150, "Arial", 14)
    txt_away_score:setFillColor( 1, 0, 0 )
    
    print("Away Score: "..away_score)
    
    
    --onSubmitMatch.insertQuery = "[[INSERT INTO match VALUES (NULL, "..home_team_id..","..away_team_id..","..home_score..","..away_score..","..winner_team_id..");]]"
    --onSubmitMatch.insertQuery = "Test"
    
end
 
 
 function onHomeTeamScorePlus(event)
    
    print("curScore Home: "..home_score)
    home_score = home_score + 1
    txt_home_score.text = "Home: "..home_score
    print("curScore Home: "..home_score)
    
end

 function onHomeTeamScoreMinus(event)
    
    print("curScore Home: "..home_score)
    home_score = home_score - 1
    txt_home_score.text = "Home: "..home_score
    print("curScore Home: "..home_score)
    
end

 function onAwayTeamScorePlus(event)
    
    print("curScore Away: "..away_score)
    away_score = away_score + 1
    txt_away_score.text = "Away: "..away_score
    print("curScore Away: "..away_score)
end

 function onAwayTeamScoreMinus(event)
    
    print("curScore Away: "..away_score)
  --  event.target.score = away_score + event.target.score
  away_score = away_score - 1
    txt_away_score.text = "Away: "..away_score
    print("curScore Away: "..away_score)
end


-- Saving to the database
local function onSubmitMatch (event)
    print("Submitting")
    if (home_score > away_score) then
        winner_team_id = home_team_id
    elseif (away_score > home_score) then
        winner_team_id = away_team_id
    else
        winner_team_id = -1  
    end
    print(event.target.insertQuery)
    insertQuery = string.format([[INSERT INTO match VALUES (NULL, '%i', '%i', '%i', '%i', '%i');]], home_team_id, away_team_id, home_score, away_score, winner_team_id)
    --insertQuery = [[INSERT INTO match VALUES (NULL, 1, 2, 6, 3, 1);]]
    db:exec(insertQuery)
    print(db:errmsg())
    local outputTxt = display.newText(insertQuery, 0,400,"Arial", 14)
    print(outputTxt.text)
    
end


btn_submit:addEventListener("tap", onSubmitMatch)


