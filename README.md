# WebScaffolding
base on spring cloud quick working

RabbitMQ
Swagger2
Redis lettuce
mybatis-plus



todo:
mybatis 默认字段赋值
token 生成器
拦截器、过滤器 用以全局token锁定用户
权限
日志系统
ftp
异步扩容线程 callable DefferredResult



#Docker

```
docker images 查看所有镜像
docker ps 查看正在运行容器
```

##RabbitMQ

```
docker pull rabbitmq:3.7.7-management
docker run -d --name rabbitmq3.7.7 -p 5672:5672 -p 15672:15672 -v `pwd`/data:/var/lib/rabbitmq --hostname myRabbit -e RABBITMQ_DEFAULT_VHOST=my_vhost -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin df80af9ca0c9
```
>-d 后台运行容器；
>
> --name 指定容器名；
> 
> -p 指定服务运行的端口（5672：应用访问端口；15672：控制台Web端口号）；
> 
> -v 映射目录或文件；
> 
> --hostname  主机名（RabbitMQ的一个重要注意事项是它根据所谓的 “节点名称” 存储数据，默认为主机名）；
> 
> -e 指定环境变量；（RABBITMQ_DEFAULT_VHOST：默认虚拟机名；RABBITMQ_DEFAULT_USER：默认的用户名；RABBITMQ_DEFAULT_PASS：默认用户名的密码）

http://Server-IP:15672

##Redis

```
docker pull redis
docker run -p 6379:6379 -d redis:latest redis-server
```
>-p 6379:6379 : 将容器的6379端口映射到主机的6379端口
>
> -v $PWD/data:/data : 将主机中当前目录下的data挂载到容器的/data

##MySql

```
docker pull mysql:5.7
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7

docker run -p 3306:3306 --name mysql \
-v /usr/local/docker/mysql/conf:/etc/mysql \
-v /usr/local/docker/mysql/logs:/var/log/mysql \
-v /usr/local/docker/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
-d mysql:5.7
```

>–name：容器名，此处命名为mysql
>
> -e：配置信息，此处配置mysql的root用户的登陆密码
>
> -p：端口映射，此处映射 主机3306端口 到 容器的3306端口
>
>-v：主机和容器的目录映射关系，":"前为主机目录，之后为容器目录


#相关资料
[docker安装RabbitMQ - 简书](https://www.jianshu.com/p/66551708ad15)

[Docker安装运行Redis - 静静别跑 - 博客园](https://www.cnblogs.com/zhzhlong/p/9465670.html)

[使用Docker搭建MySQL服务 - sablier - 博客园](https://www.cnblogs.com/sablier/p/11605606.html#2915924035)

[使用Docker搭建Maven私服_wangjianfengnb-CSDN博客_docker maven 私服](https://blog.csdn.net/u012943767/article/details/79475718)

[docker部署zipkin_Lou_Lan的博客-CSDN博客_docker zipkin](https://blog.csdn.net/Lou_Lan/article/details/101198216)

[Kubernetes零基础快速入门！初学者必看！ - 知乎](https://zhuanlan.zhihu.com/p/88236691)

[linux prometheus安装服务端配置文件教程-Linux实验室](https://idc.wanyunshuju.com/prom/1136.html)
