package hhu.propra.bati5a.domain.model.person;

import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.topic.Topic;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Advisor extends User {
    private List<String> files = new ArrayList<>();
    private Set<Topic> topics;
    private String links;
    private Set<Tag> tags;

    public Advisor(String name, String branch, LocalDate birthday, String mail) {
        super(name, branch, birthday, mail);
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    @Override
    public String getType() {
        return "Advisor";
    }
}
