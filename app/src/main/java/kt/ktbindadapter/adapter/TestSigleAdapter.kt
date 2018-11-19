package kt.ktbindadapter.adapter

import kt.ktbindadapter.bean.ItemInfo
import kt.ktbindadapter.databinding.ItemTestBinding


/**
 * Created by 田桂森 on 2017/7/18.
 * child点击事件用法,如果不需要child点击，用SimpleAdapter即可。
 */

class TestSigleAdapter(datas: MutableList<ItemInfo>, layoutId: Int) : SigleBaseAdapter<ItemInfo, ItemTestBinding>(datas, layoutId) {

    override fun decorator(bean: ItemInfo, holder: BindViewHolder<ItemTestBinding>, position: Int) {
        holder.binding.run {
            tvName.setOnClickListener({ v -> childClickLisener!!.childClick(bean, tvName, position) })
            tvAge.setOnClickListener({ v -> childClickLisener!!.childClick(bean, tvAge, position) })
        }
    }
}
