require "CiderDebugger";
--Setup database access here

require "sqlite3"
require "matchReport"
require "viewMatches"
local path = system.pathForFile("data.db", system.DocumentsDirectory)
print(path)
db = sqlite3.open( path )
local tablesetup = [[CREATE TABLE IF NOT EXISTS match (id INTEGER PRIMARY KEY autoincrement, hteamid, ateamid, hteamscore, ateamscore, winnerid);]]
db:exec( tablesetup)
--local insertQuery = [[INSERT INTO match VALUES (0, 1, 2, 6, 3, 1);]]
local insertQuery
--db:exec(insertQuery)
printMatchTable()
for row in db:nrows("SELECT * FROM match") do
    print(row.id.." "..row.hteamid.." "..row.ateamid.." "..row.hteamscore.." "..row.ateamscore.." "..row.winnerid)
    
end

--
print("Hello")

----
local textBox

local function inputListener( event )
    if event.phase == "began" then

        -- user begins editing textBox
        print( event.text )

    elseif event.phase == "ended" then

        -- do something with textBox's text

    elseif event.phase == "editing" then

        print( event.newCharacters )
        print( event.oldText )
        print( event.startPosition )
        print( event.text )

    end
end





----
pics = {"ranger-pup.JPG", "stop.JPG", "video.JPG", "plus.JPG", "minus.JPG"}

local ranger = display.newImage(pics[1])
local btn_stop = display.newImage(pics[2])
local btn_video = display.newImage(pics[3])

local txt_count = display.newText("Touch me!", 100, 100, Arial, 20)
local count = 0
txt_count.isVisible = false
btn_stop.isVisible = false
btn_video.isVisible = true
ranger:scale(.5,.5)
ranger:translate(-200,-230)


local function onPicClick( event )
    print("Playing song")
-- ranger.scale(2,2)
local song = audio.loadSound("04_thieves.mp3")
audio.setVolume(.5)
audio.play(song)
ranger.isVisible = false
btn_stop.isVisible = true
txt_count.isVisible = false
btn_video.isVisible = false
print("I should be up")
end

local function onBtnStop ( event )
    
    audio.stop()
    
    btn_stop.isVisible = false
    txt_count.isVisible = true
    ranger.isVisible = true
    btn_video.isVisible = true
    count = count + 1
    txt_count.text = "Clicked " .. count .. " times"
    print(count)
    
end

local function onVideoClick ( event )
    print("Playing match...")
        btn_stop.isVisible = false
    txt_count.isVisible = false
    ranger.isVisible = false
    btn_video.isVisible = false
    openMatchScreen()
--    ranger.isVisible = false
--    btn_video.isVisible = false
--    btn_submit.isVisible = true
--    --display.setDefault( "background", 0, 0, 255)
--    local home_team_id = 3
--    local away_team_id = 4
--    local home_score = -2
--    local away_score = -2
--    local winner_team_id = -2
--    local txt_title = display.newText("Match: ", 50, 50, "Arial", 14)
--    local txt_home_score = display.newText("Home: ", 50, 100, "Arial", 14)
--    local txt_away_score = display.newText("Away: ", 50, 150, "Arial", 14)
--    local home_score_add = display.newImage(pics[4], 75, 100)
--    home_score_add:addEventListener("touch", onTeamScore,1,home_score)
--    local away_score_add = display.newImage(pics[4], 75, 150)
--    away_score_add:addEventListener("touch", onTeamScore,1,away_score)
--    print("Away Score: "..away_score)
--    
--    if (home_score > away_score) then
--        winner_team_id = home_team_id
--    elseif (away_score > home_score) then
--        winner_team_id = away_team_id
--    else
--        winner_team_id = -1  
--    end
--    
--    insertQuery = "[[INSERT INTO match VALUES (NULL, "..home_team_id..","..away_team_id..","..home_score..","..away_score..","..winner_team_id..");]]"
--    
    
end
 


-- Saving to the database
--local function onSubmitMatch (event)
--    print("Submitting")
--    -- local insertQuery = [[INSERT INTO match VALUES (NULL, 1, 2, 6, 3, 1);]]
--    db:exec(insertQuery)
--    local outputTxt = display.newText(insertQuery, 0,400,"Arial", 14)
--    print(outputTxt.text)
--    
--end

local function onSystemEvent( event )
    if event.type == "applicationExit" then
        if db and db:isopen() then
        db:close()
        end
    end
end

local function textListener( event )
    if event.phase == "began" then

        -- user begins editing textField
        print( event.text )

    elseif event.phase == "ended" then

        -- textField/Box loses focus

    elseif event.phase == "ended" or event.phase == "submitted" then

        -- do something with defaulField's text

    elseif event.phase == "editing" then

        print( event.newCharacters )
        print( event.oldText )
        print( event.startPosition )
        print( event.text )

    end
end
Runtime:addEventListener( "system", onSystemEvent )

ranger:addEventListener( "touch", onPicClick )
btn_stop:addEventListener( "touch" , onBtnStop)
btn_video:addEventListener("touch", onVideoClick)


