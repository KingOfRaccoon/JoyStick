package com.mygdx.game.tools

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import kotlin.math.atan
import kotlin.math.sqrt

class Line(var id1 : Node, var id2: Node) {
    var texture = Texture("track2.png")
    fun draw(batch: SpriteBatch){
        var dx = id2.x - id1.x
        var dy = id2.y - id1.y
        var dist = sqrt(dx*dx + dy*dy)
        var angle = atan(dy/dx)
        var textureRegion = TextureRegion(texture)
        batch.draw(textureRegion, id1.x, id1.y,
        texture.width/2.toFloat(), texture.height/2.toFloat(),
        texture.width.toFloat(), dist,
        1f, 1f, angle)
    }
}