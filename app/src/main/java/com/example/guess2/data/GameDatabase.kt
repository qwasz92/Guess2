package com.example.guess2.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Record::class),version = 1)
//標示，物件實體要怎樣儲存，利用集合存取在Record，版本1
abstract class GameDatabase:RoomDatabase()
//抽象類別的class
{
    abstract fun recordDao():RecordDao
//利用抽象的方法取得Dao的物件
}