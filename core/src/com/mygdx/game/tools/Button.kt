package com.mygdx.game.tools

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.MyGame
import com.mygdx.game.graphobject.GraphObject
import com.mygdx.game.screen.InventareScreen

open class Button(texture: Texture, point2D: Point2D){
    var img = texture
    var position = point2D
    var cicrle = Circle(img.height*4.toFloat(), position)
    var pointer = -1
    var click = false
    fun draw(batch: SpriteBatch) {
        batch.draw(img, position.getX() - img.height*2, position.getY()- img.width*2,
                img.width*4.toFloat(), img.height*4.toFloat())
    }

    fun update(x:Float, y : Float, isDownTouch: Boolean, pointer: Int) {
        if (cicrle.isContains(Point2D(x, y)) && isDownTouch && this.pointer == -1){
            this.pointer = pointer
            click = true
        }
    }
}