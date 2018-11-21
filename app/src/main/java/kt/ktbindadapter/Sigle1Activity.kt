package kt.ktbindadapter

import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_sigle1.*
import kt.ktbindadapter.baseAdapter.SimpleAdapter
import kt.ktbindadapter.bean.ItemInfo
import tgs.kttool.util.ToastUtil

class Sigle1Activity : AppCompatActivity() {
    val listData = ObservableArrayList<ItemInfo>()

    init {
        for (i1 in 0..9) {
            listData.add(ItemInfo("name" + i1, i1))
        }

    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigle1)
        //最普通的rv,直接创建SimpleAdapter即可,没有item子view的点击事件
        val testAdapter = SimpleAdapter(listData, R.layout.item_sigle1)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv.setAdapter(testAdapter)
        
        testAdapter.setOnItemClickLisener { itemInfo, view, i ->
            ToastUtil.normal("点击的是item" + itemInfo)
        }
    }
}
