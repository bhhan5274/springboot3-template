CREATE TABLE IF NOT EXISTS `bhhan`.`users`
(
    `user_id`     bigint  NOT NULL auto_increment COMMENT '사용자 ID',
    `name`        varchar(50) NOT NULL COMMENT '사용자 이름',
    `status`      varchar(10) NOT NULL COMMENT '상태',
    `phone`       varchar(50) NOT NULL COMMENT '연락처',
    `created_at`  timestamp   NOT NULL COMMENT '생성 일시',
    `modified_at` timestamp   NOT NULL COMMENT '수정 일시',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자';
