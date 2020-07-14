package com.mygdx.game.graphobject

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class GraphObject(texture: Texture) {
    var img: Texture = texture
    abstract fun draw(batch: SpriteBatch)
    abstract fun update()
}