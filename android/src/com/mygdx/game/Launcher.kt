package com.mygdx.game

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import com.badlogic.gdx.backends.android.AndroidApplication

class Launcher:AndroidApplication(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        initialize(MyGame())
    }
}