package com.fantasylum.gameobjects

import com.fantasylum.utils.Scrollable

class Grass(x : Float,y : Float, width : Float, height : Float, scrollSpeed : Float)
    : Scrollable(x, y, width, height, scrollSpeed) {

    fun getTailX(): Float {
        return position.x + width
    }

    fun reset(newX : Float) {
        position.x = newX
        isScrolledLeft = false
    }
}