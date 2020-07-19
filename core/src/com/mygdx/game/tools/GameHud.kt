package com.mygdx.game.tools

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.mygdx.game.screen.GameScreen
import java.awt.Font

class GameHud(var str : String){
    var font: BitmapFont
    init {
        var gen = FreeTypeFontGenerator(Gdx.files.internal("juice.ttf"))
        var p = FreeTypeFontGenerator.FreeTypeFontParameter()
        p.size = Gdx.graphics.width / 25
        p.color = Color(1f, 0.5f, 0f, 1f)

        font = gen.generateFont(p)
    }
    fun draw(batch: SpriteBatch){
        var gl = GlyphLayout()
        gl.setText(font, str)
        font.draw(batch, gl, 0f, Gdx.graphics.height - gl.height)
    }
    fun update(str: String){
        this.str = str
    }
}