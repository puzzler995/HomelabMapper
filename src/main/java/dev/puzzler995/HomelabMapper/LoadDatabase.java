package dev.puzzler995.HomelabMapper;

import dev.puzzler995.HomelabMapper.data.repository.ApplicationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.puzzler995.HomelabMapper.data.model.Application;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ApplicationRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Application("Plex", "Media Server", "https://plex.puzzler995.dev", "https://plex.puzzler995.dev/web/favicon.ico")));
            log.info("Preloading " + repository.save(new Application("Jellyfin", "Media Server", "https://jellyfin.puzzler995.dev", "https://jellyfin.puzzler995.dev/web/favicon.ico")));
            log.info("Preloading " + repository.save(new Application("Sonarr", "TV Show Downloader", "https://sonarr.puzzler995.dev", "https://sonarr.puzzler995.dev/favicon.ico")));
        };
    }
}
