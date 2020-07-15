package com.mygdx.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.MyGame
import com.mygdx.game.actors.Player
import com.mygdx.game.tools.Joystick
import com.mygdx.game.tools.Point2D

class GameScreen(var myGame: MyGame) : Screen, InputProcessor{

    lateinit var joystick : Joystick
    lateinit var player: Player

    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun show(){
        Gdx.input.inputProcessor = this
        loadActors()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gameUpdate()
        myGame.batch.begin()
        gameRender(myGame.batch)
        myGame.batch.end()
    }

    override fun pause() {

    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun dispose() {
        TODO("Not yet implemented")
    }
    fun loadActors(){
        joystick = Joystick(myGame.circle, myGame.circle, (myGame.height/3).toFloat())
        player = Player(myGame.actor, Point2D((myGame.weight/2).toFloat(), (myGame.height/2).toFloat()), 20f).
        apply { speed = 10f }
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        multiTouch(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat(), false, pointer)
        return false
    }
    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        multiTouch(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat(), true, pointer)
        return false
    }
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        multiTouch(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat(), true, pointer)
        return false
    }
    fun gameUpdate(){
        player.move(joystick.direction)
        player.update()
    }
    fun gameRender(batch: SpriteBatch){
        player.draw(batch)
        joystick.draw(batch)
    }

    fun multiTouch(x:Float, y:Float, isDownTouch: Boolean, pointer: Int){
        for (i in 0..5){
            joystick.update(x, y, isDownTouch,pointer)
        }
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
    override fun keyDown(keycode: Int): Boolean {
        TODO("Not yet implemented")
    }
}