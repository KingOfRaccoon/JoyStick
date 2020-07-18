package com.mygdx.game.tools

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Node(var g: Int = 0) {
    var texture = Texture("node.png")
    private val originX = 600f
    private val originY = 400f
    var x = 0f
    var y = 0f
    constructor(x:Float, y:Float, g: Int) : this() {
        this.x = x
        this.y = y
        this.g = g
        if (this.x == 0f && this.y == 0f){
            this.x = originX
            this.y = originY
        }
    }

    fun draw(batch: SpriteBatch){
            batch.draw(texture, x, y)
    }
}