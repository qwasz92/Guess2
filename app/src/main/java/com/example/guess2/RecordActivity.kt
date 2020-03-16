package com.example.guess2

import android.app.SearchManager
import android.content.Context
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

//      學習使用OnCancelListener
        save.setOnClickListener { view ->
        val nick = nickname.text.toString()
//    定義nick = 取得nickname的文字
        getSharedPreferences("guess", Context.MODE_PRIVATE)
//                呼叫SharedPreferences，並建立一個檔案名稱"guess"，並給予MOV常數值(今天案例使用MODE_PRIVATE)
            .edit()
//                呼叫能使用getSharedPreferences的edit
            .putInt("REC_COUNTER",count)
//                呼叫資料("建立檔案的名稱",資料count)
            .putString("REC_NICKNEMA",nick)
//                呼叫字串("建立檔案的名稱",字串位置nick)
            .apply()
/*            馬上使用的時候就使用commit()，如果並不是立馬使用可以用 .apply()，此案例使用apply() */
        }
    }
}
/*
如何找出APP程式中所建立的 SharedPreferences
Terminal 模擬器(要先開啟APP模擬程式，且先要有執行紀錄)
先找到SDK Location 的資料位置 C:\Users\lovev\AppData\Local\Android\Sdk 複製起來
進入模擬器輸入 cd  +空白 +資料位置
進入後再輸入 cd +空白 +platform-tools
進入後再執行adb指令(要先確認能不能使用)
能使用adb指令後打入 adb + shell
進入小型模擬器之後 輸入su (模擬器需選擇Google APIs)
檢查資料有沒有進入 請輸入ls
cd + 資料位置(例如:cd /data/data/com.example.guess2)
cd +shared_prefs
cat guess.xml
檢查是否有儲存資料內容
<map>
    <int name="REC_COUNTER" value="1" />
    <string name="REC_NICKNEMA">Jack1</string>
</map>
* */
