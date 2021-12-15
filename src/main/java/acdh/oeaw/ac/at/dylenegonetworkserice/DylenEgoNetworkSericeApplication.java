package acdh.oeaw.ac.at.dylenegonetworkserice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org
		.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@SpringBootApplication
@EnableAsync
public class DylenEgoNetworkSericeApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DylenEgoNetworkSericeApplication.class, args);
	}

}
