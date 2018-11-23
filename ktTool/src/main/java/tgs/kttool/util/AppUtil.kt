package tgs.kttool.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings
import android.support.v4.content.ContextCompat
import tgs.kttool.ToolApplication.toolAppContext

object AppUtil {

    /**
     * 网络是否链接
     */
    fun isConnected(): Boolean {
        (toolAppContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo.also {
            if (null != it && it.isConnected) {
                if (it.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 获得values里的colors
     */
    fun getColor(colorId: Int) = ContextCompat.getColor(toolAppContext, colorId)

    /**
     * 设备id
     */
    val deviceId: String
        get() = Settings.Secure.getString(toolAppContext.contentResolver, Settings.Secure.ANDROID_ID)

}
