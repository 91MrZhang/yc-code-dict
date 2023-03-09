# yc-code-dict-nacos

nacos接入示例

这里maven的pom文件并不规范，只是为了展示依赖项目

实际开发一般都是会有父pom文件来直接依赖，并不用子项目再引用

包括fegin-client，实际上生产使用时，也是将fegin相关接口统一定义在公共jar包中，各个组件依赖

其中nacos实际使用时，也会有group和命名空间隔离，注意不要混淆