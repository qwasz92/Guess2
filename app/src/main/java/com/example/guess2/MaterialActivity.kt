package com.example.guess2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    //建立MainActivity 繼承 AppCompatActivity()
    val secretNumber=SecretNumber()
    //    產生可以用secretNumber的類別方法
    val TAG = MaterialActivity::class.java.simpleName
    //    定義TAG = MaterialActivity的類別名稱
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this).
//      對話框的Builder模式(在哪顯示)
                setTitle("Replay game").
//      標題
                setMessage("Are you sure").
//      訊息
/*                setPositiveButton(getString(R.string.ok),null).
        按鈕("文字內容",之後不做任何事)*/
                setPositiveButton(getString(R.string.ok),{dialog, which -> secretNumber.reset()
 //    對話框裡面的是否選項，並給予{}方式 dialog, 選擇which 哪個方法 ->  secretNumber.reset()
                counter.setText(secretNumber.count.toString())
//      設計counter的文字顯示
                    number.setText("")}).
//     文字方塊重製
                setNegativeButton("Cancel",null).
//     按鈕否的設定並且給予null(不做任何事情)
                show()
//            顯示
        }

            }
    fun check (view: View)
//    建立一個check方法 (view物件:View類別)
    {
        val n = number.text.toString().toInt()
//      定義一個n = number為text的字串轉數字
        println("number:${n}")
//       可使用println簡單打印出文字
        Log.d(TAG,"number:"+n)
//        Log除錯專用的d (tag是""哪個地方出來的  ,  msg是將""訊息印出來)\
        val diff = secretNumber.validate(n)
//        定義diff為secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)
//        定義message 為""裡面的文字
        if (diff<0) {
/*     判斷式if secretNumber裡面的validate方法 ,把n船進去是否<0 原先if判斷 ->(secretNumber.validate(n)
        變更為if(diff<0)     */
            Toast.makeText(this,R.string.bigger,Toast.LENGTH_SHORT).show()
//      提示訊息Toast 印出make裡面的文字(context為位置,text為文字內容,顯示為短暫)顯示出來
            message=getString(R.string.bigger)
//      簡化提示訊息
        }else if (diff>0){
            message = getString(R.string.smaller)
//      簡化提示訊息
        }else{Toast.makeText(this,getString(R.string.yes_you_got_it),Toast.LENGTH_SHORT).show()
//      正常if else 用法以及Toast正常用法
        }
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
//簡化提示訊息及else
        counter.setText(secretNumber.count.toString())
//        設計counter的文字顯示
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok),null)
            .show()
//      對話框的Builder模式(在哪顯示)標題、訊息、按鈕("文字內容",之後不做任何事)、顯示
    }

}
