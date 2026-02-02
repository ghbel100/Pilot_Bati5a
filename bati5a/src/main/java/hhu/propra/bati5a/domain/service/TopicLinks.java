package hhu.propra.bati5a.domain.service;

import hhu.propra.bati5a.domain.model.topic.Topic;

import java.util.List;

public class TopicLinks {
    private Topic topic;
    private List<Link> links;

    public TopicLinks(Topic topic, List<Link> links) {
        this.topic = topic;
        this.links = links;
    }

    public TopicLinks() {
    }

    public List<Link> all() {
        return List.copyOf(links);
    }

    public List<Link> getLinksForTopic(Topic topic) {
        if (this.topic.equals(topic)) {
            return all();
        }
        return List.of();
    }

    public Topic getTopic() {
        return topic;
    }
}
