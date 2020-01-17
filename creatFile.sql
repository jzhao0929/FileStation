CREATE TABLE `file_message` (
  `id` varchar(32) NOT NULL,
  `true_name` varchar(255) NOT NULL COMMENT '文件真实名称',
  `absolute_path` varchar(100) NOT NULL COMMENT '绝对路径',
  `type` varchar(20) DEFAULT NULL COMMENT '文件类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;