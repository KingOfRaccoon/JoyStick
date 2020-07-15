package com.mygdx.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.tools.Point2D

class Player(texture: Texture, position: Point2D, health:Float):Actors(texture, position){
    var health = health
    var radius = texture.height/2.toFloat()
    override fun draw(batch: SpriteBatch) {
        var image = TextureRegion(img)
        batch.draw(image, position.getX(), position.getY(),
                radius/2, radius/2,
                radius, radius,
                1f, 1f, getAngle())
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
}