package io.dj.modules.api.controller;


import io.dj.common.utils.R;
import io.dj.modules.api.annotation.AuthIgnore;
import io.dj.modules.api.entity.TokenEntity;
import io.dj.modules.api.entity.UserEntity;
import io.dj.modules.api.annotation.LoginUser;
import io.dj.modules.goods.service.GoodsToDictService;
import io.dj.modules.sys.service.SysWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * API测试接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:47
 */
@RestController
@RequestMapping("/api")
public class ApiTestController {

    @Autowired private SysWeatherService sysWeatherService;

    @Autowired private GoodsToDictService goodsToDictService;

    /**
     * 获取用户信息
     */
    @GetMapping("userInfo")
    public R userInfo(@LoginUser UserEntity user){
        return R.ok().put("user", user);
    }

    /**
     * 忽略Token验证测试
     */
    @AuthIgnore
    @GetMapping("notToken")
    public R notToken(){
        sysWeatherService.getTodayWeather();
        return R.ok().put("msg", "无需token也能访问。。。");
    }

    /**
     * 接收JSON数据
     */
    @PostMapping("jsonData")
    public R jsonData(@LoginUser UserEntity user, @RequestBody TokenEntity token){
        return R.ok().put("user", user).put("token", token);
    }


    /**
     * 忽略Token验证测试
     */
    @AuthIgnore
    @GetMapping("testGoods")
    public R test(){
        return R.ok().put("msg", goodsToDictService.queryObject(1L));
    }
}
