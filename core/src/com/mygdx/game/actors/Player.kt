package com.mygdx.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.tools.Point2D
import sun.awt.image.GifImageDecoder

class Player(texture: Texture, position: Point2D, health:Float):Actors(texture, position){
    var health = health
    var radius = texture.height*8.toFloat()
    var myItem = mutableListOf<Item>()
    override fun draw(batch: SpriteBatch) {
        setImade()
        var image = TextureRegion(img)
        batch.draw(image, position.getX(), position.getY(),
                radius/2, radius/2)
    }

    override fun update() {
        if (position.getX() + radius > Gdx.graphics.width)
            position.setX(Gdx.graphics.width-radius)
        if (position.getX() < 0)
            position.setX(0f)
        if (position.getY() + radius > Gdx.graphics.height)
            position.setY(Gdx.graphics.height-radius)
        if (position.getY() < 0)
            position.setY(0f)
    }
    fun setImade(){
        when (getTestAngle() % 360f){
            in (46..135) -> img = Texture("persLeft.png")
            in (226..315) -> img = Texture("persRight.png")
        }
        when(getAngle()%360f){
            in (316..360) -> img = Texture("persUp.png")
            in (0..45) -> img = Texture("persUp.png")
            in (136..225) -> img = Texture("persDown.png")
        }

    }
}