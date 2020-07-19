package com.mygdx.game.tools

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Node(var g: Int = 0) {
    var texture = Texture("node.png")
    var x = 400f
    var y = 300f
    var health = 10f
    var position = Point2D(x + texture.width/2.toFloat(), y + texture.width/2.toFloat())
    var circle = Circle(texture.width.toFloat(), position)
    var crashNodes : CrashNodes = CrashNodes.OK
    var time = 0f
    constructor(x:Float, y:Float, g: Int) : this() {
        this.x = x
        this.y = y
        this.g = g
    }
    fun hit(){
        this.health--
        if (health < 1){
            crashNodes = CrashNodes.CRASH
        }
    }
    fun fix(){
        if (health < 10 && health > 0){
            this.health++
        }
    }
    fun getStatysImage(){
        when(crashNodes){
            CrashNodes.WARNING -> texture = Texture("neu_red.png") // текстура для поврежденного нейрона
            CrashNodes.CRASH -> texture = Texture("neu_red.png") // текстура для сломанного нейрона
        }
    }
    fun draw(batch: SpriteBatch){
            batch.draw(texture, x, y)
    }
    fun crash(){
        crashNodes = CrashNodes.WARNING
    }
    fun update(){
        getStatysImage()
        time += Gdx.graphics.deltaTime
        if (crashNodes == CrashNodes.WARNING && time > 3f){
            hit()
            time = 0f
        }
    }
}