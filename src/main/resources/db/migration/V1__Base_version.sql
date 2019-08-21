USE `service_manager`;

CREATE TABLE `user_info`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `user_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                              `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                              `token` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                              `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                              `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                              `expire_time` datetime(0) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE INDEX `username_and_password`(`user_name`, `password`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

CREATE TABLE `order_info`  (
                               `id` int(11) NOT NULL,
                               `user_id` int(11) NULL DEFAULT NULL,
                               `service_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                               `service_level` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                               `slice_type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                               `order_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
                               `area_list` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
                               `user_list` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
                               `app_list` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
                               `fee` double(255, 0) NULL DEFAULT NULL,
                               `order_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                               `create_time` datetime(0) NULL DEFAULT NULL,
                               `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

ALTER TABLE `service_manager`.`order_info`
    ADD CONSTRAINT `user_info_and_order_info` FOREIGN KEY (`user_id`) REFERENCES `service_manager`.`order_info` (`id`);