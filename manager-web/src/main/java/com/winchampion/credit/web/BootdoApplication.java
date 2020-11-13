package com.winchampion.credit.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.winchampion.credit.*.dao")
@SpringBootApplication(scanBasePackages = "com.winchampion.credit")
@EnableCaching
public class BootdoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootdoApplication.class, args);
        System.out.println("XYD_MANAGER启动成功\n" +
                "\n" +
                "  \\\\    //   \\\\    //   ||﹊﹊﹊`.             \n" +
                "   \\\\  //     \\\\  //    ||﹊ ﹊`.\\\\            \n" +
                "    \\\\//       \\\\//     ||       ||           \n" +
                "    //\\\\        ||      ||       ||           \n" +
                "   //  \\\\       ||      ||______ '/           \n" +
                "  //    \\\\      ||      ||______.'               ");
    }
}
