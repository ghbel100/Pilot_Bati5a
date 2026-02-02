package hhu.propra.bati5a.domain.model.person;

import hhu.propra.bati5a.domain.model.Course;
import hhu.propra.bati5a.domain.model.Tag;

import java.time.LocalDate;
import java.util.Set;

public class Student extends User {

    private Set<Course> passedCourses;
    private Set<Tag> interests;

    public Student(String name, String branch, LocalDate birthday, String mail) {
        super(name, branch, birthday, mail);
    }

    public Set<Course> getPassedCourses() {
        return passedCourses;
    }

    public void setPassedCourses(Set<Course> passedCourses) {
        this.passedCourses = passedCourses;
    }

    public Set<Tag> getInterests() {
        return interests;
    }

    public void setInterests(Set<Tag> interests) {
        this.interests = interests;
    }

    @Override
    public String getType() {
        return "Student";
    }
}
