package hhu.propra.bati5a.application.service;

import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.repository.ProfileRepository;
import hhu.propra.bati5a.domain.model.repository.TopicRepository;
import hhu.propra.bati5a.domain.model.topic.Markdown;
import hhu.propra.bati5a.domain.model.topic.Topic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class BrowsingServiceTest {

    private TopicRepository topicRepository;
    private ProfileRepository profileRepository;
    private BrowsingService browsingService;

    @BeforeEach
    public void setUp() {
        topicRepository = Mockito.mock(TopicRepository.class);
        profileRepository = Mockito.mock(ProfileRepository.class);
        browsingService = new BrowsingService(topicRepository, profileRepository);
    }

    @Test
    public void testGetAllTopics() {
        Topic topic = new Topic("Title", "http://link", new Markdown("Desc"), Collections.emptySet(),
                Collections.emptySet());
        when(topicRepository.findAll()).thenReturn(List.of(topic));

        List<Topic> result = browsingService.getAllTopics();
        assertEquals(1, result.size());
        assertEquals("Title", result.get(0).getTitle());
    }

    @Test
    public void testFilterTopicsByTags() {
        Tag javaTag = new Tag("Java");
        Tag pythonTag = new Tag("Python");

        Topic topic1 = new Topic("Java Topic", "http://link1", new Markdown("Desc"), Collections.emptySet(),
                Set.of(javaTag));
        Topic topic2 = new Topic("Python Topic", "http://link2", new Markdown("Desc"), Collections.emptySet(),
                Set.of(pythonTag));

        when(topicRepository.findAll()).thenReturn(List.of(topic1, topic2));

        List<Topic> result = browsingService.filterTopicsByTags(Set.of(javaTag));
        assertEquals(1, result.size());
        assertEquals("Java Topic", result.get(0).getTitle());
    }

    @Test
    public void testFilterTopicsByTagsCaseInsensitive() {
        Tag javaTag = new Tag("Java");
        Topic topic = new Topic("Java Topic", "http://link", new Markdown("Desc"), Collections.emptySet(),
                Set.of(javaTag));
        when(topicRepository.findAll()).thenReturn(List.of(topic));

        // Filter with lowercase
        List<Topic> result = browsingService.filterTopicsByTags(Set.of(new Tag("java")));
        assertEquals(1, result.size());
        assertEquals("Java Topic", result.get(0).getTitle());

        // Filter with uppercase
        result = browsingService.filterTopicsByTags(Set.of(new Tag("JAVA")));
        assertEquals(1, result.size());
    }

    @Test
    public void testFilterAdvisorsByTags() {
        Tag researchTag = new Tag("Research");
        Advisor advisor = new Advisor("Dr. Smith", "CS", LocalDate.now().minusYears(40), "smith@test.com");
        advisor.setTags(Set.of(researchTag));

        when(profileRepository.findAllAdvisors()).thenReturn(List.of(advisor));

        List<Advisor> result = browsingService.filterAdvisorsByTags(Set.of(researchTag));
        assertEquals(1, result.size());
        assertEquals("Dr. Smith", result.get(0).getName());
    }

    @Test
    public void testGetAllProfiles() {
        Advisor advisor = new Advisor("Advisor", "CS", LocalDate.now().minusYears(40), "a@test.com");
        Student student = new Student("Student", "CS", LocalDate.now().minusYears(20), "s@test.com");

        when(profileRepository.findAllAdvisors()).thenReturn(List.of(advisor));
        when(profileRepository.findAllStudents()).thenReturn(List.of(student));

        List<Object> result = browsingService.getAllProfiles();
        assertEquals(2, result.size());
        assertTrue(result.contains(advisor));
        assertTrue(result.contains(student));
    }
}
