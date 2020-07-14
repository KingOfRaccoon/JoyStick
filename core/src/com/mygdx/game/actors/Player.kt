package com.mygdx.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.MyGame
import com.mygdx.game.tools.Point2D
import java.text.FieldPosition

class Player(texture: Texture, position: Point2D, speed: Float, radius: Float, health:Float):Actors(texture, position, speed, radius) {
    var health = health

    override fun draw(batch: SpriteBatch) {
        batch.draw(img, position.getX()-radius, position.getY()-radius)
    }

    override fun update() {
        if (position.getX() + radius > Gdx.graphics.width)
            position.setX(Gdx.graphics.width-radius)
        if (position.getX() - radius < 0)
            position.setX(radius)
        if (position.getY() + radius > Gdx.graphics.height)
            position.setY(Gdx.graphics.height-radius)
        if (position.getY() - radius < 0)
            position.setY(radius)

        position.add(getDir().getX()*speed, getDir().getY()*speed)
    }
}