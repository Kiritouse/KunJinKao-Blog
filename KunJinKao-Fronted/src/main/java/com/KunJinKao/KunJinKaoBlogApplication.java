package com.KunJinKao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.KunJinKao.mapper")
public class KunJinKaoBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(KunJinKaoBlogApplication.class, args);
    }

}
