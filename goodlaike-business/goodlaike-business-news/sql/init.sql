CREATE TABLE `news_cn` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(80) NOT NULL COMMENT '新闻标题',
  `subtitle` varchar(100) DEFAULT NULL COMMENT '副标题',
  `content` text NOT NULL COMMENT '新闻内容',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '新闻状态 0：正常    9：删除',
  `type` varchar(20) NOT NULL DEFAULT '新闻',
  `link` varchar(100) DEFAULT NULL COMMENT '链接地址',
  `createUserId` int(11) NOT NULL COMMENT '创建人账号',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateUserId` int(11) NOT NULL COMMENT '更新人账号',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='新闻表 - 本地化表';


CREATE TABLE `news_en` (
  `id` int(11) NOT NULL COMMENT '主键id',
  `title` varchar(200) NOT NULL COMMENT '标题 ',
  `subtitle` varchar(200) DEFAULT NULL COMMENT '副标题',
  `content` text NOT NULL COMMENT '新闻内容',
  `link` varchar(100) DEFAULT NULL COMMENT '链接地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻表 - 英文	';


CREATE TABLE `news_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `newsId` int(11) NOT NULL COMMENT '新闻id',
  `picPath` varchar(200) NOT NULL COMMENT '图片 url ，相对路径',
  `main` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否主图',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '图片状态 0：正常  9：删除',
  `createUserId` int(11) NOT NULL COMMENT '创建人工号',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateUserId` int(11) NOT NULL COMMENT '更新人id',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `IX_News_Pictures_newsId` (`newsId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='新闻图片';


CREATE ALGORITHM=MERGE DEFINER=`gd_demo_master`@`%` SQL SECURITY DEFINER VIEW `v_news_cn` AS select `news_cn`.`id` AS `id`,`news_cn`.`title` AS `title`,`news_cn`.`subtitle` AS `subtitle`,`news_cn`.`content` AS `content`,`news_cn`.`status` AS `status`,`news_cn`.`type` AS `type`,`news_cn`.`link` AS `link`,`np`.`picPath` AS `mainPicPath`,`news_cn`.`createUserId` AS `createUserId`,`news_cn`.`createTime` AS `createTime`,`news_cn`.`updateUserId` AS `updateUserId`,`news_cn`.`updateTime` AS `updateTime` from (`news_cn` left join `news_picture` `np` on(((`news_cn`.`id` = `np`.`newsId`) and (`np`.`main` = 1) and (`np`.`status` = 0)))) where (`news_cn`.`status` = 0);


CREATE ALGORITHM=UNDEFINED DEFINER=`gd_demo_master`@`%` SQL SECURITY DEFINER VIEW `v_news_en` AS select `news_cn`.`id` AS `id`,`news_en`.`title` AS `title`,`news_en`.`subtitle` AS `subtitle`,`news_en`.`content` AS `content`,`news_cn`.`status` AS `status`,`news_cn`.`type` AS `type`,`news_en`.`link` AS `link`,`np`.`picPath` AS `mainPicPath`,`news_cn`.`createUserId` AS `createUserId`,`news_cn`.`createTime` AS `createTime`,`news_cn`.`updateUserId` AS `updateUserId`,`news_cn`.`updateTime` AS `updateTime` from ((`news_cn` join `news_en` on((`news_cn`.`id` = `news_en`.`id`))) left join `news_picture` `np` on(((`news_cn`.`id` = `np`.`newsId`) and (`np`.`main` = 1) and (`np`.`status` = 0)))) where (`news_cn`.`status` = 0);


CREATE ALGORITHM=MERGE DEFINER=`gd_demo_master`@`%` SQL SECURITY DEFINER VIEW `v_news_picture` AS select `news_picture`.`id` AS `id`,`news_picture`.`newsId` AS `newsId`,`news_picture`.`picPath` AS `picPath`,`news_picture`.`status` AS `status`,`news_picture`.`sort` AS `sort`,`news_picture`.`createUserId` AS `createUserId`,`news_picture`.`createTime` AS `createTime`,`news_picture`.`updateUserId` AS `updateUserId`,`news_picture`.`updateTime` AS `updateTime` from `news_picture` where (`news_picture`.`status` = 0);
