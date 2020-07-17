package com.mygdx.game.tools

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Node(var g: Int = 0) {
    var texture = Texture("")
    val originX = 600f
    val originY = 400f
    var x = 0f
    var y = 0f
    constructor(x:Float, y:Float, g: Int) : this() {
        this.x = x
        this.y = y
        this.g = g
    }

    fun draw(batch: SpriteBatch){
        if (x == 0f && y == 0f)
            batch.draw(texture, originX, originY)
        else
            batch.draw(texture, x, y)
    }
}