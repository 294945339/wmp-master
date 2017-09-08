package io.dj.modules.sys.service.impl

import com.alibaba.fastjson.JSON
import com.xiaoleilu.hutool.date.DateUtil
import com.xiaoleilu.hutool.http.HttpUtil
import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.sys.dao.SysWeatherDao
import io.dj.modules.sys.entity.SysWeatherDomain
import io.dj.modules.sys.service.SysWeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.xiaoleilu.hutool.setting.dialect.Props
import com.xiaoleilu.hutool.util.StrUtil
import java.util.*


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/14
 * Time: 15:04
 * Email: 294945339@qq.com
 */

@Service("sysWeatherService")
open class SysWeatherServiceImpl : BaseServiceImpl<SysWeatherDao, SysWeatherDomain>(), SysWeatherService {

    @Autowired private val sysWeatherDao: SysWeatherDao? = null

    override fun getTodayWeather(): Boolean {
        var isRain = false
        var domains = findIsRain()
        if (domains!!.size > 0) {
            isRain = domains[0].rain
        } else {
            saveWeather()
            domains = findIsRain()
            isRain = domains!![0].rain
        }
        return isRain
    }

    fun findIsRain(): MutableList<SysWeatherDomain>? {
        //查询今天的天气是否有数据;没有的话查询api插入到数据库
        val domains = sysWeatherDao!!.queryList(object : HashMap<String, Any>() {
            init {
                put("today", DateUtil.today())
            }
        })

        return domains
    }

    fun saveWeather() {
        val props = Props("apiUrl.properties")
        val url = props["weatherApi"].toString()
        val paramMap = HashMap<String, Any>()
        paramMap.put("city", "温州")
        val data = JSON.parseObject(HttpUtil.get(url, paramMap)).getJSONObject("data")
        val arr = data.getJSONArray("forecast")
        for (i in arr.indices) {
            val weather = arr.getJSONObject(i).getString("type")
            var todayStr = arr.getJSONObject(i).getString("date")
            todayStr = StrUtil.sub(todayStr, 0, 2)
            val dateStr = DateUtil.format(Date(), "yyyy-MM") + "-" + todayStr
            var isRain = false
            if (StrUtil.containsIgnoreCase(weather, "雨")) {
                isRain = true
            }
            var sysWeatherDomain = object : SysWeatherDomain() {
                init {
                    today = DateUtil.parse(dateStr, DateUtil.NORM_DATE_PATTERN)
                    rain = isRain
                }
            }
            sysWeatherDao!!.save(sysWeatherDomain)
        }
    }
}