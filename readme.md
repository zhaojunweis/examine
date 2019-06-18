# 上机考试系统

### 系统使用技术和软硬件支持
* MySQL
* Spring Boot
* Shiro
* Thymleaf
* BootStrap
* JavaScript
* Jquery
* HTML/CSS

### 浏览器访问

* 访问URL： http://47.94.234.145:8080/examine-0.0.1-SNAPSHOT/
* 操作流程：数据库初始化仅仅包括admin,admin用户，操作时请先登陆admin用户，创建教师，然后再创建考试导入学生名单等操作后，可进行学生登陆

### 仓库结构
![image](https://github.com/wencaixu/examine/blob/master/src/main/resources/static/img/%E4%BB%93%E5%BA%93%E7%BB%93%E6%9E%84.jpg)

### 仓库代码说明
仓库代码是我们开发过程中本地测试的一个版本，[发布的版本](https://github.com/wencaixu/examine/blob/master/%E6%96%87%E6%A1%A3%E6%96%87%E4%BB%B6/%E5%8F%91%E5%B8%83%E9%A1%B9%E7%9B%AEwar%E5%8C%85/examine-0.0.1-SNAPSHOT.war),这是一个war包，
项目详情请访问 http://47.94.234.145:8080/examine-0.0.1-SNAPSHOT/。

### 系统结构
| 分层        |   描述  |  作用 |
| --------   | -----:   | :----:|
| domain     |  实体类        |   数据库表的实体                     |
| dao        | 数据库访问接口  |   提供一个访问数据库实例的接口          |
| service    | 服务接口       |   提供一个实现控制器提供服务的实例接口    |
| impl       | 服务的实例     |   调用dao的实例方法，为控制器提供服务    |
| controller | 前端控制器     |   请求与响应处理中心                   |
| mapper     | dao接口的实例  |   实现对数据的处理                     |
