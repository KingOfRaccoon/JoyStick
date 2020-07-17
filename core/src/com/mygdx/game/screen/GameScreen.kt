package com.mygdx.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.MyGame
import com.mygdx.game.actors.Item
import com.mygdx.game.actors.Player
import com.mygdx.game.tools.Joystick
import com.mygdx.game.tools.Point2D


class GameScreen(var myGame: MyGame) : Screen, InputProcessor{

    lateinit var joystick : Joystick
    lateinit var player: Player
    lateinit var camera: OrthographicCamera

    var items: MutableList<Item> = mutableListOf()

    //camera = OrthographicCamera(myGame.weight.toFloat(), myGame.height.toFloat())



    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun show(){
        camera = OrthographicCamera(myGame.weight.toFloat()/2, myGame.height.toFloat()/2)
        Gdx.input.inputProcessor = this
        loadActors()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gameUpdate()
        camera.update()
        myGame.batch.setProjectionMatrix(camera.combined);

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
        items.add(Item(Texture("coin.png"), "Coin", Point2D(myGame.weight/4*3.toFloat(), myGame.height/2.toFloat()  )))
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
        camera.position.x = player.position.getX()
        camera.position.y = player.position.getY()

        colision()
    }
    fun gameRender(batch: SpriteBatch){
        player.draw(batch)
        joystick.draw(batch)
        items.forEach { it.draw(batch) }
    }

    fun multiTouch(x:Float, y:Float, isDownTouch: Boolean, pointer: Int){
        for (i in 0..5){
            joystick.update(x, y, isDownTouch,pointer)
        }
    }
    fun colision(){
        for (i in 0 until items.size){
            if (items[i].bounds.overLaps(player.bounds)){
                player.myItem.add(items[i])
                items.removeAt(i)
                break
            }
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