package com.example.guess2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
//spinner練習
        val colors = arrayOf("Red","Green","Blue")
//定義colors 是arrayOf的集合(內容名稱為)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colors)
//定義adapter 是  ArrayAdapter<它是一個字串>(位置在這裡，實作在android.R.layout.simple_spinner_item裡面,顯示文字為colors)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
//adapter再設定額外的資源setDropDownViewResource在(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter
//在spinner呼叫adapter =adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//在spinner裡面點擊項目onItemSelectedListener  目標 :Adapter的物件.監聽選到的項目
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
//當我按到之後onItemSelected的時候，執行甚麼  (parent: AdapterView<*>?,點的文字名稱: View?,位置: Int,名稱: Long)
                Log.d(TAG, "onItemSelected:${colors[position]} ");
//除錯onItemsSeleted是否選擇到
            }

        }
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
        holder.itemView.setOnClickListener{
//         物件holder的itemView物件被按到
            functionClicked(position)
//      執行functionClicked的方法
        }
        }

    }

    private fun functionClicked(position: Int) {
//        方法functionClicked判斷position的值
        when (position){
//            判斷position
            1 -> startActivity(Intent(this,MaterialActivity::class.java))
//            當按下1的位置執行Intent的Activity(this,MaterialActivity::class.java))
            2 -> startActivity(Intent(this,RecordListActivity::class.java))
//            當按下2的位置執行Intent的Activity(this,RecordListActivity::class.java))
            else -> return
//            其他的回傳
        }
    }

    class FunctionHolder(view : View) : RecyclerView.ViewHolder(view)
//    一個類別FunctionHolder是一個(view:View):繼承了RecyclerView.ViewHolder的(view)物件
    {
     var nameText:TextView = view.name
//    設計nameText是一個TextView 是在view物件的name
    }
}
