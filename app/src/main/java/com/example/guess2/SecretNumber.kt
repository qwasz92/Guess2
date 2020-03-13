package com.example.guess2

import kotlin.random.Random

class SecretNumber {
//    設計一個類別class SecretNumber
    val secert:Int = Random.nextInt(10)+1
//    定義一個不可變的secert並給它Int值 = Random(亂數) 的一個整數值(10)
    var count = 0
//    定義一個可以變得值count
    fun validate(number:Int):Int{
//    建立一個方法validate並給他一個數字值並回傳數字值
    count++
//    count+1的意思
    return number-secert
//    回傳數字-亂數
}
}

fun main() {
//    建立一個main方法
    val secretNumber=SecretNumber()
//    產生可以用secretNumber的類別方法
    println(secretNumber.secert)
//    印出secretNumber.secert值
    println(secretNumber.validate(2))
//    印出secretNumber.validate(2)的差異值
    println("${secretNumber.validate(2)},count:${secretNumber.count}")
    println("${secretNumber.validate(2)},count:${secretNumber.count}")
//    印出count猜到第幾次
}