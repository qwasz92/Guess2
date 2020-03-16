package com.example.guess2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count = intent.getIntExtra("COUNTER",-1)
/*建立一個傳遞資料到這個Activity的定義名稱並定義它，
intent取得getIntExtra(要看你取得的資料名稱，但是今天是Int值)，
並告訴他是什麼的名稱"COUNTER"，並告訴他如果找不到資料時要給的預設值是多少(-1)*/
        counter.setText(count.toString())
//     取得counter資料值，setText(資料位置.字串)
    }
}
