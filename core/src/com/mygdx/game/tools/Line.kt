package com.mygdx.game.tools

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import kotlin.math.*

class Line(var id1 : Node, var id2: Node) {
    var texture = Texture("track2.png")
    fun draw(batch: SpriteBatch){
        val startPoint = if(range(id1) < range(id2)) id1 else id2

        val dx = (id2.x - id1.x)
        val dy = (id2.y - id1.y)
        val dist = sqrt(dx*dx + dy*dy)
        val scale = dist / texture.width
        val angle = if (dx > 0 ) atan2(dy,dx) / PI * 180f else -(atan2(dy, dx) / PI * 180f)

        val textureRegion = TextureRegion(texture)
        batch.draw(textureRegion,
                startPoint.x + startPoint.texture.width / 2,
                startPoint.y + startPoint.texture.height / 2,
                0f, 0f,
                texture.width.toFloat(),
                texture.height.toFloat(),
                scale, 2f,
                angle.toFloat())
    }
    fun range(node : Node) = sqrt(node.x * node.x + node.y * node.y)

}