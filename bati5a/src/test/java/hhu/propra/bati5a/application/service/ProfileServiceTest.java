package hhu.propra.bati5a.application.service;

import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.person.User;
import hhu.propra.bati5a.domain.model.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    private ProfileRepository profileRepository;
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        profileRepository = mock(ProfileRepository.class);
        profileService = new ProfileService(profileRepository);
    }

    @Test
    @DisplayName("approveAdvisorRequest should convert Student to Advisor")
    void testApproveAdvisorRequest() {
        Student student = new Student("Test Student", "CS", LocalDate.of(2000, 1, 1), "student@test.com");
        student.setGithubLogin("testuser");
        student.setAvatarUrl("http://avatar.com");
        student.setAdvisorRequestPending(true);

        when(profileRepository.findByGithubLogin("testuser")).thenReturn(Optional.of(student));

        profileService.approveAdvisorRequest("testuser");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(profileRepository).save(captor.capture());

        User approvedUser = captor.getValue();
        assertTrue(approvedUser instanceof Advisor);
        assertEquals("testuser", approvedUser.getGithubLogin());
        assertEquals("Test Student", approvedUser.getName());
        assertEquals("student@test.com", approvedUser.getMail());
        assertFalse(approvedUser.isAdvisorRequestPending());
    }

    @Test
    @DisplayName("rejectAdvisorRequest should clear pending flag")
    void testRejectAdvisorRequest() {
        Student student = new Student("Test Student", "CS", LocalDate.of(2000, 1, 1), "student@test.com");
        student.setGithubLogin("testuser");
        student.setAdvisorRequestPending(true);

        when(profileRepository.findByGithubLogin("testuser")).thenReturn(Optional.of(student));

        profileService.rejectAdvisorRequest("testuser");

        verify(profileRepository).save(student);
        assertFalse(student.isAdvisorRequestPending());
    }

    @Test
    @DisplayName("addAdvisorTag should add a tag to an Advisor")
    void testAddAdvisorTag() {
        Advisor advisor = new Advisor("Dr. Test", "CS", LocalDate.of(1980, 1, 1), "advisor@test.com");
        advisor.setGithubLogin("advuser");

        when(profileRepository.findByGithubLogin("advuser")).thenReturn(Optional.of(advisor));

        profileService.addAdvisorTag("advuser", "NewTag");

        verify(profileRepository).save(advisor);
        assertTrue(advisor.getTags().stream().anyMatch(t -> t.getName().equals("NewTag")));
    }

    @Test
    @DisplayName("updateAdvisorLinks should update links correctly")
    void testUpdateAdvisorLinks() {
        Advisor advisor = new Advisor("Dr. Test", "CS", LocalDate.of(1980, 1, 1), "advisor@test.com");
        advisor.setGithubLogin("advuser");

        when(profileRepository.findByGithubLogin("advuser")).thenReturn(Optional.of(advisor));

        String newLinks = "http://link1.com, http://link2.com";
        profileService.updateAdvisorLinks("advuser", newLinks);

        verify(profileRepository).save(advisor);
        assertEquals(newLinks, advisor.getLinks());
    }
}
