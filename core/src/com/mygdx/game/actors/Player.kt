package com.mygdx.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.tools.Any
import com.mygdx.game.tools.Point2D
import com.mygdx.game.tools.Any.map

class Player(texture: Texture, position: Point2D, health:Float):Actors(texture, position){
    var health = health
    var radius = texture.height*8.toFloat()
    var image = texture
    var stateTime = 0f
    var animationLeft = Animation(0.041f,Texture("Sprite-0015_1.png"), Texture("Sprite-0015_2.png"),
            Texture("Sprite-0015_3.png"),Texture("Sprite-0015_4.png"))
    var animationRight = Animation(0.041f, Texture("Sprite-00015.png"), Texture("Sprite-00015_2.png"),
            Texture("Sprite-00015_3.png"), Texture("Sprite-00015_4.png"))
    var animationUp = Animation(0.041f, Texture("Sprite-0016 _1.png"), Texture("Sprite-0016_2.png"),
            Texture("Sprite-0016_3.png"))
    var animationDown = Animation(0.041f, Texture("peap1.png"), Texture("peap2.png"),
            Texture("peap3.png"))
    override fun draw(batch: SpriteBatch) {
        if (direction.getX() != 0f && direction.getY() != 0f){
        when (getTestAngle() % 360f){
            in (46..135) ->{
                setAnimation(batch, animationLeft)}
            in (226..315) ->{
                setAnimation(batch, animationRight)}}
        when(getAngle()%360f){
            in (316..360) ->{
                setAnimation(batch, animationUp)}
            in (0..45) -> {
                setAnimation(batch, animationUp)}
            in (136..225) ->{
                setAnimation(batch, animationDown)}}
        }
        else{
            batch.draw(image, position.getX(), position.getY(), radius/2, radius/2 )
        }
    }
    fun setAnimation(batch: SpriteBatch, animation: Animation<Texture>){
        stateTime += Gdx.graphics.deltaTime
        var frame = animation.getKeyFrame(stateTime, true)
        batch.draw(frame, position.getX(), position.getY(), radius/2, radius/2)
    }

    override fun update() {
        if (position.getX() + radius/2 > Gdx.graphics.width)
            position.setX(Gdx.graphics.width-radius/2)
        if (position.getX() < 0)
            position.setX(0f)
        if (position.getY() + radius/2 > Gdx.graphics.height)
            position.setY(Gdx.graphics.height-radius/2)
        if (position.getY() < 0)
            position.setY(0f)
    }
}