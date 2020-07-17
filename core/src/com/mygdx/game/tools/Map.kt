package com.mygdx.game.tools

import kotlin.random.Random


class Map() {
    var nodes = mutableListOf<Node>()
    var lines = mutableListOf<Line>(Line(0,0))

    init {
        lines.clear()
    }

    fun createMap() : Map // функция создает граф и возвращает класс, содержащий массив нейронов и массив соединений
    {
        val MAXGENERATIONS = 5;  // максимальная длина графа
        val NUMBEROFCHILDS = 3;  // максимальная ширина графа
        val DISTANCEOFCREATING = 160; // дистанция между соседями при создании
        val DISTANCEOFCOLLISION = 120; // минимальная дистанция между двумя нейронами
        val DISTANCEOFNEWLINE = 160; // дистанция, при которой есть шанс создания нового соединения
        val CHANCEOFNEWLINE = 20; // шанс создания нового соединения
        var gd : Map = Map() // создается экземпляр класса, содержащий массив нейронов и массив соединений
        var iter : Int = 0; // итератор
        while (gd.nodes.size > iter) // цикл перебора каждого элемента в динамически создаваемом стаке
        {
            if (gd.nodes[iter].g < MAXGENERATIONS) // если не достигли максимальной длины
            {
                for (nevermind in 1..NUMBEROFCHILDS) { // создаем NUMBEROFCHILDS детей нейрона
                    var rand: Double = Random.nextInt(1, 359) * 0.0174533; // случайный угол в радианах
                    var x = gd.nodes[iter].x + (Math.cos(rand) * DISTANCEOFCREATING).toInt(); // получаем икс из угла
                    var y = gd.nodes[iter].y + (Math.sin(rand) * DISTANCEOFCREATING).toInt(); // получаем игрек из угла

                    var isOk : Boolean = true; // проверка на близость двух нейронов
                    for (n : Node in gd.nodes) { // для каждого уже существующего нейрона
                        if (Math.abs(x - n.x) + Math.abs(y - n.y) < DISTANCEOFCOLLISION) { // если достаточно близко
                            isOk = false; // не создаем
                            break; // выходим из цикла
                        }
                    }
                    if (isOk) // если нейрон находится достаточно далеко от других нейронов
                    {
                        for ((index, n) in gd.nodes.withIndex()) { // для каждого уже существующего нейрона
                            if (Math.abs(x - n.x) + Math.abs(y - n.y) < DISTANCEOFNEWLINE && Random.nextInt(0, 100) < CHANCEOFNEWLINE) {
                                // если достаточно близко и везет
                                gd.lines.add(Line(index,gd.nodes.size)) // добавляем соединение
                            }
                        }
                        gd.nodes.add(Node(x, y, gd.nodes[iter].g + 1)) // добавляем нейрон в стак (икс, игрек, поколение + 1)
                        gd.lines.add(Line(iter,gd.nodes.size-1)) // добавляем соединение с материнским нейроном
                    }
                }
            }
            iter++; // итератор
        }

        return gd; // возвращаем Data
    }
}