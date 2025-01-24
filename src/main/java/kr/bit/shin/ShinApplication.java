package kr.bit.shin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication	//web.xml 역할 -> DispatcherServlet
public class ShinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShinApplication.class, args);
	}

}
