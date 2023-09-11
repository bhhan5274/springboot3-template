ALTER TABLE `bhhan`.`users` ADD `group_id` bigint DEFAULT NULL COMMENT '사용자 그룹 ID';
CREATE INDEX idx_group_id on `bhhan`.`users` (group_id);


CREATE TABLE IF NOT EXISTS `bhhan`.`user_groups`
(
    `user_group_id`     bigint       NOT NULL auto_increment COMMENT '사용자 그룹 ID',
    `name`              varchar(50)  NOT NULL COMMENT '사용자 그룹 이름',
    `description`       varchar(100) NOT NULL COMMENT '사용자 그룹 설명',
    PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자 그룹';
