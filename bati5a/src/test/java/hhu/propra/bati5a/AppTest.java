package hhu.propra.bati5a;

import hhu.propra.bati5a.application.service.BrowsingService;
import hhu.propra.bati5a.application.service.FileStorageService;
import hhu.propra.bati5a.application.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AppTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BrowsingService browsingService;

    @MockBean
    ProfileService profileService;

    @MockBean
    FileStorageService fileStorageService;

    @BeforeEach
    void setup() {
        when(browsingService.getAllTopics()).thenReturn(Collections.emptyList());
        when(browsingService.getAllProfiles()).thenReturn(Collections.emptyList());
        when(profileService.getProfileByEmail(anyString()))
                .thenReturn(empty());
        when(profileService.getProfileByGithubLogin(anyString()))
                .thenReturn(empty());
    }

    @Test
    @WithMockUser
    @DisplayName("Start seite ist erreichbar")
    void test1() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser
    @DisplayName("Topics seite ist erreichbar")
    void test2() throws Exception {
        mvc.perform(get("/topics"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser
    @DisplayName("login seite ist erreichbar")
    void test3() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("profile seite ist erreichbar")
    void test4() throws Exception {
        mvc.perform(get("/profile")
                .with(oauth2Login().attributes(attrs -> {
                    attrs.put("login", "testuser");
                    attrs.put("name", "Test User");
                    attrs.put("avatar_url", "http://avatar");
                })))
                .andExpect(status().is2xxSuccessful());
    }
}
