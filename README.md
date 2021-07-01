# 项目框架

![项目结构图](https://gitee.com/MakerHu/typora_images/raw/master/img/20210702005432.png)

# 项目目录结构

```
.
├─src
├─main
├─java
│ └─com
│ └─bjtu
│ └─ledger_management_system
│ ├─common ------------------------------// 配置文件包
│ ├─controller -----------------------------// 控制层包
│ │ └─dto ---------------------------------// 数据传输对象包
│ ├─dao ------------------------------------// 数据持久层包
│ ├─entity ----------------------------------// 实体类包
│ ├─service --------------------------------// 业务逻辑接口包
│ └─serviceImpl --------------------------// 业务逻辑实现包
└─resources
```

![项目目录结构](https://gitee.com/MakerHu/typora_images/raw/master/img/20210702011014.png)

# 功能说明

## 登录注册

1. 注册用户

   - 单用户注册

     - 接口

       | 接口格式   | http://localhost:8081/register/single |      |
       | ---------- | ------------------------------------ | ---- |
       | 请求方法   | POST                                  |      |
       | 后端返回值 | 成功 | Result对象，其中data的值为空 |
       |          | 失败 | Result对象，其中data的值为空，msg: 邮箱已被使用！ |
       | 请求参数   | user对象newUser：<br/>{<br/>    "uname":"test",<br/>    "gender":true,<br/>    "email":"test2@qq.com",<br/>    "password":"123",<br/>    "birthday":"2000-05-03"<br/>} |  |

   - 批量注册

     - 接口
     
       | 接口格式   | http://localhost:8081/register/batch                         |                                                              |
       | ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
       | 请求方法   | POST                                                         |                                                              |
       | 后端返回值 | 成功 | Result对象，其中data的值为空 |
       |            | 失败 | Result对象，其中data的值为创建失败的user对象列表<br/>，msg: 部分用户创建失败！ |
       | 请求参数   | user数组newUserList<br/>其中User为：<br/>{<br/>    "uname":"test",<br/>    "gender":true,<br/>    "email":"test2@qq.com",<br/>    "password":"123",<br/>    "birthday":"2000-05-03"<br/>} |                                                              |

2. 登录

   - 接口

     | 接口格式   | http://localhost:8081/login                         |                                       |
     | ---------- | ----------------------------------------------------- | ------------------------------------- |
     | 请求方法   | POST |                                       |
     | 后端返回值 | 成功 | Result对象，其中data的值为 UserMsgDTO |
     |     | 失败 | Result对象，其中data的值为空，msg: 用户名或密码错误！ |
   | 请求参数   | 1.     String: email <br/>2.     String: password  |   |
   
   

## 部门管理

1. 创建部门
   - 接口
   
     | 接口格式   | http://192.168.110:8081/department/create |                              |
     | ---------- | ----------------------------------------- | ---------------------------- |
     | 请求方法   | POST                                      |                              |
     | 后端返回值 | 成功                                      | Result对象，其中data的值为空 |
   |     | 失败       | Result对象，msg：部门已存在！             |
   | 请求参数   | 1.	父部门编号：superDid<br/>2.	新部门对象newDepartment:<br/>{<br/>    "dname":"test",<br/>    "dmanager":13,<br/>    "description":"描述",<br/>    "tel":"324324234"<br/>} |       |

2. 编辑部门
   - 接口
   
     | 接口格式   | http://192.168.110:8081/department/modify |                              |
     | ---------- | ----------------------------------------- | ---------------------------- |
     | 请求方法   | POST                                      |                              |
     | 后端返回值 | 成功      | Result对象，其中data的值为空 |
     |         | 失败       | Result对象，msg: 部门名称重复！|
     | 请求参数   | 1. 部门对象department:<br/>{<br/>    "did":"0.1.2"<br/>    "dname":"test",<br/>    "dmanager":3,<br/>    "description":"描述",<br/>    "tel":"324324234"<br/>} |              |
   
3. 切换部门
   - 接口
   
     | 接口格式   | http://localhost:8081/department/switch |                                      |
     | ---------- | --------------------------------------- | ------------------------------------ |
     | 请求方法   | POST   |                                      |
     | 后端返回值 | 成功 | Result对象，其中data的值为UserMsgDTO |
     |           | 失败   | Result对象，msg: 部门不存在！    |
     | 请求参数   | 部门编号String：did                     |        |

## 角色管理

1. 在某部门下创建角色
   - 接口
   
     | 接口格式   | http://localhost8081/role/create                             |                              |
     | ---------- | ------------------------------------------------------------ | ---------------------------- |
     | 请求方法   | POST  |                              |
     | 后端返回值 | 成功        | Result对象，其中data的值为空 |
     |           | 失败       | Result对象，msg: 此部门已存在！ |
     | 请求参数   | 1.	角色对象role：<br/>{<br/>    "rolename":"xxx",<br/>    "did":"xxx",<br/>    "description":"xxx"<br/>}<br/>2.	权限数组rightList |        |
2. 编辑角色
   - 接口
   
     | 接口格式   | http://localhost:8081/role/modify |                              |
     | ---------- | --------------------------------- | ---------------------------- |
     | 请求方法   | POST  |                              |
     | 后端返回值 | 成功       | Result对象，其中data的值为空 |
     |          | 失败       | Result对象，msg: 此部门不存在！ |
     | 请求参数   | 1.	带roleid的role对象：<br/>{<br/>    "roleid":123,<br/>    "rolename":"xxx",<br/>    "did":"xxx",<br/>    "description":"xxx"<br/>}<br/>2.	权限数组 |         |

## 权限管理

1. 用户角色分配与修改
   - 接口
   
     | 接口格式   | http://localhost:8081/role/distribute                        |                              |
     | ---------- | ------------------------------------------------------------ | ---------------------------- |
     | 请求方法   | POST |                              |
     | 后端返回值 | 成功 | Result对象，其中data的值为空 |
     |          | 失败       | Result对象           |
     | 请求参数   | 1.	用户编号long: uid<br/>2.	角色编号数组，其中角色编号long: roleid |                              |
   
   

## 台账模板管理

1. 创建台账模板
   - 接口
   
     | 接口格式   | http://localhost:8081/template/create |                              |
     | ---------- | ------------------------------------- | ---------------------------- |
     | 请求方法   | POST |                              |
     | 后端返回值 | 成功  | Result对象，其中data的值为空 |
     |          | 失败   | Result对象                |
     | 请求参数   | 1.模板对象newTemplate<br/>2.表头数据对象tableHead<br/>{<br/>    "newTemplate":{<br/>        "did":"0.2",<br/>        "tempname":"XXX台账",<br/>        "description":"描述描述"<br/>    },<br/>    "tableHead":{<br/>        "label":"XXX表名",<br/>        "children":[<br/>            { "label": "ID", "prop": "id" },<br/>            { "label": "姓名", "prop": "name" },<br/>            { "label": "爱好", "prop": "hobby" },<br/>            {<br/>                "label": "基本情况",<br/>                "children": [<br/>                    { "label": "性别", "prop": "sex" },<br/>                    { "label": "年龄", "prop": "age" },<br/>                    {<br/>                        "label": "是否成年",<br/>                        "children": [<br/>                            { "label": "是", "prop": "adult" },<br/>                            { "label": "否", "prop": "minor" }<br/>                        ]<br/>                    }<br/>                ]<br/>            }<br/>        ]<br/>    }<br/>} |               |
   
   

## 台账管理

1. 创建台账

   - 接口

     | 接口格式   | http://localhost:8081/ledger/createledger                    |                            |
     | ---------- | ------------------------------------------------------------ | -------------------------- |
     | 请求方法   | POST  |                            |
     | 后端返回值 | 成功 | Result对象，其中data的值空 |
     |           | 失败 | Result对象 |
     | 请求参数   | 1.台账对象<br/>newLedger:<br/>{<br/>    "ledgername": "测试台账3",<br/>    "did": "0.1",<br/>    "tempid": "12",<br/>    "description": "描述描述"<br/>} |            |

2. 删除台账

   - 接口

     | 接口格式   | http://localhost:8081/ledger/delledger |                              |
     | ---------- | -------------------------------------- | ---------------------------- |
     | 请求方法   | POST |
     | 后端返回值 | 成功 | Result对象，其中data的值为空 |
     |          | 失败       | Result对象                |
     | 请求参数   | 1.台账编号：ledgerid                   |        |

3. 添加台账记录

   - 接口

     | 接口格式   | http://localhost:8081/ledger/addrecord |                            |
     | ---------- | -------------------------------------- | -------------------------- |
     | 请求方法   | GET |                            |
     | 后端返回值 | 成功 | Result对象，其中data的值空 |
     |          | 失败 | Result对象                |
     | 请求参数   | 1.请求页数：ledgerid<br/>2.行号：rowid（-1表示在最后插入，其他数字表示插入成为第rowid行）<br/>3.一行记录的每个单元格组成的list<br/>recordList:<br/>[<br/>    {"strucid": "2", "value": "sd"},<br/>    {"strucid": "3", "value": "三等"},<br/>    {"strucid": "4", "value": "蚂蚁"},<br/>    {"strucid": "6", "value": "男"},<br/>    {"strucid": "7", "value": "21"},<br/>    {"strucid": "9", "value": "大三"},<br/>    {"strucid": "10", "value": "撒旦法"}<br/>] |       |

4. 删除台账记录

   - 接口

     | 接口格式   | http://localhost:8081/ledger/delrecord |                                          |
     | ---------- | -------------------------------------- | ---------------------------------------- |
     | 请求方法   | POST ||
     | 后端返回值 | 成功 | Result对象，其中data的值为Page\<template> |
     |                        | 失败     | Result对象                             |
     | 请求参数   | 1.台账编号：ledgerid<br/>2.行号：rowid |                                          |

5. 修改台账记录

   - 接口

     | 接口格式   | http://localhost:8081/ledger/updaterecord |                            |
     | ---------- | -------------------------------------- | -------------------------- |
     | 请求方法   | GET |                            |
     | 后端返回值 | 成功 | Result对象，其中data的值空 |
     |          | 失败 | Result对象                |
     | 请求参数   | 1.请求页数：ledgerid<br/>2.行号：rowid（-1表示在最后插入，其他数字表示插入成为第rowid行）<br/>3.一行记录的每个单元格组成的list<br/>recordList:<br/>[<br/>    {"strucid": "2", "value": "sd"},<br/>    {"strucid": "3", "value": "三等"},<br/>    {"strucid": "4", "value": "蚂蚁"},<br/>    {"strucid": "6", "value": "男"},<br/>    {"strucid": "7", "value": "21"},<br/>    {"strucid": "9", "value": "大三"},<br/>    {"strucid": "10", "value": "撒旦法"}<br/>] |       |



## 日志管理

1. 查看日志

   - 接口

     | 接口格式   | http://192.168.110:8081/log/allLog |                                     |
     | ---------- | ---------------------------------- | ----------------------------------- |
     | 请求方法   | GET                                |                                     |
     | 后端返回值 | 成功   | Result对象，其中data的值为List<Log> |
     |                   | 失败     | Result对象，msg: 日志获取失败！    |
     | 请求参数   | 1.	用户编号：String：uid<br/>2.	部门编号：String：did<br/>3.	pageNum(default=1)<br/>4.	pageSize(default=10) |                                     |

# 运行项目

## 开发环境

- MySQL 5.6.38
- Java8
- Maven 3.6.3
- Springboot 2.3.9

## 克隆项目

1. 打开IDEA

   ![克隆项目](https://gitee.com/MakerHu/typora_images/raw/master/img/20210702031129.png)

   输入URL：`https://github.com/MakerHu/ledger_management_system.git`

   ![克隆项目](https://gitee.com/MakerHu/typora_images/raw/master/img/20210702031327.png)

2. 等待项目克隆完成且依赖自动安装完成后即可运行（前提是数据库配置正确，见下一步）

## 配置数据库

MySQL

- 创建数据库：lms

- 数据库用户名：root

- 数据库密码：你的数据库root用户的密码

- 项目配置文件：application.yml

  ```yaml
  spring:
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: <你的数据库用户(如root)>
      password: <你的数据库密码>
      url: jdbc:mysql://182.92.115.193:3306/lms?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2b8
  ```

  

- 数据库初始化sql脚本

  ```sql
  SET NAMES utf8mb4;
  SET FOREIGN_KEY_CHECKS = 0;
  
  -- ----------------------------
  -- Table structure for department
  -- ----------------------------
  DROP TABLE IF EXISTS `department`;
  CREATE TABLE `department`  (
    `did` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `dname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `dmanager` bigint(10) NULL DEFAULT NULL,
    `createtime` datetime NOT NULL,
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `tel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`did`) USING BTREE
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Records of department
  -- ----------------------------
  INSERT INTO `department` VALUES ('0', 'rootDepartment', 1, '2021-04-22 00:00:00', '根部门', '234325');
  INSERT INTO `department` VALUES ('0.1', '中国石油大学公安部', 1, '2021-06-19 23:42:24', '中国石油大学公安部', '12345678');
  
  -- ----------------------------
  -- Table structure for ledger
  -- ----------------------------
  DROP TABLE IF EXISTS `ledger`;
  CREATE TABLE `ledger`  (
    `ledgerid` bigint(10) NOT NULL AUTO_INCREMENT,
    `ledgername` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `did` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `creatorid` bigint(10) NOT NULL,
    `tempid` bigint(10) NOT NULL,
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `createtime` datetime NOT NULL,
    PRIMARY KEY (`ledgerid`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Table structure for log
  -- ----------------------------
  DROP TABLE IF EXISTS `log`;
  CREATE TABLE `log`  (
    `logid` bigint(20) NOT NULL AUTO_INCREMENT,
    `operatorid` bigint(20) NOT NULL,
    `operatorname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `time` datetime NOT NULL,
    `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`logid`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Table structure for record
  -- ----------------------------
  DROP TABLE IF EXISTS `record`;
  CREATE TABLE `record`  (
    `id` bigint(10) NOT NULL AUTO_INCREMENT,
    `ledgerid` bigint(10) NOT NULL,
    `strucid` bigint(10) NOT NULL,
    `createtime` datetime NOT NULL,
    `rowid` bigint(10) NOT NULL,
    `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Table structure for rights
  -- ----------------------------
  DROP TABLE IF EXISTS `rights`;
  CREATE TABLE `rights`  (
    `rightid` bigint(10) NOT NULL AUTO_INCREMENT,
    `rightname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`rightid`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Records of rights
  -- ----------------------------
  INSERT INTO `rights` VALUES (1, 'allRights', '所有权限');
  INSERT INTO `rights` VALUES (2, 'ledger:view', '查看台账');
  INSERT INTO `rights` VALUES (3, 'ledger:edit', '编辑台账');
  INSERT INTO `rights` VALUES (4, 'template:view', '查看台账模板');
  INSERT INTO `rights` VALUES (5, 'template:edit', '编辑台账模板');
  INSERT INTO `rights` VALUES (6, 'role:view', '查看角色');
  INSERT INTO `rights` VALUES (7, 'role:edit', '编辑角色');
  INSERT INTO `rights` VALUES (8, 'user:view', '查看用户');
  INSERT INTO `rights` VALUES (9, 'user:edit', '编辑用户');
  INSERT INTO `rights` VALUES (10, 'department:view', '查看部门');
  INSERT INTO `rights` VALUES (11, 'department:edit', '编辑部门');
  INSERT INTO `rights` VALUES (12, 'right:view', '查看权限');
  INSERT INTO `rights` VALUES (13, 'right:edit', '编辑权限');
  INSERT INTO `rights` VALUES (14, 'log', '查看日志');
  
  -- ----------------------------
  -- Table structure for role
  -- ----------------------------
  DROP TABLE IF EXISTS `role`;
  CREATE TABLE `role`  (
    `roleid` bigint(10) NOT NULL AUTO_INCREMENT,
    `rolename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `did` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`roleid`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Records of role
  -- ----------------------------
  INSERT INTO `role` VALUES (1, 'admin', '0', '超级管理员');
  INSERT INTO `role` VALUES (2, 'dmanager', '0.1', '中国石油大学公安部管理员');
  
  -- ----------------------------
  -- Table structure for roles_rights
  -- ----------------------------
  DROP TABLE IF EXISTS `roles_rights`;
  CREATE TABLE `roles_rights`  (
    `id` bigint(10) NOT NULL AUTO_INCREMENT,
    `roleid` bigint(10) NOT NULL,
    `rightid` bigint(10) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Records of roles_rights
  -- ----------------------------
  INSERT INTO `roles_rights` VALUES (1, 1, 1);
  INSERT INTO `roles_rights` VALUES (2, 2, 1);
  
  -- ----------------------------
  -- Table structure for template
  -- ----------------------------
  DROP TABLE IF EXISTS `template`;
  CREATE TABLE `template`  (
    `tempid` bigint(10) NOT NULL AUTO_INCREMENT,
    `creatorid` bigint(10) NOT NULL,
    `createtime` datetime NOT NULL,
    `did` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `tempname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`tempid`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Table structure for template_relation
  -- ----------------------------
  DROP TABLE IF EXISTS `template_relation`;
  CREATE TABLE `template_relation`  (
    `id` bigint(10) NOT NULL AUTO_INCREMENT,
    `tempid` bigint(10) NOT NULL,
    `superid` bigint(10) NOT NULL,
    `subid` bigint(10) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Table structure for template_structure_content
  -- ----------------------------
  DROP TABLE IF EXISTS `template_structure_content`;
  CREATE TABLE `template_structure_content`  (
    `id` bigint(10) NOT NULL AUTO_INCREMENT,
    `tempid` bigint(10) NOT NULL,
    `strucid` bigint(10) NOT NULL,
    `content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `superid` bigint(10) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Table structure for user
  -- ----------------------------
  DROP TABLE IF EXISTS `user`;
  CREATE TABLE `user`  (
    `uid` bigint(10) NOT NULL AUTO_INCREMENT,
    `uname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `gender` tinyint(1) NULL DEFAULT NULL,
    `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `birthday` date NULL DEFAULT NULL,
    `lastdid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`uid`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Records of user
  -- ----------------------------
  INSERT INTO `user` VALUES (1, 'admin', 1, 'admin@lms.com', '$2a$10$GSpoh6lTaiGhEon0PT6.y.QMOakYmA24s/jb9eEuwch/hcRcvjDRS', '2000-05-03', '0');
  
  -- ----------------------------
  -- Table structure for users_roles
  -- ----------------------------
  DROP TABLE IF EXISTS `users_roles`;
  CREATE TABLE `users_roles`  (
    `id` bigint(10) NOT NULL AUTO_INCREMENT,
    `uid` bigint(10) NOT NULL,
    `roleid` bigint(10) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
  
  -- ----------------------------
  -- Records of users_roles
  -- ----------------------------
  INSERT INTO `users_roles` VALUES (1, 1, 1);
  INSERT INTO `users_roles` VALUES (2, 1, 2);
  
  SET FOREIGN_KEY_CHECKS = 1;
  ```

  

# 项目部署

## 项目打包

1. 在Maven管理中分别点击clean-package

![IDEA项目打包](https://gitee.com/MakerHu/typora_images/raw/master/img/20210702031933.png)

2. 得到jar包

![](https://gitee.com/MakerHu/typora_images/raw/master/img/20210702032033.png)

3. 将jar包上传至服务器中

## 服务器环境配置

- 服务器操作系统：CentOS
- java：jdk1.8

## 运行jar包

cd到jar包所在文件夹下

`nohup java -jar ledger_management_system-0.0.1-SNAPSHOT.jar > system.log 2>&1 &`

