package com.example.guess2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class NewsFragment :Fragment(){
    companion object{
//伴隨的夥伴
    val instance :NewsFragment by lazy { NewsFragment() }
//建立不變的instance是一個NewsFragment不要一開始就出現NewsFragment()的物件
    }

    override fun onCreateView(
//創建圖示
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news,container,false)
//        利用 inflater.呼叫inflate方法(R.layout.fragment_news,container物件,false)
    }
}