package com.mygdx.game

import android.os.Bundle
import android.os.PersistableBundle
import com.badlogic.gdx.backends.android.AndroidApplication

class Launcher:AndroidApplication(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(MyGame())
    }
}