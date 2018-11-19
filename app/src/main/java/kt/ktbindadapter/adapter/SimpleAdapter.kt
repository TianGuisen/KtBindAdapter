package kt.ktbindadapter.adapter

import android.databinding.ViewDataBinding

/**
 * Created by 田桂森 on 2017/8/17.
 * 如果不需要设置item中子类点击事件用这个
 */

class SimpleAdapter<E : Any>(datas: MutableList<E>, layoutId: Int) : SigleBaseAdapter<E, ViewDataBinding>(datas, layoutId)
