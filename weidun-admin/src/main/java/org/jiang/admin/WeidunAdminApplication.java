package org.jiang.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description 入口
 * @Author jiang
 * @Create 2021/1/5
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "org.jiang")
public class WeidunAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeidunAdminApplication.class);
    }
}
