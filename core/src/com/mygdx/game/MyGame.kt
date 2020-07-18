package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.screen.MainScreen

class MyGame: Game() {
    lateinit var batch: SpriteBatch // отрисовчик
    lateinit var joy_back: Texture
    lateinit var joy_stick : Texture
    lateinit var actor: Texture
    var weight = 0
    var height = 0


    override fun create() { // вызывается при запуске, здесь идёт загрузка ресурсов
        batch = SpriteBatch()
        weight = Gdx.graphics.width
        height = Gdx.graphics.height
        joy_back = Texture("jo_back.png")
        joy_stick = Texture("jo_face.png")
        actor = Texture("persUp.png")
        setScreen(MainScreen(this))
    }

    override fun dispose() {//выгрузка ресурсов
        batch.dispose() // выгрузка из памяти отрисовчика
        joy_stick.dispose()
        joy_back.dispose()
        actor.dispose()
    }
}