package io.dj.modules.api.controller;


import io.dj.common.utils.R;
import io.dj.common.validator.Assert;
import io.dj.modules.api.annotation.AuthIgnore;
import io.dj.modules.api.service.UserService;
import io.dj.modules.api.service.TokenService;
import io.dj.modules.sys.service.SysWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * API登录授权
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api")
public class ApiLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired private SysWeatherService sysWeatherService;


    /**
     * 登录
     */
    @AuthIgnore
    @PostMapping("login")
    public R login(String mobile, String password){
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return R.ok(map);
    }

    @AuthIgnore
    @GetMapping("/getTodayWeather")
    public R getTodayWeather(){
        Boolean isRain = sysWeatherService.getTodayWeather();
        return R.ok().put("data",isRain);
    }

}
