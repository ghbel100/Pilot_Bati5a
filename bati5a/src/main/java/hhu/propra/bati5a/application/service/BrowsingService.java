package hhu.propra.bati5a.application.service;

import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.repository.ProfileRepository;
import hhu.propra.bati5a.domain.model.repository.TopicRepository;
import hhu.propra.bati5a.domain.model.topic.Topic;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrowsingService {

    private final TopicRepository topicRepository;
    private final ProfileRepository profileRepository;

    public BrowsingService(TopicRepository topicRepository, ProfileRepository profileRepository) {
        this.topicRepository = topicRepository;
        this.profileRepository = profileRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public List<Advisor> getAllAdvisors() {
        return profileRepository.findAllAdvisors();
    }

    public List<Student> getAllStudents() {
        return profileRepository.findAllStudents();
    }

    public List<Object> getAllProfiles() {
        return Stream.concat(
                profileRepository.findAllAdvisors().stream(),
                profileRepository.findAllStudents().stream()).collect(Collectors.toList());
    }

    public List<Topic> filterTopicsByTags(Set<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            return getAllTopics();
        }
        Set<String> searchTagNames = tags.stream()
                .map(t -> t.getName().toLowerCase())
                .collect(Collectors.toSet());

        return topicRepository.findAll().stream()
                .filter(topic -> topic.getTags() != null && topic.getTags().stream()
                        .anyMatch(t -> searchTagNames.contains(t.getName().toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Advisor> filterAdvisorsByTags(Set<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            return getAllAdvisors();
        }
        Set<String> searchTagNames = tags.stream()
                .map(t -> t.getName().toLowerCase())
                .collect(Collectors.toSet());

        return profileRepository.findAllAdvisors().stream()
                .filter(advisor -> advisor.getTags() != null && advisor.getTags().stream()
                        .anyMatch(t -> searchTagNames.contains(t.getName().toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Student> filterStudentsByInterests(Set<Tag> interests) {
        if (interests == null || interests.isEmpty()) {
            return getAllStudents();
        }
        Set<String> searchTagNames = interests.stream()
                .map(t -> t.getName().toLowerCase())
                .collect(Collectors.toSet());

        return profileRepository.findAllStudents().stream()
                .filter(student -> student.getInterests() != null
                        && student.getInterests().stream()
                                .anyMatch(t -> searchTagNames.contains(t.getName().toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Object> filterAllProfilesByTags(Set<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            return getAllProfiles();
        }

        return Stream.concat(
                filterAdvisorsByTags(tags).stream(),
                filterStudentsByInterests(tags).stream()).collect(Collectors.toList());
    }
}
