package com.mygdx.game.tools

class CrashGenerator{
    var timeToCrash = System.currentTimeMillis()
    fun crash(){
        if ((System.currentTimeMillis() - timeToCrash)/1000 >= 10){
        var g = (0..10).random()
        if (g == 6){
            Any.map.nodes[(0 until Any.map.nodes.size).random()].crashNodes = CrashNodes.CRASH
            }
        }
    }
}