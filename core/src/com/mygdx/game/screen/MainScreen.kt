package com.mygdx.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.mygdx.game.MyGame
import com.mygdx.game.tools.Button
import com.mygdx.game.tools.Point2D

import com.mygdx.game.tools.RectangleButton
import kotlin.system.exitProcess

class MainScreen(var myGame: MyGame):Screen, InputProcessor {
    lateinit var startButton: RectangleButton
    lateinit var exitButton : RectangleButton
    lateinit var soundButton: Button
    lateinit var backTexture: Texture
    lateinit var backSprite : Sprite
    override fun hide() {

    }

    override fun show() {
        Gdx.input.inputProcessor = this
        load()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        mainUpdate()
        myGame.batch.begin()
        mainRender(myGame.batch)
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
    fun load(){
        startButton = RectangleButton(Texture("start.png"), Point2D(myGame.weight/1.7.toFloat(), myGame.height/1.75.toFloat()))
        exitButton = RectangleButton(Texture("ex.png"), Point2D(myGame.weight/1.7.toFloat(), myGame.height/3.25.toFloat()))
        soundButton = Button(Texture("sounds.png"),
                Point2D(myGame.weight - Texture("sounds.png").width*2.toFloat(),
                        Texture("sounds.png").height*2.toFloat()))
        backTexture = Texture("matrix.jpg")
        backSprite = Sprite(backTexture)
        backSprite.setSize(myGame.weight.toFloat(), myGame.height.toFloat())
        backSprite.setPosition(0f, 0f)
    }
    fun touch(x:Float, y:Float, isDownTouch: Boolean, pointer: Int){
        for (i in 0..5){
            startButton.onClick(x, y, isDownTouch, pointer)
            exitButton.onClick(x, y, isDownTouch, pointer)
            soundButton.update(x, y, isDownTouch, pointer)
        }
        if (isDownTouch){
            startButton.img = Texture("start_press.png")
            soundButton.img = Texture("sound_presss.png")
            exitButton.img = Texture("ex_press.png")
        }
    }
    fun mainUpdate(){
        if (startButton.click)
            myGame.screen = GameScreen(myGame)

        if (soundButton.click){

        }
        if (exitButton.click)
            exitProcess(0)
    }
    fun mainRender(batch: SpriteBatch){
        backSprite.draw(batch)
        startButton.drawer(batch)
        soundButton.draw(batch)
        exitButton.drawer(batch)
        name(batch)
    }
    fun name(batch: SpriteBatch){
        var gen = FreeTypeFontGenerator(Gdx.files.internal("juice.ttf"))
        var p = FreeTypeFontGenerator.FreeTypeFontParameter()
        p.size = myGame.weight/15
        p.color = Color(1f, 0.5f, 0f, 1f)
        var font = gen.generateFont(p)
        var gl = GlyphLayout()
        gl.setText(font, "Fix yourself")
        font.draw(batch, gl, myGame.weight/2 - gl.width/2, myGame.height- gl.height)
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        touch(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat(), false, pointer)
        return false
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
        touch(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat(), true, pointer )
        return false
    }
}