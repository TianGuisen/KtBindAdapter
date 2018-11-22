package tgs.kttool

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import kt.ktbindadapter.BuildConfig


class ToolApplication : Application() {
    companion object {
        lateinit var toolAppContext: ToolApplication
    }

    override fun onCreate() {
        super.onCreate()
        toolAppContext = this
        initLog()
    }


    private fun initLog() {
        val strategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // 是否显示线程信息，默认为ture
                .methodCount(1)         // 显示的方法行数
                .methodOffset(0)        // 隐藏内部方法调用到偏移量
                .tag(ToolConfigs.LOGGER_TAG)
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(strategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

}
