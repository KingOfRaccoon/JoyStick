package com.mygdx.game.tools

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.actors.Item

object Any {
    var playersItem = mutableListOf<Item>()
    var items: MutableList<Item> = mutableListOf(
            Item(Texture("coin.png"), "Coin", Point2D(Gdx.graphics.width/4*3.toFloat(), Gdx.graphics.height/2.toFloat())),
            Item(Texture("strelka.png"), "Strelka", Point2D(Gdx.graphics.width/4.toFloat(), Gdx.graphics.height/2.toFloat())))
    var playerPosition : Point2D? = null
    var map = Map()
}