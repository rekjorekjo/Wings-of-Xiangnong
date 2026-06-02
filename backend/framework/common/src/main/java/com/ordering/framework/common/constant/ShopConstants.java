/**
 * Copyright (C) 2018-2022
 * Historical source retained from app.

 */
package com.ordering.framework.common.constant;

/**
 * 商城统一常量
 * @author hupeng
 * @since 2020-02-27
 */
public interface ShopConstants {

	/**
	 * 订单自动取消时间（分钟）
	 */
	long ORDER_OUTTIME_UNPAY = 30;
	/**
	 * 订单自动收货时间（分钟）
	 */
	long ORDER_OUTTIME_UNCONFIRM = 60;
	/**
	 * redis订单未付款key
	 */
	String REDIS_ORDER_OUTTIME_UNPAY_QUEUE = "order-unpay-cancel-queue";
	/**
	 * redis订单收货key
	 */
	String REDIS_ORDER_OUTTIME_UNCONFIRM = "order:unconfirm:";

	/**
	 * redis拼团key
	 */
	String REDIS_PINK_CANCEL_KEY = "pink:cancel:";

	/**
	 * 微信支付service
	 */
	String APP_WEIXIN_PAY_SERVICE = "app_weixin_pay_service";

	/**
	 * 微信支付小程序service
	 */
	String APP_WEIXIN_MINI_PAY_SERVICE = "app_weixin_mini_pay_service";

	/**
	 * 微信支付app service
	 */
	String APP_WEIXIN_APP_PAY_SERVICE = "app_weixin_app_pay_service";

	/**
	 * 微信公众号service
	 */
	String APP_WEIXIN_MP_SERVICE = "app_weixin_mp_service";
	/**
	 * 微信小程序service
	 */
	String APP_WEIXIN_MA_SERVICE = "app_weixin_ma_service";

	/**
	 * 商城默认密码
	 */
	String APP_DEFAULT_PWD = "123456";

	/**
	 * 商城默认注册图片
	 */
	String APP_DEFAULT_AVATAR = "https://image.dayouqiantu.cn/5e79f6cfd33b6.png";

	/**
	 * 腾讯地图地址解析
	 */
	String QQ_MAP_URL = "https://apis.map.qq.com/ws/geocoder/v1/";

	/**
	 * redis首页键
	 */
	String APP_REDIS_INDEX_KEY = "app:index_data";

	/**
	 * 配置列表缓存
	 */
	String APP_REDIS_CONFIG_DATAS = "app:config_datas";

	/**
	 * 充值方案
	 */
	String APP_RECHARGE_PRICE_WAYS = "app_recharge_price_ways";
	/**
	 * 首页banner
	 */
	String APP_HOME_BANNER = "app_home_banner";
	/**
	 * 首页菜单
	 */
	String APP_HOME_MENUS = "app_home_menus";
	/**
	 * 首页滚动新闻
	 */
	String APP_HOME_ROLL_NEWS = "app_home_roll_news";
	/**
	 * 热门搜索
	 */
	String APP_HOT_SEARCH = "app_hot_search";
	/**
	 * 个人中心菜单
	 */
	String APP_MY_MENUES = "app_my_menus";
	/**
	 * 秒杀时间段
	 */
	String APP_SECKILL_TIME = "app_seckill_time";
	/**
	 * 签到天数
	 */
	String APP_SIGN_DAY_NUM = "app_sign_day_num";

	/**
	 * 打印机配置
	 */
	String APP_ORDER_PRINT_COUNT = "order_print_count";
	/**
	 * 飞蛾用户信息
	 */
	String APP_FEI_E_USER = "fei_e_user";
	/**
	 * 飞蛾用户密钥
	 */
	String APP_FEI_E_UKEY= "fei_e_ukey";

	/**
	 * 打印机配置
	 */
	String APP_ORDER_PRINT_COUNT_DETAIL = "order_print_count_detail";

	/**
	 * 短信验证码长度
	 */
	int APP_SMS_SIZE = 6;

	/**
	 * 短信缓存时间
	 */
	long APP_SMS_REDIS_TIME = 600L;

	//零标识
	String APP_ZERO =  "0";

	//业务标识标识
	String APP_ONE =  "1";

	//目前完成任务数量是3
	int TASK_FINISH_COUNT = 3;

	int APP_ONE_NUM = 1;

	String APP_ORDER_CACHE_KEY = "app:order";

	String APP_ORDER_SALE_STATUS_KEY = "app:order:sale:status";

	long APP_ORDER_CACHE_TIME = 3600L;

	String WECHAT_MENUS =  "wechat_menus";

	String APP_EXPRESS_SERVICE = "app_express_service";

	String APP_REDIS_SYS_CITY_KEY = "app:city_list";

	String APP_REDIS_CITY_KEY = "app:city";

	String APP_APP_LOGIN_USER = "app-online-token:";

	String APP_WECHAT_PUSH_REMARK = "香农之翼为您服务！";

	String DEFAULT_UNI_H5_URL = "https://example.com";

	String APP_MINI_SESSION_KET = "app:session_key:";

	/**公众号二维码*/
	String WECHAT_FOLLOW_IMG="wechat_follow_img";
	/**后台api地址*/
	String ADMIN_API_URL="admin_api_url";

	//快递查询接口Logistic
	String KDNIAO_LOGISTIC_QUERY="https://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";

	// TODO 香农之翼 MVP：旧扫码入口路径，当前小程序端未找到对应页面，暂不启用
	String PAGE_GOOD_HOME = "pages/components/pages/scan/scan";
}
