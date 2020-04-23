package com.example.guess2

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_function.view.*
import java.util.jar.Manifest
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private val REQUEST_COODE_CAMERA=100
//定義REQUEST_COOD_CAMERA為100
    val TAG = MainActivity::class.java.simpleName
//定義 TAG 為MainActivity物件
    var cacheService:Intent?= null
//定義物件為null值
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
// 方法functionClicked判斷position的值
        when (position){
// 判斷position
            0-> {
                val permission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
//定義permission 的危險權限
                if (permission == PackageManager.PERMISSION_GRANTED){
//判斷危險權限是否 == PackageManager.PERMISSION_GRANTED
                takePhoto()
//執行takePhoto
                }else{
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),REQUEST_COODE_CAMERA)
//其他就執行對話框ActivityCompat.requestPermissions(在這裡,透過arrayOf(詢問的對權限字串),是否允許的值)
                }
            }
// 當按下0的位置執行intent物件
            1 -> startActivity(Intent(this,MaterialActivity::class.java))
// 當按下1的位置執行Intent的Activity(this,MaterialActivity::class.java))
            2 -> startActivity(Intent(this,RecordListActivity::class.java))
// 當按下2的位置執行Intent的Activity(this,RecordListActivity::class.java))
            else -> return
// 其他的回傳
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
// 回傳的第一個requestCode
        permissions: Array<out String>,
//字串值陣列
        grantResults: IntArray
//結果
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_COODE_CAMERA){
//如果使用者按下的是requestCode == REQUEST_COODE_CAMERA
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
//如果是grantResults[0] == PackageManager.PERMISSION_GRANTED
                takePhoto()
//執行takePhoto
            }
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // 定義intent物件為MediaStore.ACTION_IMAGE_CAPTURE
        startActivity(intent)
//        執行
    }

    class FunctionHolder(view : View) : RecyclerView.ViewHolder(view)
//    一個類別FunctionHolder是一個(view:View):繼承了RecyclerView.ViewHolder的(view)物件
    {
     var nameText:TextView = view.name
//    設計nameText是一個TextView 是在view物件的name
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//當Activity要顯示的時候，會去要一個物件
        menuInflater.inflate(R.menu.menu_main_xlm,menu)
// menuInflater物件呼叫inflate(物件在R.menu.menu_main_xlm,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//當使用者觸碰OptionsItemSelected
        if (item.itemId == R.id.action_cache){
//如果item.itemId == R.id.action_cache 執行以下內容
            Log.d(TAG, "Cache selected");
//除錯內容Cache selected
            cacheService = Intent(this,CacheService::class.java)
//給予cacheService的Intent內容(this,CacheService::class.java)
            startService(cacheService)
//執行intent物件
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
//停止程式
        super.onStop()
        stopService(cacheService)
//停止Service的cacheService
    }
}
