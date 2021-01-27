package acdh.oeaw.ac.at.dylenegonetworkservice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"acdh.oeaw.ac.at.dylenegonetworkservice.service", "acdh.oeaw.ac.at.dylenegonetworkservice.persistence" +
        ".repository"})
public class AppConfig {

}
