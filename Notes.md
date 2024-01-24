# yml文件的配置

属性名前千万不要有缩进,一定要注意格式!!!!

# 前端项目编译成功,但是在浏览器还是打不开

是因为Cookie超过了浏览器的最大储存了,只需要清空一下Cookie即可

#  配置FastJson来转换json的格式来匹配日期

在config设置里的webconfig

# @PathVariable的作用

> `@PathVariable` 是 Java Spring 框架中的一个注解，它可以帮助你从 URL 中获取一些信息。比如，你有一个 URL 是 `/api/employees/{id}`，这里的 `{id}` 是一个占位符，表示员工的 ID。你可以使用 `@PathVariable` 注解来获取这个 `{id}` 的值。这样，你就可以根据这个 id 来获取或操作对应的员工信息了。。



# 登录接口一般都是用Post请求

1. token进行校验
2. userInfo



# 在Controller里,如果是querry类型的参数就不需要加注解



# @Data注解,会自动读取配置文件中的同名字段,然后自动生成get,set方法

# 七牛云OssUploadService中的外链域名可能会过期,过期了记得自己改

# 若传入后端的参数中有Json格式,那么需要在Controller对应的同名形参前加上@RequestBody 对应的实体类