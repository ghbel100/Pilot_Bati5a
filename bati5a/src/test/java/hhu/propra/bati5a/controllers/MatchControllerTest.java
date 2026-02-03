package hhu.propra.bati5a.controllers;

import hhu.propra.bati5a.application.service.BrowsingService;
import hhu.propra.bati5a.application.service.FileStorageService;
import hhu.propra.bati5a.application.service.ProfileService;
import hhu.propra.bati5a.domain.service.MatchingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(MatchController.class)
public class MatchControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MatchingService matchingService;

    @MockBean
    BrowsingService browsingService;

    @MockBean
    ProfileService profileService;

    @Test
    @WithMockUser
    void showMatchForm_ReturnsMatchView() throws Exception {
        mvc.perform(get("/match"))
                .andExpect(status().isOk())
                .andExpect(view().name("match"));
    }

    @Test
    @WithMockUser
    void processMatch_ReturnsMatchViewWithResults() throws Exception {
        when(browsingService.getAllTopics()).thenReturn(Collections.emptyList());
        when(browsingService.getAllAdvisors()).thenReturn(Collections.emptyList());
        when(matchingService.rateTopics(any(), any())).thenReturn(Collections.emptyList());
        when(matchingService.rateAdvisors(any(), any())).thenReturn(Collections.emptyList());

        mvc.perform(post("/match")
                        .with(csrf())
                        .param("courses", "Java")
                        .param("interests", "Spring"))
                .andExpect(status().isOk())
                .andExpect(view().name("match"))
                .andExpect(model().attributeExists("topicRatings"))
                .andExpect(model().attributeExists("advisorRatings"))
                .andExpect(model().attribute("showResults", true));
    }
}
