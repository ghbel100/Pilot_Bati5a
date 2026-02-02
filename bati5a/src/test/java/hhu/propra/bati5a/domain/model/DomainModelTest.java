package hhu.propra.bati5a.domain.model;

import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.topic.Markdown;
import hhu.propra.bati5a.domain.model.topic.Topic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DomainModelTest {

    @Test
    @DisplayName("Advisor should correctly manage tags")
    void testAdvisorTags() {
        Advisor advisor = new Advisor("Dr. Test", "CS", LocalDate.of(1980, 1, 1), "test@hhu.de");
        Tag tag1 = new Tag("Java");
        Tag tag2 = new Tag("Spring");

        advisor.setTags(Set.of(tag1, tag2));

        assertEquals(2, advisor.getTags().size());
        assertTrue(advisor.getTags().contains(tag1));
        assertTrue(advisor.getTags().contains(tag2));
    }

    @Test
    @DisplayName("Student should correctly calculate age")
    void testStudentAge() {
        LocalDate birthday = LocalDate.now().minusYears(20);
        Student student = new Student("Student", "CS", birthday, "student@hhu.de");

        assertEquals(20, student.getAge());
    }

    @Test
    @DisplayName("Topic should be initialized with correct values")
    void testTopicInitialization() {
        Markdown description = new Markdown("This is a test topic");
        Course course = new Course("Propra 2");
        Tag tag = new Tag("TDD");
        Topic topic = new Topic("Test Topic", "http://test.com", description, Set.of(course), Set.of(tag));

        assertEquals("Test Topic", topic.getTitle());
        assertEquals("http://test.com", topic.getLink());
        assertEquals(description, topic.getDescription());
        assertTrue(topic.getRequiredCourses().contains(course));
        assertTrue(topic.getTags().contains(tag));
    }

    @Test
    @DisplayName("Tag equality and hashing")
    void testTagEquality() {
        Tag tag1 = new Tag("Java");
        Tag tag2 = new Tag("java"); // Assuming case-insensitive or specific behavior if implemented, but let's
                                    // check literal equality
        Tag tag3 = new Tag("Java");

        assertEquals(tag1, tag3);
        assertNotEquals(tag1, tag2);
        assertEquals(tag1.hashCode(), tag3.hashCode());
    }
}
