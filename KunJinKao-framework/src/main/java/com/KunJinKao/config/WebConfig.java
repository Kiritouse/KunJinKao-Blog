package com.KunJinKao.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration //加了这个注解代表这个对象是来配置信息的
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
// 设置允许跨域的路径
        registry.addMapping("/**")
// 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
// 是否允许cookie
                .allowCredentials(true)
// 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
// 设置允许的header属性
                .allowedHeaders("*")
// 跨域允许时间
                .maxAge(3600);
    }

    //
    @Bean//使用@Bean注入fastJsonHttpMessageConvert
    public HttpMessageConverter fastJsonHttpMessageConverters() {
//1.需要定义一个Convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new
                FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //前端判断管理员权限的时候是用的字符0,所以一定要做转换,将Long类型转换为String类型返回给前端
        SerializeConfig.globalInstance.put(Long.class, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(SerializeConfig.globalInstance);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return converter;
    }
    @Override//配置json转换器
    public void configureMessageConverters(List<HttpMessageConverter<?>>
                                                   converters) {
        converters.add(fastJsonHttpMessageConverters());
    }
}