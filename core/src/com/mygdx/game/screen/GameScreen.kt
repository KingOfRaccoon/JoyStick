package com.mygdx.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.MyGame
import com.mygdx.game.actors.Item
import com.mygdx.game.actors.Player
import com.mygdx.game.tools.Any
import com.mygdx.game.tools.Any.items
import com.mygdx.game.tools.Button
import com.mygdx.game.tools.Joystick
import com.mygdx.game.tools.Point2D

class GameScreen(var myGame: MyGame) : Screen, InputProcessor{

    lateinit var joystick : Joystick
    lateinit var player: Player
    lateinit var button: Button


    override fun hide() {
        Any.playerPosition = player.position
    }

    override fun show(){
        Gdx.input.inputProcessor = this
        loadActors()
        Gdx.input.isCatchBackKey = true
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
        joystick = Joystick(myGame.joy_back, myGame.joy_stick, (myGame.height/3).toFloat())
        if (Any.playerPosition == null){
        player = Player(myGame.actor, Point2D((myGame.weight/2).toFloat(), (myGame.height/2).toFloat()), 20f).apply{ speed = 10f }}
        else{
            player = Player(myGame.actor, Any.playerPosition!!, 20f).apply { speed = 10f}
        }
        button = Button(Texture("invent.png"),
                Point2D((Gdx.graphics.width - Texture("invent.png").width*2).toFloat(),
                        (Gdx.graphics.height - Texture("invent.png").height*2).toFloat()))
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
        colision()
        if (button.click){
            myGame.screen = InventareScreen(myGame)
        }
    }
    fun gameRender(batch: SpriteBatch){
        player.draw(batch)
        joystick.draw(batch)
        items.forEach { it.draw(batch) }
        button.draw(batch)
    }

    fun multiTouch(x:Float, y:Float, isDownTouch: Boolean, pointer: Int){
        for (i in 0..5){
            joystick.update(x, y, isDownTouch,pointer)
            button.update(x, y, isDownTouch, pointer)
        }
    }
    fun colision(){
        for (i in items){
            if (i.bounds.overLaps(player.bounds)){
                Any.playersItem.add(i)
                items.remove(i)
                break
            }
        }
    }
    fun back(keycode: Int, isDownTouch: Boolean){
        if (keycode == com.badlogic.gdx.Input.Keys.BACK && isDownTouch){
            myGame.screen = MainScreen(myGame)
        }
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
    override fun keyDown(keycode: Int): Boolean {
        back(keycode, false)
        return false
    }
}