package tgs.kttool

object ToolConfigs {
    //在Bugly上创建2个应用,一个用于测试一个用于线上,避免搞不清楚出的bug是测试测出的bug还是线上bug
    const val BUGLY_APPID_RELEASE = "9658217cfb"
    const val BUGLY_APPID_DEBUG = "abc"
    /**
     * Logger日志的tag
     */
    const val LOGGER_TAG = "tian"
    /**
     * Logger网络请求日志的tag
     */
    const val LOGGER_NET_TAG = "retrofit"


}