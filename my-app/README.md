# 工程简介



# 延伸阅读

此处的mapper层就是dao层

查询解释：controller层-----> service层(接口—>接口实现类) -----> dao层的.mapper文件  -----> 和mapper层里的.xml文件对应

自己理解：
controller
==>service
==>serviceImpl
==>com的mapper，是【接口】，也就是dao层
==>resources的mapper，也就是xml文件
抽象接口，直接和它打交道就是对表的存取(dao层，这里的mapper文件夹)==>xml

###查看被占用的端口号并结束进程的办法
1.查看被占用的端口
netstat -ano | findstr 端口号
netstat -ano | findstr 8081

 2.杀死进程
taskkill -pid 进程号 -f
taskkill -pid 5484 -f

3、后端登录功能实现

【登录过滤】
WebSecurityConfig       配置类
JwtAuthenticationFilter 登录验证过滤类 验证逻辑写在SecurityUtils里面的check方法
JwtTokenUtils 的getAuthenticationeFromToken方法获取并校验token信息
                getToken方法从请求头中获取可能携带的令牌“Authorization”或“Bearer”开头的信息，如果没有就访问“token”参数来获取

【身份验证】
Spring Security的登录验证是ProviderManager负责调用AuthenticationProvider的authenticate方法来验证的。
JwtAuthenticationProvider继承DaoAuthenticationProvider并覆盖重写additionalAuthenticationChecks方法来实现密码校验

【认证信息查询】
获取用户信息的任务是通过Spring Security的UserDetailsService来实现的


