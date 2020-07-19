package com.mygdx.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.mygdx.game.MyGame
import com.mygdx.game.actors.Player
import com.mygdx.game.tools.*
import com.mygdx.game.tools.Any
import com.mygdx.game.tools.Any.items
import com.mygdx.game.tools.Any.map
import javax.swing.DropMode

class GameScreen(var myGame: MyGame) : Screen, InputProcessor{
    lateinit var joystick : Joystick
    lateinit var player: Player
    lateinit var button: Button
//    lateinit var camera: OrthographicCamera
    lateinit var gameHud: GameHud
    var dx = 0f
    var dy = 0f
    var portal = Portal()
    lateinit var music: Music
    lateinit var musicPortal: Music

    override fun hide() {
        Any.playerPosition = player.position
        music.stop()
        music.dispose()
    }

    override fun show(){
//        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
//        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f)
        Gdx.input.inputProcessor = this
        loadActors()
        Gdx.input.isCatchBackKey = true
        music.isLooping = true
//        dx = camera.position.x + joystick.point2D.getX()
//        dy = camera.position.y + joystick.point2D.getY()
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

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun dispose() {
        TODO("Not yet implemented")
    }
    fun loadActors(){
        Any.map.createMap()
        music = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"))
        musicPortal = Gdx.audio.newMusic(Gdx.files.internal("teleportation.mp3"))
//        Any.map.nodes = Any.map.nodes.take(5).toMutableList()
//        Any.map.lines = Any.map.lines.take(4).toMutableList()
//        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
//        camera.position.x = camera.viewportWidth/2
//        camera.position.y = camera.viewportHeight/2
        gameHud = GameHud("Health system: ${map.getHealth()}"   )
        joystick = Joystick(myGame.joy_back, myGame.joy_stick, Gdx.graphics.height/3.toFloat())
//        if (Any.playerPosition == null){
        player = Player(myGame.actor, map.nodes.first().position, 20f).apply{ speed = 10f }
//        }
//        else{
//            player = Player(myGame.actor, Any.playerPosition!!, 20f).apply { speed = 10f}
//        }
        button = Button(Texture("invent.png"),
                Point2D((Gdx.graphics.width - Texture("invent.png").width*2).toFloat(),
                        (Gdx.graphics.height - Texture("invent.png").height*2).toFloat()))
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        var y = map.getMaxY() - screenY
        multiTouch(screenX.toFloat(), y, false, pointer)
        return false
    }
    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        multiTouch(screenX.toFloat(), (map.getMaxY() - screenY).toFloat(), true, pointer)
        return false
    }
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        multiTouch(screenX.toFloat(), (map.getMaxY() - screenY).toFloat(), true, pointer)
        return false
    }
    fun gameUpdate(){
//        camera.update()
//        player.position.setX(camera.position.x/2)
//        player.position.setY(camera.position.y/2)
        player.move(joystick.direction)
        player.update()
        map.update()
//        map.nodes.forEach {
//            it.update()
//        }
//        camera.position.x += joystick.direction.getX() * player.speed
//        camera.position.y += joystick.direction.getY() * player.speed
//        joystick.boundsStick.point = Point2D(((camera.viewportWidth/3)/1.5).toFloat(), ((camera.viewportWidth/3)/2+(camera.viewportWidth/3)/4))
//        joystick.boundsCicrle.point = Point2D(((camera.viewportWidth/3)/1.5).toFloat(), ((camera.viewportWidth/3)/2+(camera.viewportWidth/3)/4))
//        joystick.point2D.add(joystick.direction.getX()*player.speed, joystick.direction.getY()*player.speed)
//        joystick.boundsCicrle.point.add(joystick.direction.getX()*10f, joystick.direction.getY()*10f)
//        joystick.point2D.setX(camera.position.x - dx)
//        joystick.point2D.setY(camera.position.y - dy)
//        joystick.boundsStick.point.add(joystick.direction.getX()*player.speed, joystick.direction.getY()*player.speed)
//        joystick.boundsCicrle.point.add(joystick.direction.getX()*player.speed, joystick.direction.getY()*player.speed)
//        joystick.boundsCicrle.point.setX(camera.position.x  - dx)
//        joystick.boundsCicrle.point.setY(camera.position.y - dy)
//        joystick.boundsStick.point.setX(camera.position.x  - dx)
//        joystick.boundsStick.point.setY(camera.position.y - dy)
        colision()
        if (button.click){
            myGame.screen = InventareScreen(myGame)
        }
        if (portal.button.click){
            music.stop()
            musicPortal.play()
            player.position = Point2D((0..Gdx.graphics.width).random().toFloat(), (0..Gdx.graphics.height).random().toFloat())
            portal.button.click = false
            portal.button.pointer = -1
        }
        if (!musicPortal.isPlaying){
            music.play()
        }
        gameHud.update("Health system: ${map.getHealth()}")
        if (map.getHealth() <= 0){
            myGame.screen = DeadScreen(myGame)
        }
    }
    fun gameRender(batch: SpriteBatch){
        map.draw(batch)
        player.draw(batch)
        joystick.draw(batch)

        button.draw(batch)
        gameHud.draw(batch)
        portal.draw(batch)
        map.lines.forEach {
            it.texture.dispose()
        }
        map.nodes.forEach {
            it.texture.dispose()
        }
    }

    fun multiTouch(x:Float, y:Float, isDownTouch: Boolean, pointer: Int){
        for (i in 0..5){
            joystick.update(x, y, isDownTouch, pointer)
            button.update(x, y, isDownTouch, pointer)
            portal.button.update(x, y, isDownTouch, pointer)
        }
    }
    fun colision(){
//        for (i in items){
//            if (i.bounds.overLaps(player.bounds)){
//                Any.playersItem.add(i)
//                items.remove(i)
//                break
//            }
//        }
        for (i in map.nodes){
            if (i.circle.overLaps(player.bounds)) {
                i.fix()
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