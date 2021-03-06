package com.example.guess2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.guess2.data.GameDatabase
import com.example.guess2.data.Record

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    //建立MainActivity 繼承 AppCompatActivity()
    private val REQUEST_RECORD = 100
    //定義 REQUEST_RECORD=  100
    val secretNumber=SecretNumber()
    //    產生可以用secretNumber的類別方法
    val TAG = MaterialActivity::class.java.simpleName
    //    定義TAG = MaterialActivity的類別名稱
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate ");
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

    fab.setOnClickListener { view ->
        replay()
//    方法為重新開始

}
        counter.setText(secretNumber.count.toString())
        //      設計counter的文字顯示
        Log.d(TAG, " onCreate: "+secretNumber.secert);
//        增加Log 的secert隱藏數字顯示
        val count =getSharedPreferences("guess",Context.MODE_PRIVATE)
        .getInt("REC_COUNTER",-1)
/*如果開啟後想讀取資料count的資料，可使用getSharedPreferences，檔案名稱要一樣，並MODE的PRIVATE。
直接取得檔案的getInt並找出記錄名稱(不能與一開始設定的名稱不一樣)，若檔案內沒有資料可以給他-1*/
        val nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME",null)
/*如果開啟後想讀取資料nike的資料，可使用getSharedPreferences，檔案名稱要一樣，並MODE的PRIVATE。
直接取得檔案getString並找出記錄名稱(不能與一開始設定的名稱不一樣)，若檔案內沒有資料可以給他-1*/
        Log.d(TAG, "data:$count/$nick");
//        設定Log.d除錯(活動名稱 "名稱 +cont/ +nick")
//Room讀取資料
/*課程27 因為加入suspend，所以原先的getAll不能使用
        AsyncTask.execute {
//這裡不使用利用Thread(){}的方法，我們用Java的AsyncTask的execute(快速使用的方法)將下列方式顯示出來，並不會影響到UI的順暢度
    val list = GameDatabase.getInstance(this)?.recordDao()?.getAll()
//利用list集合建立一個GameDatabase calss的getInstance資料利用recordDao裡面的getAll方法呼叫資料
        list?.forEach {
//利用for迴圈把list資料印出來
            Log.d(TAG, "record:${it.nickname} ${it.counter} ");
//檢查所有forEach(稱為it)裡面的資料
        }
        }*/
    }

/*
//    room 測試用
    val database = Room.databaseBuilder(this,GameDatabase::class.java,"game.db").build()
//        定義一個不變的database，他是能執行Room的databaseBuilder
    val record = Record("Jack",3)
//        建立一個測試的資料名稱
        Thread(){database.recordDao().insert(record)}.start()
//    原先database.recordDao().insert(record)的方式較為耗時或複雜，所以利用其他的執行序Thread(){}.start()的方式將耗時的工作拉出來執行
    }*/
/*    Activity 的生命週期
開始啟用 -> onCreate-> onCreate: 7 (秘密數字) -> data:1/null (data資料) ->onStart -> onResume
開始猜數字  -> 第一次猜 number:8 -> 第二次猜 number:6 -> 第三次猜 number:7(猜對了)
猜對了切換Acitvity -> onPause -> onStop -> 顯示下一個Acitvity
當按下返回鍵之後 -> onRestart -> onStart -> onResume
當不玩了，跳回桌面之後 -> onPause -> onStop -> onDestroy
* */

/* 課程22 Room 新增紀錄案例實作
Terminal 模擬器(要先開啟APP模擬程式，且先要有執行紀錄)
先找到SDK Location 的資料位置 C:\Users\lovev\AppData\Local\Android\Sdk 複製起來
進入模擬器輸入 cd  +空白 +資料位置
進入後再輸入 cd +空白 +platform-tools
進入後再執行adb指令(要先確認能不能使用)
能使用adb指令後打入 adb + shell
進入小型模擬器之後 輸入su (模擬器需選擇Google APIs)
檢查資料有沒有進入 請輸入ls
cd + 資料位置(例如:cd /data/data/com.example.guess2)
cd +databases
進入後在執行開啟
sqlite3 (執行指令)
sqlite3 +檔案名稱(例如:sqlite3  game.db)
進入後在執行.tables
開啟檔案內容 select*from +檔案內容; (例如:select*from Record;)
檢查是否有資料產生出來
1|Jack|3
2|Jack|3
3|Jack|3
* */



    private fun replay() {
        AlertDialog.Builder(this).
            //      對話框的Builder模式(在哪顯示)
            setTitle("Replay game").
            //      標題
            setMessage("Are you sure").
            //      訊息
            /*                setPositiveButton(getString(R.string.ok),null).
                    按鈕("文字內容",之後不做任何事)*/
            setPositiveButton(getString(R.string.ok), { dialog, which ->
                secretNumber.reset()
                //    對話框裡面的是否選項，並給予{}方式 dialog, 選擇which 哪個方法 ->  secretNumber.reset()
                counter.setText(secretNumber.count.toString())
                //      設計counter的文字顯示
                number.setText("")
            }).
            //     文字方塊重製
            setNegativeButton("Cancel", null).
            //     按鈕否的設定並且給予null(不做任何事情)
            show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart ");
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop ");

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause ");
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume ");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart ");

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy ");
    }

    fun check (view: View)
//    建立一個check方法 (view物件:View類別)
    {
        val n = number.text.toString().toInt()
//      定義一個n = number為text的字串轉數字
        println("number:${n}")
//       可使用println簡單打印出文字
        Log.d(TAG,"number:"+n)
//        Log除錯專用的d (tag是""哪個地方出來的  ,  msg是將""訊息印出來)\
        val diff = secretNumber.validate(n)
//        定義diff為secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)
//        定義message 為""裡面的文字
        if (diff<0) {
/*     判斷式if secretNumber裡面的validate方法 ,把n船進去是否<0 原先if判斷 ->(secretNumber.validate(n)
        變更為if(diff<0)     */
      Toast.makeText(this,R.string.bigger,Toast.LENGTH_SHORT).show()
//      提示訊息Toast 印出make裡面的文字(context為位置,text為文字內容,顯示為短暫)顯示出來
      message=getString(R.string.bigger)
//      簡化提示訊息
        }else if (diff>0){
            message = getString(R.string.smaller)
//      簡化提示訊息
        }else{Toast.makeText(this,getString(R.string.yes_you_got_it),Toast.LENGTH_SHORT).show()
//      正常if else 用法以及Toast正常用法
        }
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
//簡化提示訊息及else
        counter.setText(secretNumber.count.toString())
//        設計counter的文字顯示
        AlertDialog.Builder(this)
//     對話框的Builder模式(在哪顯示)
      .setTitle(getString(R.string.dialog_title))
//     標題
      .setMessage(message)
//       訊息
      .setPositiveButton(getString(R.string.ok),{dialog, which ->
          if (diff == 0){
       val intent = Intent(this,RecordActivity::class.java)
       intent.putExtra("COUNTER",secretNumber.count)
//      設定intent的方法傳出資料到RecordActivity，使用putExtra傳遞"資料名稱" 並給予哪邊的資料值
/*      startActivity(intent)
按鈕 if() 判斷式，當成功答對的時候(diff 等於0)，執行Intent資料傳輸轉換到第二個Activity(RecordActivity)
    並透過執行startActivity呼叫Intent物件*/
              startActivityForResult(intent,REQUEST_RECORD)
//      除了要告訴intent這個方法，你還要告訴我這個動作的號碼 (REQUEST_RECORD)
}
}).show()
//     顯示
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        方法為傳入Activity結果值
        super.onActivityResult(requestCode, resultCode, data)
//        方法onActivityResult
    if (requestCode == REQUEST_RECORD) {
//        如果是正常結束requestCode == REQUEST_RECORD執行
            if (resultCode == Activity.RESULT_OK)
//            如果是的話我要執行
        {
            val nickname = data?.getStringExtra("NICK")
//        建立一個nickname 的data類型的資料且有肯能事空值，並把將代號NICK的字串內容傳入近來
            Log.d(TAG, ":onActivityResult :${nickname}");
//        增加Log 的nickname 的data資料
            replay()
//方法 重新開始
            }
        }
    }
}
