package com.example.guess2

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
/*課程33 原先程式碼
class CacheService() :Service() {

override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//啟動執行
        Log.d(TAG, "onStartCommand ");
//除錯內容onStartCommand
        return START_STICKY
//回傳後自己再生出來
    }
//onHandleIntent 與 onStartCommand不能同時出現，不然只會出現onStartCommand
IntentService好用的地方：
假如未來耗時工作製作在這裡，程式不斷產生或啟動，他會排隊等待啟用。
例如聊天室、訊息、下載等(主要是操作者在執行程式的時候，某些送出的訊息不能亂跳)
優勢：
1.執行完會自己結束
2.會自己排隊執行
3.用另外的執行序來執行程序
*/
class CacheService() :IntentService("CacheService") {
//    繼承IntentService並且要給他字串值("CacheService")
    override fun onHandleIntent(intent: Intent?) {
// 處理意圖
        Log.d(TAG, "onHandleIntent ");
//除錯內容onHandleIntent
        Thread.sleep(5000)
//製作一個暫停5秒鐘後等待再次執行
    }
//建立名稱為CacheService 繼承Service
    private val TAG = CacheService::class.java.simpleName
//建立TAG的名稱訊息

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