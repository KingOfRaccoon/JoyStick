package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.screen.GameScreen

class MyGame: Game() {
    lateinit var img: Texture // текстура
    lateinit var batch: SpriteBatch // отрисовчик
    lateinit var circle: Texture
    lateinit var actor: Texture
    var weight = 0
    var height = 0


    override fun create() { // вызывается при запуске, здесь идёт загрузка ресурсов
        batch = SpriteBatch()
        weight = Gdx.graphics.width
        height = Gdx.graphics.height
        img = Texture("badlogic.jpg")
        circle = Texture("circle1.png")
        actor = Texture("round.png")
        setScreen(GameScreen(this))
    }

    override fun dispose() {//выгрузка ресурсов
        batch.dispose() // выгрузка из памяти отрисовчика
        img.dispose() // выгрузка из памяти текстуры
        circle.dispose()
        actor.dispose()
    }
}