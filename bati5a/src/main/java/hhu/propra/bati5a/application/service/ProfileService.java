package hhu.propra.bati5a.application.service;

import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.person.Advisor;
import hhu.propra.bati5a.domain.model.person.Student;
import hhu.propra.bati5a.domain.model.person.User;
import hhu.propra.bati5a.domain.model.repository.ProfileRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Optional<User> getProfileByEmail(String email) {
        return profileRepository.findByMail(email);
    }

    public Optional<User> getProfileByGithubLogin(String login) {
        return profileRepository.findByGithubLogin(login);
    }

    public void saveProfile(User user) {
        profileRepository.save(user);
    }

    public List<User> getAllPendingAdvisorRequests() {
        return profileRepository.findAllPendingAdvisorRequests();
    }

    public void approveAdvisorRequest(String login) {
        Optional<User> userOpt = profileRepository.findByGithubLogin(login);
        if (userOpt.isPresent() && userOpt.get() instanceof Student s) {
            Advisor approved = new Advisor(
                    s.getName(), s.getBranch(), s.getBirthday(), s.getMail());
            approved.setGithubLogin(s.getGithubLogin());
            approved.setAvatarUrl(s.getAvatarUrl());
            approved.setAdvisorRequestPending(false);
            profileRepository.save(approved);
        }
    }

    public void rejectAdvisorRequest(String login) {
        Optional<User> userOpt = profileRepository.findByGithubLogin(login);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setAdvisorRequestPending(false);
            profileRepository.save(user);
        }
    }

    public void addAdvisorTag(String login, String tagName) {
        Optional<User> userOpt = profileRepository.findByGithubLogin(login);
        if (userOpt.isPresent() && userOpt.get() instanceof Advisor advisor) {
            Set<Tag> tags = advisor.getTags();
            if (tags == null) {
                tags = new HashSet<>();
            }
            tags.add(new Tag(tagName));
            advisor.setTags(tags);
            profileRepository.save(advisor);
        }
    }

    public void updateAdvisorLinks(String login, String links) {
        Optional<User> userOpt = profileRepository.findByGithubLogin(login);
        if (userOpt.isPresent() && userOpt.get() instanceof Advisor advisor) {
            advisor.setLinks(links);
            profileRepository.save(advisor);
        }
    }

    public void addAdvisorFile(String login, String fileName) {
        Optional<User> userOpt = profileRepository.findByGithubLogin(login);
        if (userOpt.isPresent() && userOpt.get() instanceof Advisor advisor) {
            List<String> files = advisor.getFiles();
            files.add(fileName);
            advisor.setFiles(files);
            profileRepository.save(advisor);
        }
    }
}
