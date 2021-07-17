CREATE TABLE `oauth_client_details`  (
                                         `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                         `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         `access_token_validity` int(11) NULL DEFAULT NULL,
                                         `refresh_token_validity` int(11) NULL DEFAULT NULL,
                                         `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `oauth_client_details` VALUES ('client', NULL, '123456', 'all', 'password,refresh_token', '', NULL, NULL, NULL, NULL, NULL);


CREATE TABLE `sys_oauth_client` (
                                    `client_id` varchar(256) NOT NULL,
                                    `resource_ids` varchar(256) DEFAULT NULL,
                                    `client_secret` varchar(256) DEFAULT NULL,
                                    `scope` varchar(256) DEFAULT NULL,
                                    `authorized_grant_types` varchar(256) DEFAULT NULL,
                                    `web_server_redirect_uri` varchar(256) DEFAULT NULL,
                                    `authorities` varchar(256) DEFAULT NULL,
                                    `access_token_validity` int(11) DEFAULT NULL,
                                    `refresh_token_validity` int(11) DEFAULT NULL,
                                    `additional_information` varchar(4096) DEFAULT NULL,
                                    `autoapprove` varchar(256) DEFAULT NULL,
                                    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

INSERT INTO `sys_oauth_client` VALUES ('client', NULL, '123456', 'all', 'authorization_code,password,refresh_token,implicit', NULL, NULL, 3600, 7200, NULL, 'true');
INSERT INTO `sys_oauth_client` VALUES ('youlai-admin', '', '123456', 'all', 'password,client_credentials,refresh_token,authorization_code', '', '', 3600, 7200, NULL, 'true');
INSERT INTO `sys_oauth_client` VALUES ('youlai-weapp', '', '123456', 'all', 'authorization_code,password,refresh_token,implicit', NULL, NULL, 3600, 7200, NULL, 'true');
