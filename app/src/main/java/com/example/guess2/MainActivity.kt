package com.example.guess2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
}
