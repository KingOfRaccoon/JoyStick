package com.mygdx.game.actors

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.graphobject.GraphObject
import com.mygdx.game.tools.Circle
import com.mygdx.game.tools.Point2D
import java.awt.dnd.peer.DragSourceContextPeer
import java.text.FieldPosition

abstract class Actors(texture: Texture, point2D: Point2D, var speed: Float, var radius: Float):GraphObject(texture) {
    var position: Point2D = point2D
    var bounds = Circle(radius, point2D)
    var direction = Point2D(0f,0f)
        set(value) {
            field = value
        }

    //    fun setDirection(dir: Point2D){
//        this.direction = dir
//    }
    fun getDir(): Point2D = direction
}