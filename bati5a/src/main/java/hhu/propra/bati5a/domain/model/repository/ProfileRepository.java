package hhu.propra.bati5a.domain.model.repository;

import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.person.User;
import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    List<Advisor> findAllAdvisors();

    List<Student> findAllStudents();

    Optional<User> findByMail(String email);

    Optional<User> findByGithubLogin(String login);

    List<User> findAllPendingAdvisorRequests();

    void save(User user);
}
