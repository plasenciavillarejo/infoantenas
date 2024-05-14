package es.gob.info.ant.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching
@Configuration
public class CacheConfig {

  @Bean
  Caffeine<Object, Object> caffeineConfig() {
    return Caffeine.newBuilder()
        .expireAfterWrite(1, TimeUnit.MINUTES)
        .initialCapacity(10);
  }

  @Bean
  CacheManager cacheManager() {
    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
    //caffeineCacheManager.setCaffeine(caffeineConfig());
    caffeineCacheManager.setCacheNames(Arrays.asList("provinciasCacheadas", "municipiosCacheados"));
    return caffeineCacheManager;
  }
  
}
