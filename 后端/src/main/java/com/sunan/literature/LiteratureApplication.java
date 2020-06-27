package com.sunan.literature;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sunan.literature.dao")
public class LiteratureApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiteratureApplication.class, args);
    }

}
