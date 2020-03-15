package com.example.guess2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//建立MainActivity 繼承 AppCompatActivity()
    val secretNumber=SecretNumber()
//    產生可以用secretNumber的類別方法
    override fun onCreate(savedInstanceState: Bundle?) {
//        複寫 副類別 onCreate自動執行類別
    super.onCreate(savedInstanceState)
    /*        setContentView(R.layout.linear_main)
                linear_main.xml 封面顯示為此畫面
        * */
        setContentView(R.layout.activity_main)
    }
    fun check (view:View)
//    建立一個check方法 (view物件:View類別)
    {
        val n = number.text.toString().toInt()
//      定義一個n = number為text的字串轉數字
        println("number:${n}")
//       可使用println簡單打印出文字
        Log.d("MainActivity","number:"+n)
//        Log除錯專用的d (tag是""哪個地方出來的  ,  msg是將""訊息印出來)
    }
}
