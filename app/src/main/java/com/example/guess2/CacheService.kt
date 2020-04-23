package com.example.guess2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class CacheService() :Service(){
//建立名稱為CacheService 繼承Service
    private val TAG = CacheService::class.java.simpleName
//建立TAG的名稱訊息

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//啟動執行
        Log.d(TAG, "onStartCommand ");
//除錯內容onStartCommand
        return START_STICKY
//回傳後自己再生出來
    }

    override fun onCreate() {
//當程式執行的時候
        super.onCreate()
        Log.d(TAG, "onCreate ");
//除錯內容onCreate
    }

    override fun onDestroy() {
// 結束程式
        super.onDestroy()
        Log.d(TAG, "onDestroy ");
//除錯內容onDestroy
    }

    override fun onBind(intent: Intent?): IBinder? {
//綁定程式
    return null
//回傳直為空值
}
}