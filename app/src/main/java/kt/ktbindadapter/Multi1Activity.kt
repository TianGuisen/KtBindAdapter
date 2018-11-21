package kt.ktbindadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import kotlinx.android.synthetic.main.activity_sigle1.*
import kt.ktbindadapter.baseAdapter.MultiItem
import kt.ktbindadapter.baseAdapter.SimpleMultiAdapter
import kt.ktbindadapter.bean.ItemInfo
import tgs.kttool.util.ToastUtil

class Multi1Activity : AppCompatActivity() {
    val itemInfos = mutableListOf<MultiItem<ItemInfo>>()

    companion object {
        val TYPE0 = 0
        val TYPE1 = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi1)
        initAdapter()
        loadData()
    }

    private fun initAdapter() {
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val multiAdapter = SimpleMultiAdapter(itemInfos)
        //添加布局
        multiAdapter.addItemType(TYPE0, R.layout.item_type0)
        multiAdapter.addItemType(TYPE1, R.layout.item_type1)
        //
        multiAdapter.setOnItemClickLisener { multiItem: MultiItem<ItemInfo>, view: View, i: Int ->
            ToastUtil.normal("点击的是item:" + multiItem + "  位置:" + i)
        }
        rv.setAdapter(multiAdapter)
    }

    private fun loadData() {
        for (i in 0..20) {
            if (i % 4 == 0) {
                itemInfos.add(MultiItem(TYPE0, ItemInfo("a", 1)))
            }
            itemInfos.add(MultiItem(TYPE1, ItemInfo("b", 1)))
        }
    }

}
