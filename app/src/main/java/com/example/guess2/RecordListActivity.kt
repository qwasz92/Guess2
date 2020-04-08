package com.example.guess2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guess2.data.GameDatabase
import kotlinx.android.synthetic.main.activity_record_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecordListActivity : AppCompatActivity() ,CoroutineScope{
//    增加實作CoroutineScope
    private lateinit var job: Job
//    給予一個屬性物件稱為job是Job的工作
    override val coroutineContext: CoroutineContext
//        實作屬性
        get() = job + Dispatchers.Main
//    在CoroutineContext的物件上面給予get() = job + Dispatchers的Main的執行序
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)
        job = Job()
//        初始化這個job的工作
        //     如何取得get records
        launch {
//        因已經繼承了CoroutineScope這個執行序，所以直接使用launch的實作方式
            val records = GameDatabase.getInstance(this@RecordListActivity)?.recordDao()?.getAll()
            records?.let {
                //      如果records不是null執行.let 以下內容{
                runOnUiThread {
                    //      使用runOnUi的Thread 執行序{
                    recycler.layoutManager = LinearLayoutManager(this@RecordListActivity)
//      使用recycler的layoutManager = 取得LinearLayoutManager(因為已經變成CoroutineScopr所以context會變成目前的名稱的RecordListActivity)
                    recycler.setHasFixedSize(true)
//      使用recycler的固定大小
                    recycler.adapter = RecordAdapter(it)
//      使用recycler的adapter在RecordAdapter(裡面的資料)
                }
            }
/*
        課程28 將繼承的畫面增加繼承的介面CoroutineScope介面，主要做更改呼叫的方式
        CoroutineScope(Dispatchers.IO).launch {
//        協程(情境.耗時的意思).程式區塊
            val records = GameDatabase.getInstance(this@RecordListActivity)?.recordDao()?.getAll()
//      建立一個records，是從GameDatabase的資料，並取得getInstance(因為已經變成CoroutineScopr所以context會變成目前的名稱的RecordListActivity)資料(裡面有肯能是null)並使用recordDao(裡面有肯能是null)裡的.getAll()方法
            records?.let {
                //      如果records不是null執行.let 以下內容{
                runOnUiThread {
                    //      使用runOnUi的Thread 執行序{
                    recycler.layoutManager = LinearLayoutManager(this@RecordListActivity)
//      使用recycler的layoutManager = 取得LinearLayoutManager(因為已經變成CoroutineScopr所以context會變成目前的名稱的RecordListActivity)
                    recycler.setHasFixedSize(true)
//      使用recycler的固定大小
                    recycler.adapter = RecordAdapter(it)
//      使用recycler的adapter在RecordAdapter(裡面的資料)
                }
            }
/*課程27 因為加入suspend，所以原先的getAll不能使用，所以使用CoroutineScope
AsyncTask.execute {
//      利用AsyncTask的execute方法
    val records = GameDatabase.getInstance(this)?.recordDao()?.getAll()
//      建立一個records，是從GameDatabase的資料，並取得getInstance(this)資料(裡面有肯能是null)並使用recordDao(裡面有肯能是null)裡的.getAll()方法
    records?.let {
//      如果records不是null執行.let 以下內容{
    runOnUiThread {
//      使用runOnUi的Thread 執行序{
    recycler.layoutManager = LinearLayoutManager(this)
//      使用recycler的layoutManager = 取得LinearLayoutManager(this)
    recycler.setHasFixedSize(true)
//      使用recycler的固定大小
    recycler.adapter = RecordAdapter(it)
//      使用recycler的adapter在RecordAdapter(裡面的資料)
}
}
}*/
}*/
}
}
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
//        清除job的活動
    }
}
