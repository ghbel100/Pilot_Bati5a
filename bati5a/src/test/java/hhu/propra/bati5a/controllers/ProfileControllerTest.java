package hhu.propra.bati5a.controllers;

import hhu.propra.bati5a.application.service.FileStorageService;
import hhu.propra.bati5a.application.service.ProfileService;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.infrastructure.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfileController.class)
@Import(SecurityConfig.class)
public class ProfileControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProfileService profileService;

    @MockBean
    FileStorageService fileStorageService;

    @MockBean
    hhu.propra.bati5a.application.service.BrowsingService browsingService;
    @MockBean
    hhu.propra.bati5a.domain.service.MatchingService matchingService;
    @MockBean
    hhu.propra.bati5a.infrastructure.config.AppUserService appUserService;

    @Test
    void addInterest_WithCorrectParam_AddsTag() throws Exception {
        String login = "testuser";
        Student student = new Student("Test", "CS", LocalDate.now(), "test@test.com");
        student.setGithubLogin(login);
        
        when(profileService.getProfileByGithubLogin(login)).thenReturn(Optional.of(student));

        mvc.perform(post("/profile/interests/add")
                        .with(csrf())
                        .with(oauth2Login().attributes(attrs -> {
                            attrs.put("login", login);
                            attrs.put("name", "Test User");
                            attrs.put("email", "test@test.com");
                        }))
                        .param("name", "Java")) // This confirms that passing "name" works
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"))
                .andExpect(flash().attributeExists("message"))
                .andExpect(flash().attribute("message", "Interesse 'Java' hinzugefügt!"));

        verify(profileService).saveProfile(student);
    }
}
