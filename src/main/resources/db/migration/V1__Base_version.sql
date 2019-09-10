USE `service_manager`;

CREATE TABLE `user_info`  (
                              `id` bigint(255) NOT NULL AUTO_INCREMENT,
                              `user_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                              `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                              `token` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                              `create_time` datetime(0) NULL DEFAULT current_timestamp,
                              `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                              `expire_time` bigint(14) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE INDEX `username_and_password_index`(`user_name`, `password`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

CREATE TABLE `order_info`  (
                               `id` bigint(255) NOT NULL AUTO_INCREMENT,
                               `user_id` bigint(11) NULL DEFAULT NULL,
                               `service_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                               `service_level` tinyint(10) NULL DEFAULT NULL,
                               `slice_type` tinyint(255) NULL DEFAULT NULL,
                               `order_time` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                               `area_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                               `user_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                               `app_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                               `fee` double(255, 0) NULL DEFAULT NULL,
                               `order_status` tinyint(255) NULL DEFAULT NULL,
                               `create_time` datetime(0) NULL DEFAULT current_timestamp,
                               `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `user_id_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

