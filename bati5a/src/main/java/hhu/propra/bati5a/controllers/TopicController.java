package hhu.propra.bati5a.controllers;

import hhu.propra.bati5a.application.service.BrowsingService;
import hhu.propra.bati5a.domain.model.topic.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class TopicController {

    private final BrowsingService browsingService;

    public TopicController(BrowsingService browsingService) {
        this.browsingService = browsingService;
    }

    @GetMapping("/topic/{title}")
    public String showTopic(@PathVariable String title, Model model) {
        Optional<Topic> topicOpt = browsingService.getAllTopics().stream()
                .filter(t -> t.getTitle().equals(title))
                .findFirst();

        if (topicOpt.isPresent()) {
            model.addAttribute("topic", topicOpt.get());
            return "topic-details";
        }

        return "redirect:/topics";
    }
}
