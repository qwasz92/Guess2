package com.example.guess2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guess2.data.Record
import kotlinx.android.synthetic.main.row_record.view.*

class RecordAdapter (var records:List<Record>): RecyclerView.Adapter<RecordViewHolder>() {
//     建立RecordAdapter (定義records是一個List集合<裡面有一個Record>)繼承了RecyclerView.Adapter<RecordViewHolder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
    //        當onCreateViewHolder生出來之後要給我什麼
    return RecordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_record,parent,false))
//    回傳 RecordViewHolder，是LayoutInflater中的from(parent.context)，使用inflate方法取得(R.layout.row_record,parent,false))
    }

    override fun getItemCount(): Int {
//        裡面有幾筆資料
        return records.size
//        回傳functions有多少資料
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int)
//        當有資料的時候
    {
     holder.nickText.text = records.get(position).nickname
//     物件holder有nickText的text = 從records裡面取得.get(position)的nickname
     holder.counterTect.text = records.get(position).counter.toString()
//     物件holder有counterTect的text = 從records裡面取得.get(position)的.counter轉換為.toString()
    }

}
class RecordViewHolder(view: View):RecyclerView.ViewHolder(view){
//    建立RecordViewHolder(裡面建構值為view):繼承之前的RecyclerView.ViewHolder(view)
    var nickText = view.record_nickname
//    建立nickText物件為view，名稱為record_nickname
    var counterTect = view.record_counter
//    建立counterTect物件為view，名稱為record_counter
}