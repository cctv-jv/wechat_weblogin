package com.wechat.core.module.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.core.common.response.ApiResponse;
import com.wechat.core.common.utils.AesUtil;
import com.wechat.core.common.utils.DateUtils;
import com.wechat.core.common.utils.HttpClientUtils;
import com.wechat.core.module.po.AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.wechat.core.common.constants.Constant.*;
import static com.wechat.core.common.enums.ResponseCode.*;

/**
 * @author ruqiang
 * @version 1.0.0
 * @className: WxLoginController
 * @Description 微信登录管理
 * @create 2019/11/21
 */
@RestController
@Slf4j
@RequestMapping("/wx")
public class WxLoginController {
    @Value("${appId}")
    private String appid;
    @Value("${secret}")
    private String secret;
    @Value("${redirect_uri}")
    private String redirect_uri;
    @Value("${redirect_bindWeChat}")
    private String redirect_bindWeChat;
    @Value("${redirect_getToken}")
    private String redirect_getToken;

    /**
     * 回调地址处理
     * @return
     */
    @RequestMapping("/getLoginUrl")
    public ApiResponse getLoginUrl() {
        //加密state进行验证 回调地址当天有效 防止恶意攻击
        byte[] encrypt = AesUtil.encrypt(PWD_MD5 + DateUtils.getYYYYMMdd(), AesUtil.PASSWORD_SECRET_KEY, 16);
        String url = null;
        try {
            url = String.format(AUTHORIZATION_URL, appid, URLEncoder.encode(redirect_uri, "GBK"), SCOPE, AesUtil.parseByte2HexStr(encrypt));
        } catch (UnsupportedEncodingException e) {
            log.debug("编码格式--->{},错误", e.getMessage());
        }
        return ApiResponse.OK(url);
    }

    /**
     * 登录回调地址处理
     * @param request
     * @param response
     * @param code
     * @param state
     * @return
     * @throws IOException
     */
    @RequestMapping("/login")
    public Object weChatLogin(HttpServletRequest request, HttpServletResponse response, String code, String state) throws IOException {
        if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(state)) {
            // 验证state防止跨站请求伪造攻击
            String decrypt = AesUtil.decrypt(AesUtil.parseHexStr2Byte(state), AesUtil.PASSWORD_SECRET_KEY, 16);
            if (!decrypt.equals(PWD_MD5 + DateUtils.getYYYYMMdd())) {
                return ApiResponse.ERROR(SYSTEM_ERROR.getCode(), STATE_ERROR.getMsg(), state);
            }
            String responseContent = HttpClientUtils.getInstance().sendHttpGet(String.format(ACCESS_TOKEN_URL, appid, secret, code));
            if (StringUtils.isEmpty(responseContent)) {
                return ApiResponse.ERROR(FAILED.getCode(), FAILED.getMsg(), "获取ACCESS_TOKEN失败");
            }
            JSONObject parseObject = JSONObject.parseObject(responseContent);
            AccessToken accessToken = JSONObject.toJavaObject(parseObject, AccessToken.class);
            if (accessToken != null) {
                // 根据OPENID查询用户是否存在
                // SysUserEntity user = weChatLoginService.getUserByOpenId(accessToken.getOpenid());
                // 增加日志
//                if (user == null) {
//                    // 重定向到绑定账号页面
//                    response.sendRedirect(redirect_bindWeChat + accessToken.getOpenid());
//                } else {
//                     //存在则把当前账号信息授权给扫码用户
//
//                    // 拿到openid获取微信用户的基本信息
//                    String res = HttpClientUtils.getInstance().sendHttpGet(String.format(GET_UNIONID_URL, accessToken.getAccess_token(), accessToken.getOpenid()));
//                    if (StringUtils.isEmpty(res)) {
//                        return null;
//                    }
//                    JSONObject object = JSONObject.parseObject(res);
//                }
            }
        }
        return null;
    }

    /**
     * 微信绑定账号
     * @param
     * @return
     */
    @PostMapping("/bindWeChat")
    public ApiResponse bindWeChat() {
        //把openId绑定给用户
        return ApiResponse.OK(Boolean.TRUE);
    }
}