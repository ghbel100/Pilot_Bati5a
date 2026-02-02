package hhu.propra.bati5a.controllers;

import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.application.service.BrowsingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Set;

@Controller
public class WebController {

    private final BrowsingService browsingService;

    public WebController(BrowsingService browsingService) {
        this.browsingService = browsingService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/topics")
    public String topics(@RequestParam(required = false) String tag, Model model) {
        if (tag != null && !tag.isEmpty()) {
            Set<Tag> tags = Collections.singleton(new Tag(tag));
            model.addAttribute("topics", browsingService.filterTopicsByTags(tags));
            model.addAttribute("profiles", browsingService.filterAllProfilesByTags(tags));
        } else {
            model.addAttribute("topics", browsingService.getAllTopics());
            model.addAttribute("profiles", browsingService.getAllProfiles());
        }
        return "topics";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
