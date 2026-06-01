/**
 * Copyright (C) 2018-2022
 * Historical source retained from wings.

 */
package com.wings.framework.common.constant;

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
	String WINGS_WEIXIN_PAY_SERVICE = "wings_weixin_pay_service";

	/**
	 * 微信支付小程序service
	 */
	String WINGS_WEIXIN_MINI_PAY_SERVICE = "wings_weixin_mini_pay_service";

	/**
	 * 微信支付app service
	 */
	String WINGS_WEIXIN_APP_PAY_SERVICE = "wings_weixin_app_pay_service";

	/**
	 * 微信公众号service
	 */
	String WINGS_WEIXIN_MP_SERVICE = "wings_weixin_mp_service";
	/**
	 * 微信小程序service
	 */
	String WINGS_WEIXIN_MA_SERVICE = "wings_weixin_ma_service";

	/**
	 * 商城默认密码
	 */
	String WINGS_DEFAULT_PWD = "123456";

	/**
	 * 商城默认注册图片
	 */
	String WINGS_DEFAULT_AVATAR = "https://image.dayouqiantu.cn/5e79f6cfd33b6.png";

	/**
	 * 腾讯地图地址解析
	 */
	String QQ_MAP_URL = "https://apis.map.qq.com/ws/geocoder/v1/";

	/**
	 * redis首页键
	 */
	String WINGS_REDIS_INDEX_KEY = "wings:index_data";

	/**
	 * 配置列表缓存
	 */
	String WINGS_REDIS_CONFIG_DATAS = "wings:config_datas";

	/**
	 * 充值方案
	 */
	String WINGS_RECHARGE_PRICE_WAYS = "wings_recharge_price_ways";
	/**
	 * 首页banner
	 */
	String WINGS_HOME_BANNER = "wings_home_banner";
	/**
	 * 首页菜单
	 */
	String WINGS_HOME_MENUS = "wings_home_menus";
	/**
	 * 首页滚动新闻
	 */
	String WINGS_HOME_ROLL_NEWS = "wings_home_roll_news";
	/**
	 * 热门搜索
	 */
	String WINGS_HOT_SEARCH = "wings_hot_search";
	/**
	 * 个人中心菜单
	 */
	String WINGS_MY_MENUES = "wings_my_menus";
	/**
	 * 秒杀时间段
	 */
	String WINGS_SECKILL_TIME = "wings_seckill_time";
	/**
	 * 签到天数
	 */
	String WINGS_SIGN_DAY_NUM = "wings_sign_day_num";

	/**
	 * 打印机配置
	 */
	String WINGS_ORDER_PRINT_COUNT = "order_print_count";
	/**
	 * 飞蛾用户信息
	 */
	String WINGS_FEI_E_USER = "fei_e_user";
	/**
	 * 飞蛾用户密钥
	 */
	String WINGS_FEI_E_UKEY= "fei_e_ukey";

	/**
	 * 打印机配置
	 */
	String WINGS_ORDER_PRINT_COUNT_DETAIL = "order_print_count_detail";

	/**
	 * 短信验证码长度
	 */
	int WINGS_SMS_SIZE = 6;

	/**
	 * 短信缓存时间
	 */
	long WINGS_SMS_REDIS_TIME = 600L;

	//零标识
	String WINGS_ZERO =  "0";

	//业务标识标识
	String WINGS_ONE =  "1";

	//目前完成任务数量是3
	int TASK_FINISH_COUNT = 3;

	int WINGS_ONE_NUM = 1;

	String WINGS_ORDER_CACHE_KEY = "wings:order";

	String WINGS_ORDER_SALE_STATUS_KEY = "wings:order:sale:status";

	long WINGS_ORDER_CACHE_TIME = 3600L;

	String WECHAT_MENUS =  "wechat_menus";

	String WINGS_EXPRESS_SERVICE = "wings_express_service";

	String WINGS_REDIS_SYS_CITY_KEY = "wings:city_list";

	String WINGS_REDIS_CITY_KEY = "wings:city";

	String WINGS_APP_LOGIN_USER = "app-online-token:";

	String WINGS_WECHAT_PUSH_REMARK = "香农之翼为您服务！";

	String DEFAULT_UNI_H5_URL = "https://example.com";

	String WINGS_MINI_SESSION_KET = "wings:session_key:";

	/**公众号二维码*/
	String WECHAT_FOLLOW_IMG="wechat_follow_img";
	/**后台api地址*/
	String ADMIN_API_URL="admin_api_url";

	//快递查询接口Logistic
	String KDNIAO_LOGISTIC_QUERY="https://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";

	// TODO 香农之翼 MVP：旧扫码入口路径，当前小程序端未找到对应页面，暂不启用
	String PAGE_GOOD_HOME = "pages/components/pages/scan/scan";
}
