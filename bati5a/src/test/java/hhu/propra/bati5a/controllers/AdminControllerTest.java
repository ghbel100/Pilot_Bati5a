package hhu.propra.bati5a.controllers;

import hhu.propra.bati5a.application.service.BrowsingService;
import hhu.propra.bati5a.application.service.FileStorageService;
import hhu.propra.bati5a.application.service.ProfileService;
import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.person.User;
import hhu.propra.bati5a.domain.service.MatchingService;
import hhu.propra.bati5a.infrastructure.config.AppUserService;
import hhu.propra.bati5a.infrastructure.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
@Import(SecurityConfig.class)
public class AdminControllerTest {

    @Autowired
    MockMvc mvc;
    
    @MockBean
    AppUserService appUserService;

    @MockBean
    ProfileService profileService;

    // Mocking other services to satisfy ApplicationContext if they are required by other components
    @MockBean
    BrowsingService browsingService;
    
    @MockBean
    MatchingService matchingService;

    @MockBean
    FileStorageService fileStorageService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminDashboard_AsAdmin_ReturnsViewAndModel() throws Exception {
        User user = new Student("TestUser", "CS", LocalDate.now(), "test@test.com");
        when(profileService.getAllPendingAdvisorRequests()).thenReturn(List.of(user));

        mvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeExists("pendingRequests"))
                .andExpect(model().attribute("pendingRequests", List.of(user)));
    }

    @Test
    @WithMockUser(roles = "USER")
    void adminDashboard_AsUser_IsForbidden() throws Exception {
        mvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminDashboard_AsAnonymous_IsRedirectedToLogin() throws Exception {
        mvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void approveRequest_AsAdmin_CallsServiceAndRedirects() throws Exception {
        String login = "testuser";

        mvc.perform(post("/admin/approve")
                        .with(csrf())
                        .param("login", login))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin"))
                .andExpect(flash().attributeExists("message"));

        verify(profileService).approveAdvisorRequest(login);
    }

    @Test
    @WithMockUser(roles = "USER")
    void approveRequest_AsUser_IsForbidden() throws Exception {
        mvc.perform(post("/admin/approve")
                        .with(csrf())
                        .param("login", "testuser"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void rejectRequest_AsAdmin_CallsServiceAndRedirects() throws Exception {
        String login = "testuser";

        mvc.perform(post("/admin/reject")
                        .with(csrf())
                        .param("login", login))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin"))
                .andExpect(flash().attributeExists("message"));

        verify(profileService).rejectAdvisorRequest(login);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createProfile_AsAdmin_CreatesStudentAndRedirects() throws Exception {
        mvc.perform(post("/admin/create-profile")
                        .with(csrf())
                        .param("type", "student")
                        .param("name", "New Student")
                        .param("email", "new@student.com")
                        .param("branch", "CS")
                        .param("birthday", "2000-01-01")
                        .param("githubLogin", "newstudent"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin"))
                .andExpect(flash().attributeExists("message"));

        verify(profileService).saveProfile(any(Student.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createProfile_AsAdmin_CreatesAdvisorAndRedirects() throws Exception {
        mvc.perform(post("/admin/create-profile")
                        .with(csrf())
                        .param("type", "advisor")
                        .param("name", "New Advisor")
                        .param("email", "new@advisor.com")
                        .param("branch", "Math")
                        .param("birthday", "1980-01-01")
                        .param("githubLogin", "newadvisor"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin"))
                .andExpect(flash().attributeExists("message"));

        verify(profileService).saveProfile(any(Advisor.class));
    }
}
