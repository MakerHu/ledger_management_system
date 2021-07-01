# ledger_management_system
台账管理系统后端

# 项目框架

![项目结构图](https://gitee.com/MakerHu/typora_images/raw/master/img/20210702005432.png)

# 项目目录结构

> .
> ├─src
> ├─main
> ├─java
> │  └─com
> │      └─bjtu
> │          └─ledger_management_system
> │              ├─common ------------------------------// 配置文件包
> │              ├─controller -----------------------------// 控制层包
> │              │  └─dto ---------------------------------// 数据传输对象包
> │              ├─dao ------------------------------------// 数据持久层包
> │              ├─entity ----------------------------------// 实体类包
> │              ├─service --------------------------------// 业务逻辑接口包
> │              └─serviceImpl --------------------------// 业务逻辑实现包
> └─resources

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
       | 后端返回值 | 成功                                                         | Result对象，其中data的值为空                                 |
       |            | 失败                                                         | Result对象，其中data的值为创建失败的user对象列表<br/>，msg: 部分用户创建失败！ |
       | 请求参数   | user数组newUserList<br/>其中User为：<br/>{<br/>    "uname":"test",<br/>    "gender":true,<br/>    "email":"test2@qq.com",<br/>    "password":"123",<br/>    "birthday":"2000-05-03"<br/>} |                                                              |

2. 登录

   - 接口

     | 接口格式   | http://localhost:8081/login                         |                                       |
     | ---------- | ----------------------------------------------------- | ------------------------------------- |
     | 请求方法   | POST                                                  |                                       |
     | 后端返回值 | 成功                                                  | Result对象，其中data的值为 UserMsgDTO |
   |     | 失败       | Result对象，其中data的值为空，msg: 用户名或密码错误！ |
   | 请求参数   | 1.     String: email <br/>2.     String: password     |   |
   
   

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
     | 后端返回值 | 成功 | Result对象，其中data的值为Page<template> |
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

## 克隆项目

## 配置数据库

# 项目部署

## 项目打包

## 服务器环境配置

## 运行jar包

