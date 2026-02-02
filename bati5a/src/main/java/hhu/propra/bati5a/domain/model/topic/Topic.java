package hhu.propra.bati5a.domain.model.topic;

import hhu.propra.bati5a.domain.model.Course;
import hhu.propra.bati5a.domain.model.FileMetadata;
import hhu.propra.bati5a.domain.model.Tag;

import java.util.Set;

public class Topic {
    private String title;

    private Markdown description;

    private Set<Course> requiredCourses;

    private Set<Tag> tags;

    private FileMetadata files;

    private String link;

    public Topic(String title, String link, Markdown description, Set<Course> requiredCourses, Set<Tag> tags) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.requiredCourses = requiredCourses;
        this.tags = tags;
    }

    public Topic() {
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Markdown getDescription() {
        return description;
    }

    public Set<Course> getRequiredCourses() {
        return requiredCourses;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public FileMetadata getFiles() {
        return files;
    }
}
