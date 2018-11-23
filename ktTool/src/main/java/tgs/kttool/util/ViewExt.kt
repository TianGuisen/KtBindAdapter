package tgs.kttool.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}


/**
 * 双击间隔时间
 */
const val INTERVAL = 400

inline fun View.setDoubleClickLis(crossinline function: (View) -> Unit): View {
    var temTime: Long = 0
    this.setOnClickListener {
        if (System.currentTimeMillis() - temTime > INTERVAL) {
            temTime = System.currentTimeMillis()
            function(it)
        }
    }
    return this
}

inline fun doubleClickLis(crossinline function: (View) -> Unit): View.OnClickListener {
    var temTime: Long = 0
    return object : View.OnClickListener {
        override fun onClick(v: View) {
            if (System.currentTimeMillis() - temTime > INTERVAL) {
                temTime = System.currentTimeMillis()
                function(v)
            }
        }
    }
}


inline fun refreshListener(crossinline refreshLayout: (RefreshLayout) -> Unit): OnRefreshListener {
    return object : OnRefreshListener {
        override fun onRefresh(refreshLayout: RefreshLayout) {
            refreshLayout(refreshLayout)
        }
    }
}

inline fun loadMoreListener(crossinline refreshLayout: (RefreshLayout) -> Unit): OnLoadMoreListener {
    return object : OnLoadMoreListener {
        override fun onLoadMore(refreshLayout: RefreshLayout) {
            refreshLayout(refreshLayout)
        }
    }
}





