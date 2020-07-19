package com.mygdx.game.tools

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Portal {
    var button = Button(Texture("jo_face.png"), Point2D(Gdx.graphics.width -((Gdx.graphics.height/3)/1.5).toFloat(), ((Gdx.graphics.height/3)/2+(Gdx.graphics.height/3)/4).toFloat()))
    var rad = button.img.width/2

    fun draw(batch: SpriteBatch){
        batch.draw(button.img, button.position.getX()-rad, button.position.getY()-rad)
    }
}