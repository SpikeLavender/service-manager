
USE `service_manager`;

CREATE TABLE `active_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(0) NULL,
  `start_time` datetime(0) NULL,
  `end_time` datetime(0) NULL,
  `active_duration` bigint(255) NULL,
  `active_status` tinyint(10) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT current_timestamp,
  `update_time` datetime(0) NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);