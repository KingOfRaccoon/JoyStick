package com.mygdx.game.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.graphobject.GraphObject
import com.mygdx.game.tools.Point2D

class Item(var texture: Texture, var name: String, point2D: Point2D): Actors(texture, point2D) {
    override fun draw(batch: SpriteBatch) {
        batch.draw(texture, position.getX(), position.getY())
    }

    override fun update() {
        TODO("Not yet implemented")
    }
}