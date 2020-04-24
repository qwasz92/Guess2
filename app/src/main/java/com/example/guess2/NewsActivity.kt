package com.example.guess2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
//定義fragmentTransaction為supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container,NewsFragment.instance)
//建立一個fragmentTransaction的add方法加入倒(R.id.container,NewsFragment.instance)
        fragmentTransaction.commit()
//呼叫fragmentTransaction並執行出來
    }
}
