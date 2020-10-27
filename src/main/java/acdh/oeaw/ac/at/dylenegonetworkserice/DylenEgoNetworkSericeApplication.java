package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org
		.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DylenEgoNetworkSericeApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DylenEgoNetworkSericeApplication.class, args);

		var corpusService = context.getBean(CorpusService.class);
		corpusService.getAllCorpora();
	}

}
