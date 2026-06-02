///**
// * Copyright (C) 2018-2022
//
// */
//package com.ordering.module.product.service.storeproduct.dto;
//
//import com.ordering.modules.user.service.dto.LegacyUserSmallDto;
//import lombok.Data;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
//* @author hupeng
//* @date 2020-05-12
//*/
//@Data
//public class LegacyStoreProductReplyDto implements Serializable {
//
//    // 评论ID
//    private Long id;
//
//    // 用户ID
//    private Long uid;
//
//    private LegacyUserSmallDto user;
//
//    // 订单ID
//    private Long oid;
//
//    // 唯一id
//    private String unique;
//
//    // 产品id
//    private Long productId;
//
//    private LegacyStoreProductSmallDto storeProduct;
//
//
//    // 某种商品类型(普通商品、秒杀商品）
//    private String replyType;
//
//    // 商品分数
//    private Integer productScore;
//
//    // 服务分数
//    private Integer serviceScore;
//
//    // 评论内容
//    private String comment;
//
//    // 评论图片
//    private String pics;
//
//    // 评论时间
//    private Date createTime;
//
//    // 管理员回复内容
//    private String merchantReplyContent;
//
//    // 管理员回复时间
//    private Date merchantReplyTime;
//
//    // 0未回复1已回复
//    private Integer isReply;
//}
