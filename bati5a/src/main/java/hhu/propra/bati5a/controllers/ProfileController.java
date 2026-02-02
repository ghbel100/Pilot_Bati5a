package hhu.propra.bati5a.controllers;

import hhu.propra.bati5a.application.service.FileStorageService;
import hhu.propra.bati5a.application.service.ProfileService;
import hhu.propra.bati5a.controllers.dto.ProfileForm;
import hhu.propra.bati5a.domain.model.Course;
import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.person.User;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class ProfileController {

    private final ProfileService profileService;
    private final FileStorageService fileStorageService;

    public ProfileController(ProfileService profileService,
            FileStorageService fileStorageService) {
        this.profileService = profileService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal == null)
            return "redirect:/";

        String login = principal.getAttribute("login");
        Optional<User> userOpt = profileService.getProfileByGithubLogin(login);

        model.addAttribute("githubProfile", principal.getAttributes());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setGithubLogin(login);
            user.setAvatarUrl(principal.getAttribute("avatar_url"));
            profileService.saveProfile(user);

            model.addAttribute("user", user);
            model.addAttribute("isOwnProfile", true);
            return "profile";
        }

        ProfileForm form = new ProfileForm(
                principal.getAttribute("name") != null ? principal.getAttribute("name")
                        : principal.getAttribute("login"),
                principal.getAttribute("email") != null ? principal.getAttribute("email")
                        : principal.getAttribute("login") + "@github.com",
                "",
                null,
                "Student");
        model.addAttribute("profileForm", form);
        model.addAttribute("needsCreation", true);
        model.addAttribute("isOwnProfile", true);
        return "profile";
    }

    @GetMapping("/profile/{login}")
    public String showPublicProfile(@PathVariable("login") String login, Model model) {
        Optional<User> userOpt = profileService.getProfileByGithubLogin(login);

        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            model.addAttribute("isOwnProfile", false);
            return "profile";
        }
        return "redirect:/topics";
    }

    @PostMapping("/profile/create")
    public String createProfile(@AuthenticationPrincipal OAuth2User principal,
            @Valid @ModelAttribute("profileForm") ProfileForm profileForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (principal == null)
            return "redirect:/";

        if (bindingResult.hasErrors()) {
            model.addAttribute("githubProfile", principal.getAttributes());
            model.addAttribute("needsCreation", true);
            model.addAttribute("isOwnProfile", true);
            return "profile";
        }

        String login = principal.getAttribute("login");
        String avatarUrl = principal.getAttribute("avatar_url");

        // Everyone starts as a Student; if Advisor is requested, it requires approval
        User newUser = new Student(profileForm.name(), profileForm.branch(), profileForm.birthday(),
                profileForm.email());
        newUser.setGithubLogin(login);
        newUser.setAvatarUrl(avatarUrl);

        if ("Advisor".equalsIgnoreCase(profileForm.role())) {
            newUser.setAdvisorRequestPending(true);
            redirectAttributes.addFlashAttribute("message",
                    "Profil erstellt! Ihre Anfrage für die Betreuer-Rolle wurde an den Administrator gesendet.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Profil erfolgreich als Student erstellt!");
        }

        profileService.saveProfile(newUser);

        return "redirect:/profile";
    }

    @PostMapping("/profile/interests/add")
    public String addInterest(@AuthenticationPrincipal OAuth2User principal,
            @Valid @ModelAttribute Tag tag,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (principal == null)
            return "redirect:/";

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/profile";
        }

        String login = principal.getAttribute("login");
        Optional<User> userOpt = profileService.getProfileByGithubLogin(login);

        if (userOpt.isPresent() && userOpt.get() instanceof Student student) {
            Set<Tag> interests = student.getInterests();
            if (interests == null) {
                interests = new HashSet<>();
            }
            interests.add(tag);
            student.setInterests(interests);
            profileService.saveProfile(student);
            redirectAttributes.addFlashAttribute("message", "Interesse '" + tag.getName() + "' hinzugefügt!");
        }

        return "redirect:/profile";
    }

    @PostMapping("/profile/courses/add")
    public String addCourse(@AuthenticationPrincipal OAuth2User principal,
            @Valid @ModelAttribute Course course,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (principal == null)
            return "redirect:/";

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/profile";
        }

        String login = principal.getAttribute("login");
        Optional<User> userOpt = profileService.getProfileByGithubLogin(login);

        if (userOpt.isPresent() && userOpt.get() instanceof Student student) {
            Set<Course> courses = student.getPassedCourses();
            if (courses == null) {
                courses = new HashSet<>();
            }
            courses.add(course);
            student.setPassedCourses(courses);
            profileService.saveProfile(student);
            redirectAttributes.addFlashAttribute("message", "Kurs '" + course.getName() + "' hinzugefügt!");
        }

        return "redirect:/profile";
    }

    @PostMapping("/profile/role/switch")
    public String switchRole(@AuthenticationPrincipal OAuth2User principal,
            RedirectAttributes redirectAttributes) {

        if (principal == null)
            return "redirect:/";

        String login = principal.getAttribute("login");
        Optional<User> userOpt = profileService.getProfileByGithubLogin(login);

        if (userOpt.isPresent()) {
            User current = userOpt.get();
            if (current instanceof Student) {
                // Request advisor role
                current.setAdvisorRequestPending(true);
                profileService.saveProfile(current);
                redirectAttributes.addFlashAttribute("message",
                        "Ihre Anfrage für die Betreuer-Rolle wurde an den Administrator gesendet.");
            } else if (current instanceof Advisor) {
                // Immediately switch to student (downgrade is allowed)
                User switched = new Student(current.getName(), current.getBranch(), current.getBirthday(),
                        current.getMail());
                switched.setGithubLogin(current.getGithubLogin());
                switched.setAvatarUrl(current.getAvatarUrl());
                profileService.saveProfile(switched);
                redirectAttributes.addFlashAttribute("message", "Rolle erfolgreich zu Student gewechselt!");
            }
        }

        return "redirect:/profile";
    }

    @PostMapping("/profile/tags/add")
    public String addAdvisorTag(@AuthenticationPrincipal OAuth2User principal,
            @Valid @ModelAttribute Tag tag,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (principal == null)
            return "redirect:/";

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/profile";
        }

        profileService.addAdvisorTag(principal.getAttribute("login"), tag.getName());
        redirectAttributes.addFlashAttribute("message", "Tag '" + tag.getName() + "' hinzugefügt!");

        return "redirect:/profile";
    }

    @PostMapping("/profile/links/update")
    public String updateAdvisorLinks(@AuthenticationPrincipal OAuth2User principal,
            @RequestParam("links") String links,
            RedirectAttributes redirectAttributes) {

        if (principal == null)
            return "redirect:/";

        profileService.updateAdvisorLinks(principal.getAttribute("login"), links);
        redirectAttributes.addFlashAttribute("message", "Links aktualisiert!");

        return "redirect:/profile";
    }

    @PostMapping("/profile/upload")
    public String handleFileUpload(@AuthenticationPrincipal OAuth2User principal,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if (principal == null)
            return "redirect:/";

        String login = principal.getAttribute("login");
        try {
            File savedFile = fileStorageService.storeFile(file);
            profileService.addAdvisorFile(login, savedFile.getName());
            redirectAttributes.addFlashAttribute("message",
                    "Datei '" + file.getOriginalFilename() + "' erfolgreich hochgeladen!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Fehler beim Hochladen: " + e.getMessage());
        }

        return "redirect:/profile";
    }

    @GetMapping("/profile/files/download")
    public ResponseEntity<Resource> downloadFile(
            @AuthenticationPrincipal OAuth2User principal,
            @RequestParam("name") String fileName) {

        if (principal == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .build();

        try {
            Resource resource = fileStorageService.loadFileAsResource(fileName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
