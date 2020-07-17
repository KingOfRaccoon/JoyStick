package com.mygdx.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.MyGame
import com.mygdx.game.tools.Any
import com.mygdx.game.tools.Button
import com.mygdx.game.tools.Point2D
import jdk.internal.util.xml.impl.Input

class InventareScreen(var myGame: MyGame): Screen, InputProcessor {
    lateinit var button: Button
    override fun hide() {

    }

    override fun show() {
        Gdx.input.inputProcessor = this
        loadItems()
        Gdx.input.isCatchBackKey = true
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        myGame.batch.begin()
        inventRender(myGame.batch)
        myGame.batch.end()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun dispose() {
        TODO("Not yet implemented")
    }
    fun inventRender(batch: SpriteBatch){
        Any.playersItem.forEach { it.draw(batch) }
//        button.draw(batch)
    }
    fun loadItems(){
        for (i in 0 until Any.playersItem.size){
            Any.playersItem[i].position = Point2D((Gdx.graphics.width/(i+1.5)).toFloat(), Gdx.graphics.height/2.toFloat())
        }
//        button = Button(Texture("back.png")).apply { position = Point2D(0f, Gdx.graphics.height - Texture("back.png").width.toFloat()) }
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
         return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun keyUp(keycode: Int): Boolean {
        back(keycode, true)
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun keyDown(keycode: Int): Boolean {
        back(keycode, false)
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        TODO("Not yet implemented")
    }
    fun back(keycode: Int, isDownTouch: Boolean){
        if (keycode == com.badlogic.gdx.Input.Keys.BACK && isDownTouch){
            myGame.screen = GameScreen(myGame)
        }
    }
}