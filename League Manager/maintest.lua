
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
textBox = native.newTextField( 15, 70, 280, 70 )
print("Here")
display.insert(textBox)
print("Did it")
textBox.text = "This is line 1.\nAnd this is line2"
textBox:addEventListener( "userInput", inputListener )