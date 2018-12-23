package com.lspringb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.lspringb.web.servlet")
public class LspringbApplication {

	public static void main(String[] args) {
		SpringApplication.run(LspringbApplication.class, args);
	}

}

