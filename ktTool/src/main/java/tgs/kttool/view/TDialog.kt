package tg.my.core.view

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kt.ktbindadapter.R
import tgs.kttool.util.setDoubleClickLis


/**
 * dialog
 */
class TDialog : DialogFragment() {

    private lateinit var tvTitle: TextView
    private lateinit var tvMsg: TextView
    private lateinit var tvOk: TextView
    private lateinit var tvCancel: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context!!, R.layout.dia_t, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvTitle = view.findViewById<TextView>(R.id.tv_title)
        tvMsg = view.findViewById<TextView>(R.id.tv_msg)
        tvOk = view.findViewById<TextView>(R.id.tv_ok)
        tvCancel = view.findViewById<TextView>(R.id.tv_cancel)
    }

    fun setOnOkClickLis(function: () -> Unit): TDialog {
        tvOk.setDoubleClickLis {
            function()
        }
        return this
    }

    fun setOnCancelClickLis(function: () -> Unit): TDialog {
        tvCancel.setDoubleClickLis {
            dismiss()
            function()
        }
        return this
    }

    fun title(title: String?): TDialog {
        if (title.isNullOrEmpty()) {
            tvTitle.visibility = View.GONE
        } else {
            tvTitle.text = title
        }
        return this
    }

    fun msg(msg: String): TDialog {
        tvMsg.text = msg
        return this
    }

    fun okText(okText: String): TDialog {
        tvOk.text = okText
        return this
    }

    fun cancelText(cancelText: String?): TDialog {
        if (cancelText.isNullOrEmpty()) {
            tvCancel.visibility = View.GONE
        } else {
            tvCancel.text = cancelText
        }
        return this
    }

    fun cancelable(b: Boolean): TDialog {
        setCancelable(b)
        return this
    }

    fun showNew(manager: FragmentManager?, tag: String?): TDialog {
        //必须调用这个showNow而不是普通的show,生命周期会同步执行
        super.showNow(manager, tag)
        return this
    }

    
}