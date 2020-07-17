package com.mygdx.game.tools

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class RectangleButton(texture: Texture, point2D: Point2D): Button(texture, point2D){
    var height = img.height.toFloat()
    var weight = img.width.toFloat()
    var bounds = Rectangle(weight*4, height*4, position)

    fun onClick(x:Float, y : Float, isDownTouch: Boolean, pointer: Int) {
        if (bounds.isContains(Point2D(x, y)) && isDownTouch && this.pointer == -1){
            this.pointer = pointer
            click = true
        }
    }

    fun drawer(batch: SpriteBatch){
        batch.draw(img, position.getX(), position.getY(),weight*4, height*4)
    }
}