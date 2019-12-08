demo-common： 公共代码模块，主要放置一些工具类
demo-core： 封装业务模块，主要封装公共业务模块
demo-admin： 后台管理模块，包含用户、角色、菜单管理等
demo-backup： 系统数据备份还原模块，可选择独立部署
demo-monitor： 系统监控服务端，监控Spring Boot应用
demo-producer： 服务提供者示例，方便在此基础上搭建模块
demo-consumer： 服务消费者示例，方便在此基础上搭建模块
demo-hystrix： 服务熔断监控模块，收集汇总熔断统计信息
demo-zuul： API服务网关模块，统一管理和转发外部调用请求
demo-config： 配置中心服务端，生成GIT配置文件的访问接口
config-repo： 配置中心仓库，在GIT上统一存储系统配置文件
demo-pom： 聚合模块，仅为简化打包，一键执行打包所有模块

consul 注册中心单独下载
