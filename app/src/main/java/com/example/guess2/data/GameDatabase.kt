package com.example.guess2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Record::class),version = 1)
//標示，物件實體要怎樣儲存，利用集合存取在Record，版本1
abstract class GameDatabase:RoomDatabase()
//抽象類別的class
{
    abstract fun recordDao():RecordDao
//利用抽象的方法取得Dao的物件
/*Room Database 在使用的時候要小心，
原先Material利用RoomDatabaseBuilder來產生一個database物件來新增資料
我們在Record也同樣一個物件再新增資料
如果有兩個同樣的物件同時使用存取資料庫的時候，程式會出錯
所以在程式設計有一個模式，確保只有同一時間只有一個物件使用，稱為Singleton(單一物件)
原先要產生一個物件需要context，而我們傳入this
            val database = Room.databaseBuilder(this, GameDatabase::class.java,"game.db").build()
            val record = Record(nick,count)
            Thread(){database.recordDao().insert(record)}.start()
* */
    companion object
//在這裏面的物件都是單一類別的物件方法
{
        private var instance : GameDatabase? = null
//    設計一個private var instance，代表是是GameDatabase? 有肯能是null
        fun getInstance(context : Context):GameDatabase? {
//    設計一個開放的方法getInstance，對方呼叫我的時候我要給他的物件(context : Context)回傳GameDatabase?
            if (instance == null){
//                如果instance是null
                instance = Room.databaseBuilder(context,GameDatabase::class.java,"game.db").build()
//                給他Room.databaseBuilder(人家傳進來的,我這個類別的class.java,檔名).build()
            }
            return instance
//    回傳instance
        }
    }
}