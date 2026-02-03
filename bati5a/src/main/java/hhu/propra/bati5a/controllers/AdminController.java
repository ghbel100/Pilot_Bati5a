package hhu.propra.bati5a.controllers;

import hhu.propra.bati5a.application.service.AdminOnly;
import hhu.propra.bati5a.application.service.ProfileService;
import hhu.propra.bati5a.domain.model.person.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Controller
@AdminOnly
public class AdminController {

    private final ProfileService profileService;

    public AdminController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        List<User> pendingRequests = profileService.getAllPendingAdvisorRequests();
        model.addAttribute("pendingRequests", pendingRequests);
        return "admin";
    }

    @PostMapping("/admin/create-profile")
    public String createProfile(
            @RequestParam("type") String type,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("branch") String branch,
            @RequestParam("birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
            @RequestParam("githubLogin") String githubLogin,
            RedirectAttributes redirectAttributes) {
        
        try {
            User newUser;
            if ("student".equalsIgnoreCase(type)) {
                newUser = new Student(name, branch, birthday, email);
            } else if ("advisor".equalsIgnoreCase(type)) {
                newUser = new Advisor(name, branch, birthday, email);
            } else {
                throw new IllegalArgumentException("Ungültiger Profiltyp: " + type);
            }
            
            newUser.setGithubLogin(githubLogin);
            profileService.saveProfile(newUser);
            
            redirectAttributes.addFlashAttribute("message", "Profil für " + name + " (" + type + ") wurde erfolgreich erstellt.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Fehler beim Erstellen des Profils: " + e.getMessage());
        }
        
        return "redirect:/admin";
    }

    @PostMapping("/admin/approve")
    public String approveRequest(@RequestParam("login") String login,
            RedirectAttributes redirectAttributes) {
        profileService.approveAdvisorRequest(login);
        redirectAttributes.addFlashAttribute("message", "Anfrage von " + login + " wurde genehmigt!");

        return "redirect:/admin";
    }

    @PostMapping("/admin/reject")
    public String rejectRequest(@RequestParam("login") String login,
            RedirectAttributes redirectAttributes) {
        profileService.rejectAdvisorRequest(login);
        redirectAttributes.addFlashAttribute("message", "Anfrage von " + login + " wurde abgelehnt.");

        return "redirect:/admin";
    }
}
