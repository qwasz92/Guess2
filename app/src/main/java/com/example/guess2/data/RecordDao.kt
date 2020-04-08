package com.example.guess2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
//專用標識
interface RecordDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    建立一個資料，當有衝突的時候 = OnConflictStrategy.更新
   suspend fun insert(record:Record)
//    如果加上suspend代表此方法可以使用Coroutines，增加新資料

    @Query(value = "select * from record")
//    查詢語法，用字串"select * from 什麼表格"
    suspend fun getAll():List<Record>
//    如果加上suspend代表此方法可以使用Coroutines，取得全部資料，不用給參數，我給你集合的方式回傳
}