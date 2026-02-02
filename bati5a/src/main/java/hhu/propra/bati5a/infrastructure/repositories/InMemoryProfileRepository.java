package hhu.propra.bati5a.infrastructure.repositories;

import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.person.User;
import hhu.propra.bati5a.domain.model.repository.ProfileRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryProfileRepository implements ProfileRepository {

    private final List<Advisor> advisors = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();

    public InMemoryProfileRepository() {
        // Dummy Advisors
        advisors.add(new Advisor("Dr. Jens Müller", "Software Engineering", LocalDate.of(1980, 5, 10),
                "jens.mueller@hhu.de"));
        advisors.get(0).setTags(Set.of(new Tag("Java"), new Tag("Architecture")));
        advisors.get(0).setGithubLogin("jens-mueller");

        advisors.add(new Advisor("Prof. Sarah Schmidt", "AI & Data Science", LocalDate.of(1975, 11, 20),
                "sarah.schmidt@hhu.de"));
        advisors.get(1).setTags(Set.of(new Tag("Python"), new Tag("AI")));

        // Dummy Students
        students.add(new Student("Max Mustermann", "Computer Science", LocalDate.of(2000, 1, 1), "max@student.hhu.de"));
        students.get(0).setInterests(Set.of(new Tag("Java"), new Tag("Web")));
        students.get(0).setGithubLogin("max-mustermann");
    }

    @Override
    public List<Advisor> findAllAdvisors() {
        return Collections.unmodifiableList(advisors);
    }

    @Override
    public List<Student> findAllStudents() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public List<User> findAllPendingAdvisorRequests() {
        return Stream.concat(advisors.stream(), students.stream())
                .filter(User::isAdvisorRequestPending)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByMail(String email) {
        return Stream.concat(advisors.stream(), students.stream())
                .filter(u -> u.getMail().equalsIgnoreCase(email))
                .findFirst()
                .map(u -> (User) u);
    }

    @Override
    public Optional<User> findByGithubLogin(String login) {
        if (login == null)
            return java.util.Optional.empty();
        return Stream.concat(advisors.stream(), students.stream())
                .filter(u -> u.getGithubLogin() != null && u.getGithubLogin().equalsIgnoreCase(login))
                .findFirst()
                .map(u -> (User) u);
    }

    @Override
    public void save(User user) {
        // Remove existing entries to handle type swaps or updates
        advisors.removeIf(
                a -> (user.getGithubLogin() != null && user.getGithubLogin().equalsIgnoreCase(a.getGithubLogin())) ||
                        (user.getMail() != null && user.getMail().equalsIgnoreCase(a.getMail())));
        students.removeIf(
                s -> (user.getGithubLogin() != null && user.getGithubLogin().equalsIgnoreCase(s.getGithubLogin())) ||
                        (user.getMail() != null && user.getMail().equalsIgnoreCase(s.getMail())));

        if (user instanceof Advisor advisor) {
            advisors.add(advisor);
        } else if (user instanceof Student student) {
            students.add(student);
        } else {
            Student s = new Student(user.getName(), user.getBranch(), user.getBirthday(), user.getMail());
            s.setGithubLogin(user.getGithubLogin());
            s.setAvatarUrl(user.getAvatarUrl());
            students.add(s);
        }
    }
}
