package com.mygdx.game.tools

class Circle(radius: Float, point: Point2D){
    var radius = radius
    var point = point
    
    fun isContains(point2D: Point2D): Boolean {
        var dx = point.getX() - point2D.getX()
        var dy = point.getY() - point2D.getY()
        return dx*dx+dy*dy <= radius*radius
    }
    fun overLaps(circle: Circle):Boolean{
        var dx = point.getX() - circle.point.getX()
        var dy = point.getY() - circle.point.getY()
        var dist = dx*dx+dy*dy
        var sumR = circle.radius + radius
        return dist < sumR*sumR
    }
}