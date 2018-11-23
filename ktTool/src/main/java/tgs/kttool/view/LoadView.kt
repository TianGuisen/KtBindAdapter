package tg.my.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kt.ktbindadapter.R
import java.util.*

/**
 * 一个方便在多种状态切换的view
 */
class LoadView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mEmptyView: View? = null
    private var mErrorView: View? = null
    private var mLoadingView: View? = null
    private var mNoNetworkView: View? = null
    private var mContentView: View? = null
    private val mEmptyViewResId: Int
    private val mErrorViewResId: Int
    private val mLoadingViewResId: Int
    private val mNoNetworkViewResId: Int
    private val mContentViewResId: Int

    /**
     * 获取当前状态
     */
    var viewStatus: Int = 0
        private set
    private val mInflater: LayoutInflater
    private var mOnRetryClickListener: View.OnClickListener? = null

    private val mOtherIds = ArrayList<Int>()
    private val onLoad: Unit? = null

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.loadView, defStyleAttr, 0)
        mEmptyViewResId = a.getResourceId(R.styleable.loadView_emptyView, R.layout.view_empty)
        mErrorViewResId = a.getResourceId(R.styleable.loadView_errorView, R.layout.view_error)
        mLoadingViewResId = a.getResourceId(R.styleable.loadView_loadingView, R.layout.view_loding)
        mNoNetworkViewResId = a.getResourceId(R.styleable.loadView_noNetworkView, R.layout.view_no_network)
        mContentViewResId = a.getResourceId(R.styleable.loadView_contentView, NULL_RESOURCE_ID)
        a.recycle()
        mInflater = LayoutInflater.from(getContext())
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear(mEmptyView, mLoadingView, mErrorView, mNoNetworkView)
        mOtherIds?.clear()
        if (null != mOnRetryClickListener) {
            mOnRetryClickListener = null
        }
    }

    /**
     * 设置重试点击事件
     *
     * @param onRetryClickListener 重试点击事件
     */
    fun setOnRetryClickListener(onRetryListener: OnClickListener) {
        mOnRetryClickListener= onRetryListener
    }

    /**
     * 显示空视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    @JvmOverloads
    fun showEmpty(layoutId: Int = mEmptyViewResId, layoutParams: ViewGroup.LayoutParams = DEFAULT_LAYOUT_PARAMS) {
        showEmpty(if (null == mEmptyView) inflateView(layoutId) else mEmptyView, layoutParams)
    }

    /**
     * 显示空视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showEmpty(view: View?, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Empty view is null.")
        checkNull(layoutParams, "Layout params is null.")
        viewStatus = STATUS_EMPTY
        if (null == mEmptyView) {
            mEmptyView = view
            val emptyRetryView = mEmptyView!!.findViewById<View>(R.id.cl_empty)
            if (null != mOnRetryClickListener && null != emptyRetryView) {
                emptyRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mEmptyView!!.id)
            addView(mEmptyView, 0, layoutParams)
        }
        showViewById(mEmptyView!!.id)
    }

    /**
     * 显示错误视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    @JvmOverloads
    fun showError(layoutId: Int = mErrorViewResId, layoutParams: ViewGroup.LayoutParams = DEFAULT_LAYOUT_PARAMS) {
        showError(if (null == mErrorView) inflateView(layoutId) else mErrorView, layoutParams)
    }

    /**
     * 显示错误视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showError(view: View?, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Error view is null.")
        checkNull(layoutParams, "Layout params is null.")
        viewStatus = STATUS_ERROR
        if (null == mErrorView) {
            mErrorView = view
            val errorRetryView = mErrorView!!.findViewById<View>(R.id.cl_error)
            if (null != mOnRetryClickListener && null != errorRetryView) {
                errorRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mErrorView!!.id)
            addView(mErrorView, 0, layoutParams)
        }
        showViewById(mErrorView!!.id)
    }

    /**
     * 显示加载中视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    @JvmOverloads
    fun showLoading(layoutId: Int = mLoadingViewResId, layoutParams: ViewGroup.LayoutParams = DEFAULT_LAYOUT_PARAMS) {
        showLoading(if (null == mLoadingView) inflateView(layoutId) else mLoadingView, layoutParams)
    }

    /**
     * 显示加载中视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showLoading(view: View?, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Loading view is null.")
        checkNull(layoutParams, "Layout params is null.")
        viewStatus = STATUS_LOADING
        if (null == mLoadingView) {
            mLoadingView = view
            mOtherIds.add(mLoadingView!!.id)
            addView(mLoadingView, 0, layoutParams)
        }
        showViewById(mLoadingView!!.id)
    }

    /**
     * 显示无网络视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    @JvmOverloads
    fun showNoNetwork(layoutId: Int = mNoNetworkViewResId, layoutParams: ViewGroup.LayoutParams = DEFAULT_LAYOUT_PARAMS) {
        showNoNetwork(if (null == mNoNetworkView) inflateView(layoutId) else mNoNetworkView, layoutParams)
    }

    /**
     * 显示无网络视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showNoNetwork(view: View?, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "No network view is null.")
        checkNull(layoutParams, "Layout params is null.")
        viewStatus = STATUS_NO_NETWORK
        if (null == mNoNetworkView) {
            mNoNetworkView = view
            val noNetworkRetryView = mNoNetworkView!!.findViewById<View>(R.id.cl_no_network)
            if (null != mOnRetryClickListener && null != noNetworkRetryView) {
                noNetworkRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mNoNetworkView!!.id)
            addView(mNoNetworkView, 0, layoutParams)
        }
        showViewById(mNoNetworkView!!.id)
    }

    /**
     * 显示内容视图
     */
    fun showContent() {
        viewStatus = STATUS_CONTENT
        if (null == mContentView && mContentViewResId != NULL_RESOURCE_ID) {
            mContentView = mInflater.inflate(mContentViewResId, null)
            addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS)
        }
        showContentView()
    }

    /**
     * 显示内容视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    fun showContent(layoutId: Int, layoutParams: ViewGroup.LayoutParams) {
        showContent(inflateView(layoutId), layoutParams)
    }

    /**
     * 显示内容视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showContent(view: View, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Content view is null.")
        checkNull(layoutParams, "Layout params is null.")
        viewStatus = STATUS_CONTENT
        clear(mContentView)
        mContentView = view
        addView(mContentView, 0, layoutParams)
        showViewById(mContentView!!.id)
    }

    private fun inflateView(layoutId: Int): View {
        return mInflater.inflate(layoutId, null)
    }

    private fun showViewById(viewId: Int) {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (view.id == viewId) View.VISIBLE else View.GONE
        }
    }

    private fun showContentView() {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (mOtherIds.contains(view.id)) View.GONE else View.VISIBLE
        }
    }

    private fun checkNull(`object`: Any?, hint: String) {
        if (null == `object`) {
            throw NullPointerException(hint)
        }
    }

    private fun clear(vararg views: View?) {
        if (null == views) {
            return
        }
        try {
            for (view in views) {
                if (null != view) {
                    removeView(view)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    companion object {
        private val DEFAULT_LAYOUT_PARAMS = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT)
        val STATUS_CONTENT = 0x00
        val STATUS_LOADING = 0x01
        val STATUS_EMPTY = 0x02
        val STATUS_ERROR = 0x03
        val STATUS_NO_NETWORK = 0x04

        private val NULL_RESOURCE_ID = -1
    }


}