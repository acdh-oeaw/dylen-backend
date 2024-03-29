package acdh.oeaw.ac.at.dylenegonetworkserice;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@EnableCaching
@Configuration
public class CacheConfig{
    @Bean(name = "cacheMgr")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("corpora", "sources", "targetwords", "generalNet");
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor(); // Or use another one of your liking
    }
}