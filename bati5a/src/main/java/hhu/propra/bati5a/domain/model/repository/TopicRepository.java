package hhu.propra.bati5a.domain.model.repository;

import hhu.propra.bati5a.domain.model.topic.Topic;
import java.util.List;

public interface TopicRepository {
    List<Topic> findAll();
}
