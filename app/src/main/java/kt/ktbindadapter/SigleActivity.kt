package kt.ktbindadapter

import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import kotlinx.android.synthetic.main.activity_sigle.*
import kt.ktbindadapter.adapter.SimpleAdapter
import kt.ktbindadapter.bean.ItemInfo

class SigleActivity : AppCompatActivity() {
    val listData = ObservableArrayList<ItemInfo>()

    init {
        for (i1 in 0..9) {
            listData.add(ItemInfo("name" + i1, i1))
        }

    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigle)
        //rv
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val testAdapter = SimpleAdapter(listData, R.layout.item_sigle)
        rv.setAdapter(testAdapter)
        testAdapter.setOnItemClickLisener { itemInfo, view, i ->
//            ToastUtil.info("点击的是item" + itemInfo)
        }
        testAdapter.setChildClickLisener { itemInfo: ItemInfo?, view: View, i: Int ->
            if (view.id == R.id.tv_name) {
//                ToastUtil.success("点击的是子item中的tv_name")
            } else if (view.id == R.id.tv_age) {
//                ToastUtil.success("点击的是子item中的tv_age")
            }
        }
    }
}
