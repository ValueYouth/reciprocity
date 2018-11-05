# balabala

* SpringBoot + SpringCloud + Mysql + MongoDB + Mybatis(SpringData JPA) + Jenkins + Redis + ElasticJob

* 使用Consul作为服务注册与服务发现中心，Zuul作为服务路由，Ribbon作为服务负载均衡，Hystrix作为服务熔断器。各个服务之间通过Feign进行服务接口调用，对外提供RESTful风格接口。当客户端调用支付接口进行相关操作时，所有的请求被API网关截获，可以先做权限认证，然后通过负载均衡（ribbon）调用后台服务。首先会通过zuul根据传递的相关key值，选择不同的支付渠道，并使用Redis分布式锁锁定每一笔交易，防止支付交易重复提交。

* 请求统一通过API网关（Zuul）来访问内部服务，网关接收到请求后，从注册中心（Consul）获取可用服务，由Ribbon进行均衡负载后，分发到后端具体实例，微服务之间通过Feign进行通信处理业务。Hystrix负责处理服务超时熔断。

* 使用ElasticJob定时任务框架，实现发送和查询支付交易，做到客户无感支付。处理结果通过SpringDataJPA或Mybatis固化到数据库中

* <https://juejin.im/post/5b1c76f4e51d450688135cfd>

## balabalala

* Spring + Mysql + Mybatis + Oss + RabbitMQ + Jenkins