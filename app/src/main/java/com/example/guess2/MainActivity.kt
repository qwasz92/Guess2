package com.example.guess2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_function.view.*

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    //RecyclerView (清單元件)
    val functions = listOf<String>(
        "Camera",
        "Guess game",
        "Record list",
        "Download coupons",
        "News",
        "Map")
//        建立一個不可變的functions，他是一個list的集合<他是一個字串>(名稱為)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(this)
//        recycler是一個清單訊息 = 清單式的編排元件(this)
        recycler.setHasFixedSize(true)
//        recycler是一個固定大小的(true)
        recycler.adapter = FunctionAdapter()
//        recycler適合的連結器是FunctionAdapter
    }
    inner class FunctionAdapter():RecyclerView.Adapter<FunctionHolder>()
//    內部的類別FunctionAdapter繼承RecyclerView.Adapter<是使用FunctionHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionHolder
//        當onCreateViewHolder生出來之後要給我什麼
        {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.row_function,parent,false)
//         view物件是使用LayoutInflater的parent.context，使用inflate方法取得R.layout.row_function,parent,假值
         val holder = FunctionHolder(view)
//         建立holder 是一個FunctionHolder的view物件
         return holder
//         回傳holder
        }

        override fun getItemCount(): Int {
//        裡面有幾筆資料
          return functions.size
//        回傳functions有多少資料
        }

        override fun onBindViewHolder(holder: FunctionHolder, position: Int)
//        當有資料的時候
        {
        holder.nameText.text = functions.get(position)
//      物件holder有個nameText的text = functions裡面取得的位置
        }

    }
    class FunctionHolder(view : View) : RecyclerView.ViewHolder(view)
//    一個類別FunctionHolder是一個(view:View):繼承了RecyclerView.ViewHolder的(view)物件
    {
     var nameText:TextView = view.name
//    設計nameText是一個TextView 是在view物件的name
    }
}
