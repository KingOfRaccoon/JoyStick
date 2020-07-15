package com.mygdx.game.tools

class Point2D(x:Float, y:Float) {
    private var x = x
    private var y = y

    fun add(xPlus:Float, yPlus:Float){
        x += xPlus
        y += yPlus
    }
    fun setX(xNew: Float){
        this.x = xNew
    }
    fun setY(yNew:Float){
        this.y = yNew
    }
    fun getX(): Float = x
    fun getY(): Float = y
    fun setPoint(point2D: Point2D){
        this.x = point2D.x
        this.y = point2D.y
    }
}