package hhu.propra.bati5a.domain.model.person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class User {

    @NotBlank(message = "Name darf nicht leer sein")
    private String name;

    @NotBlank(message = "Bereich darf nicht leer sein")
    private String branch;

    @NotBlank(message = "Email darf nicht leer sein")
    @Email(message = "Ungültige Email-Adresse")
    private String mail;

    private Integer age;

    @NotNull(message = "Geburtsdatum darf nicht leer sein")
    @Past(message = "Geburtsdatum muss in der Vergangenheit liegen")
    private LocalDate birthday;
    private String githubLogin;
    private String avatarUrl;
    private boolean advisorRequestPending;

    public User(String name, String branch, LocalDate birthday, String mail) {
        this.name = name;
        this.branch = branch;
        this.birthday = birthday;
        this.mail = mail;
        this.age = calculateAge(birthday);
    }


    private Integer calculateAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(birthday, today);
    }

    public String getName() {
        return this.name;
    }

    public String getBranch() {
        return this.branch;
    }

    public String getMail() {
        return this.mail;
    }

    public String getGithubLogin() {
        return githubLogin;
    }

    public void setGithubLogin(String githubLogin) {
        this.githubLogin = githubLogin;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isAdvisorRequestPending() {
        return advisorRequestPending;
    }

    public void setAdvisorRequestPending(boolean advisorRequestPending) {
        this.advisorRequestPending = advisorRequestPending;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        this.age = calculateAge(birthday);
    }

    public String getType() {
        return "User";
    }

}