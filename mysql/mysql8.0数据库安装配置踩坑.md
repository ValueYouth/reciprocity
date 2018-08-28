### mysql8.0安装篇

* mysql官网：https://www.mysql.com/
* 方法一：DOWNLOADS --> Community --> MySQL Community Downloads --> MySQL Community Server (GPL) --> Other Downloads: Windows (x86, 64-bit), ZIP Archive
* 方法二：https://dev.mysql.com/downloads/mysql/ 最下方Other Downloads: Windows (x86, 64-bit), ZIP Archive

### mysql8.0配置篇
* 配置mysql环境变量：我的电脑->属性->高级->环境变量->系统变量，编辑Path变量：mysql bin文件夹的路径
* 在mysql安装目录根目录下，新建文件夹data
* 在mysql安装目录根目录下，添加配置文件settings.ini
* 运行初始化命令：`mysqld --initialize` （管理员身份）
* 运行安装命令：`mysqld -install`（管理员身份）

### 常用命令
* 启动服务：`net start mysql`
* 关闭服务：`net stop mysql`
* 登录数据库：mysql -u username -p password

### 首次修改密码
* 控制台命令：`mysqladmin -u root -p password new_password`，new_password指的是新密码
