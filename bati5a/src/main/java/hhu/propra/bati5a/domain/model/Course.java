package hhu.propra.bati5a.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public class Course {
    @NotBlank(message = "Kursname darf nicht leer sein")
    @Size(min = 2, max = 100, message = "Kursname muss zwischen 2 und 100 Zeichen lang sein")
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name);
    }

}
