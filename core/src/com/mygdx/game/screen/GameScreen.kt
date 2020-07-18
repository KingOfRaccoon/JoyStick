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


public class GameScreen(var myGame: MyGame) : Screen, InputProcessor{

    lateinit var joystick : Joystick
    lateinit var player: Player
    lateinit var camera: OrthographicCamera // камера

    lateinit var game_joystic_distance :Point2D
    /**
     * создаю объект <game_joystic_distance>, который хранит разницу расстояния джостика и игрока
     ** в дальнейшем используется для установки позиции джостика оттносительно игроку*/

    var items: MutableList<Item> = mutableListOf()


    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun show(){
        camera = OrthographicCamera(myGame.weight.toFloat()/2, myGame.height.toFloat()/2)
        // инициализация камеры

        Gdx.input.inputProcessor = this

        loadActors()
        game_joystic_distance = Point2D(
                x = player.position.getX() - joystick.point2D.getX(),
                y = player.position.getY() - joystick.point2D.getY())
    }

    fun gameUpdate(){
        player.move(joystick.direction)
        player.update()

        joystick.point2D.setX(player.position.getX() - game_joystic_distance.getX())//=====
        joystick.point2D.setY(player.position.getY() - game_joystic_distance.getY())//=====
        camera.position.x = player.position.getX() // камера следит
        camera.position.y = player.position.getY() // за игроком

        colision()
    }

    fun gameRender(batch: SpriteBatch){
        player.draw(batch)
        joystick.draw(batch)

        items.forEach { it.draw(batch) }
    }



    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update() // обновление камеры
        myGame.batch.projectionMatrix = camera.combined

        myGame.batch.begin()
        gameRender(myGame.batch)
        myGame.batch.end()
        gameUpdate()
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