package com.example.guess2

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.guess2.data.GameDatabase
import com.example.guess2.data.Record
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

//     insert to room表格
 //    room 測試用
            Thread(){GameDatabase.getInstance(this)?.
                recordDao()?.
                insert(Record(nick,count))}.
                start()
//拉出來執行 GameDatabase的getInstance(context是自己)?.recordDao()?.insert(Record(nick,count)}.start()
/*   課程23 原先的程式碼
            val database = Room.databaseBuilder(this, GameDatabase::class.java,"game.db").build()
//        定義一個不變的database，他是能執行Room的databaseBuilder
            val record = Record(nick,count)
//        建立一個測試的資料名稱
            Thread(){database.recordDao().insert(record)}.start()
//    原先database.recordDao().insert(record)的方式較為耗時或複雜，所以利用其他的執行序Thread(){}.start()的方式將耗時的工作拉出來執行
*/

            var intent = Intent()
//            建立一個新的intent
            intent.putExtra("NICK",nick)
//            將這個intent傳入額外的資料稱為為"NICK""，是nick的物件
            setResult(Activity.RESULT_OK,intent)
//        當按下ok之後回傳資料結果
            finish()
//        結束這個Activity並回復到前一個Activity
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
