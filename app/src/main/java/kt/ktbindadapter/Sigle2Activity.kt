package kt.ktbindadapter

import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import kotlinx.android.synthetic.main.activity_sigle1.*
import kt.ktbindadapter.baseAdapter.BaseSigleAdapter
import kt.ktbindadapter.baseAdapter.BindViewHolder
import kt.ktbindadapter.bean.ItemInfo
import kt.ktbindadapter.databinding.ItemSigle2Binding
import tgs.kttool.util.ToastUtil

class Sigle2Activity : AppCompatActivity() {
    val listData = ObservableArrayList<ItemInfo>()

    init {
        for (i1 in 0..9) {
            listData.add(ItemInfo("name" + i1, i1))
        }

    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigle2)
        //单type的rv,没有item子view的点击事件,使用只需要创建SimpleAdapter
        val sigle2Adapger= object : BaseSigleAdapter<ItemInfo, ItemSigle2Binding>(listData, R.layout.item_sigle2){
            override fun decorator(bean: ItemInfo, holder: BindViewHolder<ItemSigle2Binding>, position: Int) {
                holder.binding.run {
                    tvName.setOnClickListener({ v -> childClickLisener!!.childClick(bean, tvName, position) })
                    tvAge.setOnClickListener({ v -> childClickLisener!!.childClick(bean, tvAge, position) })
                }
            }
        }
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv.setAdapter(sigle2Adapger)
        
        sigle2Adapger.setOnItemClickLisener { itemInfo, view, i ->
            ToastUtil.info("点击的是item" + itemInfo)
        }
        sigle2Adapger.setChildClickLisener { itemInfo: ItemInfo?, view: View, i: Int ->
            if (view.id == R.id.tv_name) {
                ToastUtil.success("点击的是子item中的tv_name")
            } else if (view.id == R.id.tv_age) {
                ToastUtil.success("点击的是子item中的tv_age")
            }
        }
    }
}
