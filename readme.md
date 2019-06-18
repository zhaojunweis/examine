# 上机考试系统

### 服务器访问URL

http://47.94.234.145:8080/examine-0.0.1-SNAPSHOT/

### 仓库结构
![image](https://github.com/wencaixu/examine/blob/master/src/main/resources/static/img/%E4%BB%93%E5%BA%93%E7%BB%93%E6%9E%84.jpg)


### 仓库代码说明


### 系统结构
| 分层        |   描述  |  作用 |
| --------   | -----:   | :----:|
| domain     |  实体类        |   数据库表的实体                     |
| dao        | 数据库访问接口  |   提供一个访问数据库实例的接口          |
| service    | 服务接口       |   提供一个实现控制器提供服务的实例接口    |
| impl       | 服务的实例     |   调用dao的实例方法，为控制器提供服务    |
| controller | 前端控制器     |   请求与响应处理中心                   |
| mapper     | dao接口的实例  |   实现对数据的处理                     |
