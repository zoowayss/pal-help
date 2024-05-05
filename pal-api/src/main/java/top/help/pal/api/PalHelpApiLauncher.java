package top.help.pal.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("top.help.pal.**")
@MapperScan("top.help.pal.api.mapper.**")
public class PalHelpApiLauncher {

	public static void main(String[] args) {
		SpringApplication.run(PalHelpApiLauncher.class, args);
	}

}
