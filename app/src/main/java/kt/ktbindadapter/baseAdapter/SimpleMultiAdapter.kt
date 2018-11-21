package kt.ktbindadapter.baseAdapter

import android.databinding.ViewDataBinding

/**
 * Created by 田桂森 on 2017/8/18.
 * 多type如果不需要设置item中子类子类点击事件用这个
 */
class SimpleMultiAdapter<E : Any>(datas: MutableList<MultiItem<E>>) : BaseMultiAdapter<E>(datas) {
    override fun decorator(bean: MultiItem<E>, holder: BindViewHolder<ViewDataBinding>, position: Int) {

    }
}