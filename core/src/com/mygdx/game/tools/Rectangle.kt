package com.mygdx.game.tools

class Rectangle(var weight: Float, var height: Float, var position: Point2D){

    fun isContains(point2D: Point2D): Boolean{
        return position.getX() <= point2D.getX() && position.getX() + weight >= point2D.getX() && position.getY() <= point2D.getY() && position.getY() + height >= point2D.getY()
    }

}