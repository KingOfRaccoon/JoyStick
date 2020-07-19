package com.mygdx.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.mygdx.game.MyGame
import com.mygdx.game.tools.GameHud

class DeadScreen(var myGame: MyGame):Screen, InputProcessor {

    lateinit var font: BitmapFont
    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun show() {
        Gdx.input.inputProcessor = this
        var gen = FreeTypeFontGenerator(Gdx.files.internal("19713.ttf"))
        var p = FreeTypeFontGenerator.FreeTypeFontParameter()
        p.size = Gdx.graphics.width / 5
        p.color = Color(1f, 0.5f, 0f, 1f)

        font = gen.generateFont(p)
    }

    override fun render(delta: Float) {

        var gl = GlyphLayout()
        gl.setText(font, "You died")
        myGame.batch.begin()
        font.draw(myGame.batch, gl, Gdx.graphics.width/2 - gl.width, Gdx.graphics.height/2 - gl.height)
        myGame.batch.end()
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun dispose() {
        TODO("Not yet implemented")
    }






    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun keyTyped(character: Char): Boolean {
        TODO("Not yet implemented")
    }

    override fun scrolled(amount: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun keyUp(keycode: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun keyDown(keycode: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        TODO("Not yet implemented")
    }
}