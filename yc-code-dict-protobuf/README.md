# yc-code-dict-protobuf

Protobuf的基本用法

Protobuf可以理解为一种类似JSON的结构，特点就是双方各约定一份proto文件，严格一致

传输时不携带变量名以及JSON的结构括号，只带变量值，节省带宽提高效，适用于硬件高频传输

这里采用Google原生的maven插件，自动根据pb文件生成java类

实际使用时，通常是将两端getBytes后，base64传输

几个关键词

1. Protobuf的版本，2还是3
2. 对于oneof和repeat的使用
3. pb文件中记得加，其中_是个人习惯，主要区分哪些是自动生成的
``` java
package vxII;
option java_package = "com.code.dict.protobuf.pb.v1";
option java_outer_classname = "_VehClass";
```
         
