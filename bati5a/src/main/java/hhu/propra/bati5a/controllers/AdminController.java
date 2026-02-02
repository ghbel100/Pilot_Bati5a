package hhu.propra.bati5a.controllers;

import hhu.propra.bati5a.application.service.AdminOnly;
import hhu.propra.bati5a.application.service.ProfileService;
import hhu.propra.bati5a.domain.model.person.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AdminOnly
public class AdminController {

    private final ProfileService profileService;

    public AdminController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/admin")
    public String adminDashboard(@AuthenticationPrincipal OAuth2User principal, Model model) {
        List<User> pendingRequests = profileService.getAllPendingAdvisorRequests();
        model.addAttribute("pendingRequests", pendingRequests);
        return "admin";
    }

    @PostMapping("/admin/approve")
    public String approveRequest(@AuthenticationPrincipal OAuth2User principal,
            @RequestParam("login") String login,
            RedirectAttributes redirectAttributes) {
        profileService.approveAdvisorRequest(login);
        redirectAttributes.addFlashAttribute("message", "Anfrage von " + login + " wurde genehmigt!");

        return "redirect:/admin";
    }

    @PostMapping("/admin/reject")
    public String rejectRequest(@AuthenticationPrincipal OAuth2User principal,
            @RequestParam("login") String login,
            RedirectAttributes redirectAttributes) {
        profileService.rejectAdvisorRequest(login);
        redirectAttributes.addFlashAttribute("message", "Anfrage von " + login + " wurde abgelehnt.");

        return "redirect:/admin";
    }
}
