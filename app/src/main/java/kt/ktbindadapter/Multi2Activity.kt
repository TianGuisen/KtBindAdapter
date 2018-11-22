package kt.ktbindadapter

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import kotlinx.android.synthetic.main.activity_sigle1.*
import kt.ktbindadapter.baseAdapter.BindViewHolder
import kt.ktbindadapter.bean.ItemInfo
import kt.ktbindadapter.databinding.ItemType1Binding
import tgs.adapter.BaseMultiAdapter
import tgs.adapter.MultiItem
import tgs.kttool.util.ToastUtil

class Multi2Activity : AppCompatActivity() {
    val itemInfos = mutableListOf<MultiItem<ItemInfo>>()

    companion object {
        val TYPE0 = 0
        val TYPE1 = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi2)
        initAdapter()
        loadData()
    }

    private fun initAdapter() {
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val multiAdapter = object : BaseMultiAdapter<ItemInfo>(itemInfos) {
            override fun decorator(bean: MultiItem<ItemInfo>, holder: BindViewHolder<ViewDataBinding>, position: Int) {
                when (bean.itemType) {
                    TYPE1 -> {
                        (holder.binding as ItemType1Binding).run {
                            tvName.setOnClickListener { v -> childClickLisener?.childClick(bean, tvName, position) }
                            tvAge.setOnClickListener { v -> childClickLisener?.childClick(bean, tvAge, position) }
                        }
                    }
                }
            }
        }
        //添加布局
        multiAdapter.addItemType(TYPE0, R.layout.item_type0)
        multiAdapter.addItemType(TYPE1, R.layout.item_type1)
        //
        multiAdapter.setOnItemClickLisener { multiItem: MultiItem<ItemInfo>, view: View, i: Int ->
            ToastUtil.normal("点击的是item:" + multiItem + "  位置:" + i)
        }
        multiAdapter.setChildClickLisener { multiItem: MultiItem<ItemInfo>, view: View, i: Int ->
            if (view.id == R.id.tv_name) {
                ToastUtil.normal("点击的是tv_name  " + multiItem.data.name)
            } else if (view.id == R.id.tv_age) {
                ToastUtil.normal("点击的是tv_age  " + multiItem.data.age)
            }
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
