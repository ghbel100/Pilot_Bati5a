package hhu.propra.bati5a;

import hhu.propra.bati5a.domain.model.Course;
import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.topic.Markdown;
import hhu.propra.bati5a.domain.model.topic.Topic;
import hhu.propra.bati5a.domain.service.MatchRating;
import hhu.propra.bati5a.domain.service.MatchingService;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchingServiceTest {

    @Test
    public void testRateTopics() {
        MatchingService service = new MatchingService();

        Tag javaTag = new Tag("Java");
        Tag aiTag = new Tag("AI");
        Course oopCourse = new Course("OOP");

        Student student = new Student("Student", "CS", java.time.LocalDate.now().minusYears(20), "s@test.com");
        student.setInterests(new HashSet<>(Arrays.asList(javaTag, aiTag)));
        student.setPassedCourses(new HashSet<>(Collections.singletonList(oopCourse)));

        Topic topic1 = new Topic("Java Topic", "http://l1", new Markdown("Desc"), Collections.singleton(oopCourse),
                new HashSet<>(Collections.singletonList(javaTag)));
        Topic topic2 = new Topic("AI Topic", "http://l2", new Markdown("Desc"), Collections.singleton(oopCourse),
                new HashSet<>(Arrays.asList(javaTag, aiTag)));
        Topic topic3 = new Topic("Hard Topic", "http://l3", new Markdown("Desc"),
                Collections.singleton(new Course("Math")),
                new HashSet<>(Collections.singletonList(aiTag)));

        List<MatchRating<Topic>> results = service.rateTopics(student, Arrays.asList(topic1, topic2, topic3));

        // Topic 2: 10 (req met) + 2 (tags) = 12
        // Topic 1: 10 (req met) + 1 (tag) = 11
        // Topic 3: 0 (req not met)

        assertEquals(3, results.size());
        assertEquals(topic2, results.get(0).getEntity());
        assertEquals(12, results.get(0).getScore());

        assertEquals(topic1, results.get(1).getEntity());
        assertEquals(11, results.get(1).getScore());

        assertEquals(topic3, results.get(2).getEntity());
        assertEquals(0, results.get(2).getScore());
    }

    @Test
    public void testRateAdvisors() {
        MatchingService service = new MatchingService();
        Tag javaTag = new Tag("Java");
        Tag webTag = new Tag("Web");

        Student student = new Student("Student", "CS", java.time.LocalDate.now().minusYears(20), "s@test.com");
        student.setInterests(new HashSet<>(Collections.singletonList(javaTag)));

        Advisor advisor1 = new Advisor("Adv1", "CS", java.time.LocalDate.now().minusYears(30), "a1@test.com");
        advisor1.setTags(new HashSet<>(Arrays.asList(javaTag, webTag))); // 1 match

        Advisor advisor2 = new Advisor("Adv2", "CS", java.time.LocalDate.now().minusYears(30), "a2@test.com");
        advisor2.setTags(new HashSet<>(Collections.singletonList(webTag))); // 0 matches

        List<MatchRating<Advisor>> results = service.rateAdvisors(student, Arrays.asList(advisor1, advisor2));

        assertEquals(advisor1, results.get(0).getEntity());
        assertEquals(1, results.get(0).getScore());

        assertEquals(advisor2, results.get(1).getEntity());
        assertEquals(0, results.get(1).getScore());
    }

    @Test
    public void testRateTopicsEmptyList() {
        MatchingService service = new MatchingService();
        Student student = new Student("Student", "CS", java.time.LocalDate.now().minusYears(20), "s@test.com");
        List<MatchRating<Topic>> results = service.rateTopics(student, Collections.emptyList());
        assertTrue(results.isEmpty());
    }

    @Test
    public void testRateAdvisorsNoMatches() {
        MatchingService service = new MatchingService();
        Tag javaTag = new Tag("Java");
        Tag pythonTag = new Tag("Python");

        Student student = new Student("Student", "CS", java.time.LocalDate.now().minusYears(20), "s@test.com");
        student.setInterests(new HashSet<>(Collections.singletonList(javaTag)));

        Advisor advisor = new Advisor("Adv", "CS", java.time.LocalDate.now().minusYears(30), "a@test.com");
        advisor.setTags(new HashSet<>(Collections.singletonList(pythonTag)));

        List<MatchRating<Advisor>> results = service.rateAdvisors(student, List.of(advisor));

        assertEquals(0, results.get(0).getScore());
    }

    @Test
    public void testRateTopicsCaseInsensitive() {
        MatchingService service = new MatchingService();

        // System has "Java"
        Tag systemTag = new Tag("Java");
        Course systemCourse = new Course("OOP");

        Topic topic = new Topic("Java Topic", "http://l1", new Markdown("Desc"), 
                Collections.singleton(systemCourse),
                new HashSet<>(Collections.singletonList(systemTag)));

        // Student inputs "java" and "oop" (lowercase)
        Student student = new Student("Student", "CS", java.time.LocalDate.now().minusYears(20), "s@test.com");
        student.setInterests(new HashSet<>(Collections.singletonList(new Tag("java"))));
        student.setPassedCourses(new HashSet<>(Collections.singletonList(new Course("oop"))));

        List<MatchRating<Topic>> results = service.rateTopics(student, Collections.singletonList(topic));

        // Should match despite case difference
        // 10 (req met) + 1 (tag) = 11
        assertEquals(11, results.get(0).getScore());
    }
}
