package com.example.mystartup.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystartup.R
import kotlinx.android.synthetic.main.except_map_fragment.*
import kotlinx.android.synthetic.main.google_map.*

class ListAndDetailFragment(val cafeActivity: CafeActivity) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.except_map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cafeList = ArrayList<CafeInfo>()
        for (i in 0 until 20)
            cafeList.add(
                CafeInfo(
                    "http://job.seoul.go.kr/www/common/img.jsp?dir=jobcafe&name=MIN_0559_1.jpg"
                    , "CAFE" + i
                    , "info info info" + i
                    , "강서구" + i
                )
            )
        with(cafe_recycler_view) {
            this.adapter = CafeRecyclerAdapter(
                cafeActivity,
                LayoutInflater.from(cafeActivity),
                cafeList
            )
            this.layoutManager = LinearLayoutManager(cafeActivity)
        }




    }
}