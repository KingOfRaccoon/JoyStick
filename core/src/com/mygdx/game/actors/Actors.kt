package com.mygdx.game.actors

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.graphobject.GraphObject
import com.mygdx.game.tools.Circle
import com.mygdx.game.tools.Point2D
import java.awt.dnd.peer.DragSourceContextPeer
import java.text.FieldPosition
import kotlin.math.PI
import kotlin.math.acos

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
        var angle = (acos(direction.getX()) *(180* PI)).toFloat()
        angle %= 360f
        return angle
    }
}