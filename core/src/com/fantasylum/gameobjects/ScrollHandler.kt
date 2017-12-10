package com.fantasylum.gameobjects

class ScrollHandler(val yPos : Float) {

    private val scrollSpeed = -59f

    val frontGrass = Grass(0f, yPos, 143f, 11f, scrollSpeed)
    val backGrass = Grass(frontGrass.getTailX(), yPos, 143f, 11f, scrollSpeed)

    fun update(delta: Float) {
        frontGrass.update(delta)
        backGrass.update(delta)
        if (frontGrass.isScrolledLeft) {
            frontGrass.reset(backGrass.getTailX())

        } else if (backGrass.isScrolledLeft) {
            backGrass.reset(frontGrass.getTailX())

        }
    }

}