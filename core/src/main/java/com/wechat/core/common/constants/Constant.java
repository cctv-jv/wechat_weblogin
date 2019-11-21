package com.wechat.core.common.constants;

/**
 * @author ruqiang
 * @version 1.0.0
 * @className: Constants
 * @Description Constants
 * @create 2019/11/21
 */
public class Constant {

    /**
     * app唯一标识
     */
    public static final String APP_ID = "appid";

    /**
     * 用户唯一标识
     */
    public static final String OPEN_ID = "openid";

    /**
     * 密匙
     */
    public static final String SECRET = "secret";

    /**
     * token
     */
    public static final String ACCESS_TOKEN = "access_token";

    /**
     * 回调地址
     */
    public static final String REDIRECT_URI = "redirect_uri";
    /**
     * SCOPE
     */
    public static final String SCOPE = "snsapi_login";
    /**
     * 授权回调地址
     */
    public static final String AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    /**
     * 获取token
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 根据token和openId获取用户信息 UnionID机制
     */
    public static final String GET_UNIONID_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

    /**
     * 自定义加密措施
     */
    public static final String PWD_MD5 = "FeNG#@YOnG";

    /**
     * 7天后过期
     */
    public final static int EXPIRE = 7*24*3600*1000;
}