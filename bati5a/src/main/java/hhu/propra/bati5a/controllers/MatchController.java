package hhu.propra.bati5a.controllers;

import hhu.propra.bati5a.application.service.BrowsingService;
import hhu.propra.bati5a.application.service.ProfileService;
import hhu.propra.bati5a.domain.model.Course;
import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.person.User;
import hhu.propra.bati5a.domain.service.MatchingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MatchController {

    private final MatchingService matchingService;
    private final BrowsingService browsingService;
    private final ProfileService profileService;

    public MatchController(MatchingService matchingService, BrowsingService browsingService, ProfileService profileService) {
        this.matchingService = matchingService;
        this.browsingService = browsingService;
        this.profileService = profileService;
    }

    @GetMapping("/match")
    public String showMatchForm(@AuthenticationPrincipal OAuth2User principal, 
                                @CookieValue(value = "bati5a_courses", required = false) String cookieCourses,
                                @CookieValue(value = "bati5a_interests", required = false) String cookieInterests,
                                Model model) {
        String courses = null;
        String interests = null;

        if (principal != null) {
            String login = principal.getAttribute("login");
            Optional<User> userOpt = profileService.getProfileByGithubLogin(login);
            if (userOpt.isPresent() && userOpt.get() instanceof Student student) {
                Set<Course> passedCoursesSet = student.getPassedCourses();
                if (passedCoursesSet != null) {
                    courses = passedCoursesSet.stream()
                            .map(Course::getName)
                            .collect(Collectors.joining(", "));
                }
                
                Set<Tag> interestsSet = student.getInterests();
                if (interestsSet != null) {
                    interests = interestsSet.stream()
                            .map(Tag::getName)
                            .collect(Collectors.joining(", "));
                }
            }
        }

        // If no user data, use cookies
        if (courses == null && cookieCourses != null) {
            courses = java.net.URLDecoder.decode(cookieCourses, java.nio.charset.StandardCharsets.UTF_8);
        }
        if (interests == null && cookieInterests != null) {
            interests = java.net.URLDecoder.decode(cookieInterests, java.nio.charset.StandardCharsets.UTF_8);
        }

        model.addAttribute("courses", courses);
        model.addAttribute("interests", interests);
        return "match";
    }

    @PostMapping("/match")
    public String processMatch(@RequestParam String courses,
                               @RequestParam String interests,
                               HttpServletResponse response,
                               Model model) {
        
        // Save to cookies
        if (courses != null) {
            String encodedCourses = java.net.URLEncoder.encode(courses, java.nio.charset.StandardCharsets.UTF_8);
            Cookie coursesCookie = new Cookie("bati5a_courses", encodedCourses);
            coursesCookie.setPath("/");
            response.addCookie(coursesCookie);
        }

        if (interests != null) {
            String encodedInterests = java.net.URLEncoder.encode(interests, java.nio.charset.StandardCharsets.UTF_8);
            Cookie interestsCookie = new Cookie("bati5a_interests", encodedInterests);
            interestsCookie.setPath("/");
            response.addCookie(interestsCookie);
        }
        
        Set<Course> passedCourses = new HashSet<>();
        if (courses != null && !courses.isBlank()) {
            passedCourses = Arrays.stream(courses.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Course::new)
                    .collect(Collectors.toSet());
        }

        Set<Tag> interestTags = new HashSet<>();
        if (interests != null && !interests.isBlank()) {
            interestTags = Arrays.stream(interests.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Tag::new)
                    .collect(Collectors.toSet());
        }

        // Create a temporary student object for matching
        Student student = new Student("Guest", "N/A", LocalDate.now(), "guest@example.com");
        student.setPassedCourses(passedCourses);
        student.setInterests(interestTags);

        var topicRatings = matchingService.rateTopics(student, browsingService.getAllTopics());
        var advisorRatings = matchingService.rateAdvisors(student, browsingService.getAllAdvisors());
        
        model.addAttribute("topicRatings", topicRatings);
        model.addAttribute("advisorRatings", advisorRatings);
        model.addAttribute("courses", courses);
        model.addAttribute("interests", interests);
        model.addAttribute("showResults", true);

        return "match";
    }
}
