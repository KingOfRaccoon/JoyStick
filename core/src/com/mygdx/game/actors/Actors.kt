package com.mygdx.game.actors

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.graphobject.GraphObject
import com.mygdx.game.tools.Circle
import com.mygdx.game.tools.Point2D
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.tan

abstract class Actors(texture: Texture, point2D: Point2D):GraphObject(texture) {
    var speed: Float = 1f
    var position: Point2D = point2D
    var bounds = Circle(texture.height/2.toFloat(), point2D)
    var direction = Point2D(0f,0f)

    fun move(point2D: Point2D){
        direction = point2D
        position.add(point2D.getX()* speed, point2D.getY()*speed)
    }

    fun getAngle(): Float{
        if (direction.getY() == 0f && direction.getX() == 0f){
            return 0f
        }
//        SpeedY = cos(angle/(180*PI))
//        SpeedX = cos((90+angle)/(180*PI))
        var angle = (acos(direction.getY())/ PI * 180).toFloat()
            println(angle)
            return angle
    }
    fun getTestAngle():Float{
        var testAngle = ((acos(direction.getX())/ PI *180)-90).toFloat()
        testAngle %= 360f
        if (testAngle < 0){
            testAngle = 360 + testAngle
        }
        return testAngle
    }
    fun getTan(): Float {
        if (direction.getX() == 0f && direction.getY() > 0) {
            return 0f
        } else {
            if (direction.getX() == 0f && direction.getY() < 0) {
                return 180f
            }
            else{
                var tan = tan(direction.getY() / direction.getX())
                if (direction.getX() < 0){
                    tan *= -1
                }
                println((tan/ PI * 180))
                return (tan/ PI * 180).toFloat()
            }
        }
    }
}
