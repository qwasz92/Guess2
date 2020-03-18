package com.example.guess2.data

import android.provider.ContactsContract
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
//儲存位置實體
class Record (
    @NonNull
//    不能沒有值
    @ColumnInfo(name = "nick")
//    列訊息名稱叫做nick
    var nickname:String,
//    定義nickname為字串
    @NonNull
//    不能沒有值
    var counter :Int
//    定義counter為數字值
) {
    @PrimaryKey(autoGenerate = true)
//    首要的關鍵值
    var id: Long = 0
//    定義id為數字Long 為0
}
@Entity
//儲存位置實體

class Word {
    @PrimaryKey
//    首要的關鍵值
    var name: String = ""
//    定義name為字串空值
    var means: String = ""
//    定義means為字串空值
    var star: Int = 0
//    定義star為數字0
}