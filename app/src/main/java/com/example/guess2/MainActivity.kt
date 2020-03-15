package com.example.guess2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//建立MainActivity 繼承 AppCompatActivity()
    val secretNumber=SecretNumber()
//    產生可以用secretNumber的類別方法
    override fun onCreate(savedInstanceState: Bundle?) {
//        複寫 副類別 onCreate自動執行類別
    super.onCreate(savedInstanceState)
    /*        setContentView(R.layout.linear_main)
                linear_main.xml 封面顯示為此畫面
        * */
        setContentView(R.layout.activity_main)
    Log.d("MainActivity", "secret:"+secretNumber.secert);
//    Log.d (secretNumber) 提示出secert數字
    }
    fun check (view:View)
//    建立一個check方法 (view物件:View類別)
    {
        val n = number.text.toString().toInt()
//      定義一個n = number為text的字串轉數字
        println("number:${n}")
//       可使用println簡單打印出文字
        Log.d("MainActivity","number:"+n)
//        Log除錯專用的d (tag是""哪個地方出來的  ,  msg是將""訊息印出來)\
        val diff = secretNumber.validate(n)
        var message = "Yes you got it"
        if (secretNumber.validate(n)<0) {
//      判斷式if secretNumber裡面的validate方法 ,把n船進去是否<0
        Toast.makeText(this,"Bigger",Toast.LENGTH_SHORT).show()
//      提示訊息Toast 印出make裡面的文字(context為位置,text為文字內容,顯示為短暫)顯示出來
        message="Bigger"
//      簡化提示訊息
        }else if (diff>0){
        message = "Smaller"
//      簡化提示訊息
        }else{Toast.makeText(this,"Yes you got it",Toast.LENGTH_SHORT).show()
//      正常if else 用法以及Toast正常用法
        }
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
//簡化提示訊息及else
    AlertDialog.Builder(this)
        .setTitle("Message")
        .setMessage(message)
        .setPositiveButton("OK",null)
        .show()
//      對話框的Builder模式(在哪顯示)標題、訊息、按鈕("文字內容",之後不做任何事)、顯示
    }
}
