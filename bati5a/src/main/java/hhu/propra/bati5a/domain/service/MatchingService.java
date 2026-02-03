package hhu.propra.bati5a.domain.service;

import hhu.propra.bati5a.domain.model.Course;
import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.topic.Topic;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MatchingService {

    public List<MatchRating<Topic>> rateTopics(Student student, List<Topic> topics) {
        return topics.stream()
                .map(topic -> new MatchRating<>(topic, calculateTopicScore(student, topic)))
                .sorted(Comparator.comparingInt(MatchRating::getScore))
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
                }));
    }

    public List<MatchRating<Advisor>> rateAdvisors(Student student, List<Advisor> advisors) {
        return advisors.stream()
                .map(advisor -> new MatchRating<>(advisor, calculateAdvisorScore(student, advisor)))
                .sorted(Comparator.comparingInt(MatchRating::getScore))
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
                }));
    }

    private int calculateTopicScore(Student student, Topic topic) {
        int score = 0;

        // Check course requirements
        Set<Course> required = topic.getRequiredCourses();
        if (required != null && !required.isEmpty()) {
            Set<Course> passed = student.getPassedCourses();
            if (passed == null)
                passed = Collections.emptySet();

            Set<String> passedNames = passed.stream()
                    .map(c -> c.getName().toLowerCase())
                    .collect(Collectors.toSet());

            boolean allRequiredMet = required.stream()
                    .map(c -> c.getName().toLowerCase())
                    .allMatch(passedNames::contains);

            if (allRequiredMet) {
                score += 10;
            } else {
                return 0; // Prerequisite not met
            }
        } else {
            score += 10; // No requirements is good
        }

        // Check tag overlap
        Set<Tag> studentInterests = student.getInterests();
        Set<Tag> topicTags = topic.getTags();

        if (studentInterests != null && topicTags != null) {
            Set<String> studentTagNames = studentInterests.stream()
                    .map(t -> t.getName().toLowerCase())
                    .collect(Collectors.toSet());

            long matches = topicTags.stream()
                    .map(t -> t.getName().toLowerCase())
                    .filter(studentTagNames::contains)
                    .count();
            score += (int) matches;
        }

        return score;
    }

    private int calculateAdvisorScore(Student student, Advisor advisor) {
        int score = 0;
        Set<Tag> studentInterests = student.getInterests();
        Set<Tag> advisorTags = advisor.getTags();

        if (studentInterests != null && advisorTags != null) {
            Set<String> studentTagNames = studentInterests.stream()
                    .map(t -> t.getName().toLowerCase())
                    .collect(Collectors.toSet());

            long matches = advisorTags.stream()
                    .map(t -> t.getName().toLowerCase())
                    .filter(studentTagNames::contains)
                    .count();
            score += (int) matches;
        }
        return score;
    }
}
