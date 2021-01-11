package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.NetworkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"acdh.oeaw.ac.at.dylenegonetworkserice.service", "acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository"})
public class AppConfig {

}
