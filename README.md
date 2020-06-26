# java-example
java example info


###1，微内核-plug方式,基于SPI的约定
##1）设计一个服务接口，并提供对应的实现类，可根据需要提供不同的具体的实现
##2）在jar包的META-INF/servies目录中创建一个以服务接口命名的文件，配制该接口的具体实现
##3）外部程序通过jar包META-INF/services/目录下的配制文件找到具体的实现类名并实例化。


###2，基于java的一个包之间的循环依赖的案例，使用eclipse安装插件jdepend进行检查依赖关系,
##方法1，在eclipse的插件商店中搜索jdepend安装
##方法2，在线安装插件http://andrei.gmxhome.de/eclipse/

