local widget = require( "widget" )


function printMatchTable ( event )
    
local tableView = widget.newTableView
{
    top = 100,
    width = 320, 
    height = 366,
    maskFile = "assets/mask-320x366.png",
    listener = tableViewListener,
    onRowRender = onRowRender,
    onRowTouch = onRowTouch,
}

display.newRect(0, 800, 100, 100)

for i = 1, 100 do
    local isCategory = false
    local rowHeight = 30
    local rowColor = 
    { 
        default = { 255, 255, 255 },
    }
    local lineColor = { 220, 220, 220 }

    -- Make some rows categories
    if i == 25 or i == 50 or i == 75 then
        isCategory = true
        rowHeight = 24
        rowColor = 
        { 
            default = { 150, 160, 180, 200 },
        }
    end

    -- Insert the row into the tableView
    tableView:insertRow{
        isCategory = isCategory,
        rowHeight = rowHeight,
        rowColor = rowColor,
        lineColor = lineColor,
    }
end 

end


-- Listen for tableView events
local function tableViewListener( event )
    local phase = event.phase
    local row = event.target

    print( event.phase )
end

-- Handle row rendering
local function onRowRender( event )
    local phase = event.phase
    local row = event.row

    local rowTitle = display.newText( row, "Row " .. row.index, 0, 0, nil, 14 )
    rowTitle.x = row.x - ( row.contentWidth * 0.5 ) + ( rowTitle.contentWidth * 0.5 )
    rowTitle.y = row.contentHeight * 0.5
    rowTitle:setTextColor( 0, 0, 0 )
end

-- Handle touches on the row
local function onRowTouch( event )
    local phase = event.phase

    if "press" == phase then
        print( "Touched row:", event.target.index )
    end
end

-- Create a tableView

--group:insert( tableView )

-- Create 100 rows

