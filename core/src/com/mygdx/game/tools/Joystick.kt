package com.mygdx.game.tools

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.sin

class Joystick(imgCircle: Texture, imgStick: Texture,size : Float) {
    var textureCircle: Texture = imgCircle
    var textureStick : Texture = imgStick
    var point2D = Point2D(((Gdx.graphics.height/3)/1.5).toFloat(), ((Gdx.graphics.height/3)/2+(Gdx.graphics.height/3)/4).toFloat())
    var radCircle : Float = size/2
    var radStick : Float = radCircle/2
    val boundsCicrle : Circle = Circle(radCircle, point2D)
    var boundsStick  : Circle = Circle(radStick, point2D)
    var direction = Point2D(0f,0f)
    var pointer = -1

    fun draw(batch: SpriteBatch){
        batch.draw(textureCircle, boundsCicrle.point.getX()-radCircle, boundsCicrle.point.getY()-radCircle, radCircle*2, radCircle*2)
        batch.draw(textureStick, boundsStick.point.getX()-radStick, boundsStick.point.getY() - radStick, radStick*2, radStick*2)
    }

    fun update(x:Float, y:Float, isDownTouch: Boolean, pointer: Int){
        var touch = Point2D(x,y)
        if (boundsCicrle.isContains(touch) && isDownTouch && this.pointer == -1)
            this.pointer = pointer
        if (boundsCicrle.overLaps(boundsStick) && isDownTouch && pointer == this.pointer)
            atControl(touch)
        if((!isDownTouch && pointer == this.pointer) || (isDownTouch && pointer == this.pointer && !boundsCicrle.isContains(touch)))
            returnStick()
    }
    fun atControl(point: Point2D){
        boundsStick.point = point
        var dx = boundsCicrle.point.getX() - boundsStick.point.getX()
        var dy = boundsCicrle.point.getY() - boundsStick.point.getY()
        var dist = Math.sqrt((dx*dx + dy*dy).toDouble()).toFloat()
        direction = Point2D(-(dx/dist), -(dy/dist))
    }
    fun returnStick(){
        boundsStick.point.setPoint(boundsCicrle.point)
        direction = Point2D(0f,0f)
        pointer = -1
    }
}