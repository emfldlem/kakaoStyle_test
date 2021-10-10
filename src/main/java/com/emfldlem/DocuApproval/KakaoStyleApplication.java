package com.emfldlem.DocuApproval;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.emfldlem.*")
@MapperScan("com.emfldlem.*")
public class KakaoStyleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoStyleApplication.class, args);
    }

}
