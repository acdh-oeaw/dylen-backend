package acdh.oeaw.ac.at.dylenegonetworkserice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord",
                "acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord",
                "acdh.oeaw.ac.at.dylenegonetworkserice.service.generalNetworks",
                "acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.generalNetworks"})
public class AppConfig {

}
