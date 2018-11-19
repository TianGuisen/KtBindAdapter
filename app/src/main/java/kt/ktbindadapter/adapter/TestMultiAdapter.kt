package kt.ktbindadapter.adapter

import android.databinding.ViewDataBinding
import kt.ktbindadapter.databinding.ItemType0Binding
import kt.ktbindadapter.databinding.ItemType1Binding

/**
 * Created by 田桂森 on 2017/8/17.
 * 多type，child点击事件用法,如果不需要child点击，用SimpleMultiAdapter即可。
 */

class TestMultiAdapter(itemTypeLayoutMap: Map<Int, Int>) : MultiBaseAdapter(itemTypeLayoutMap) {

    override fun decorator(bean: Any, holder: BindViewHolder<ViewDataBinding>, position: Int) {
        val integer = mResLayout[position]
        when (integer) {
            0 -> {
                val binding0 = holder.binding as ItemType0Binding
                binding0.tvName.setOnClickListener{ v -> childClickLisener?.childClick(bean, binding0.tvName, position) }
                binding0.tvAge.setOnClickListener{ v -> childClickLisener?.childClick(bean, binding0.tvAge, position) }
            }
            1 -> {
                val binding1 = holder.binding as ItemType1Binding
                binding1.tvName.setOnClickListener{ v -> childClickLisener?.childClick(bean, binding1.tvName, position) }
                binding1.tvAge.setOnClickListener{ v -> childClickLisener?.childClick(bean, binding1.tvAge, position) }
            }
        }
    }
} 