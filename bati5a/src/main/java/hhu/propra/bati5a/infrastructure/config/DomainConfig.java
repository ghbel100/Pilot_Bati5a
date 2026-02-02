package hhu.propra.bati5a.infrastructure.config;

import hhu.propra.bati5a.application.service.FileStorageService;
import hhu.propra.bati5a.application.service.ProfileService;
import hhu.propra.bati5a.domain.model.repository.ProfileRepository;
import hhu.propra.bati5a.domain.model.repository.TopicRepository;
import hhu.propra.bati5a.application.service.BrowsingService;
import hhu.propra.bati5a.domain.service.MatchingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public BrowsingService browsingService(TopicRepository topicRepository, ProfileRepository profileRepository) {
        return new BrowsingService(topicRepository, profileRepository);
    }

    @Bean
    public MatchingService matchingService() {
        return new MatchingService();
    }

    @Bean
    public ProfileService profileService(
            ProfileRepository profileRepository) {
        return new ProfileService(profileRepository);
    }

    @Bean
    public FileStorageService fileStorageService() {
        return new FileStorageService();
    }
}
