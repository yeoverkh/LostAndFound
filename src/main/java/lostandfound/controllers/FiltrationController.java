package lostandfound.controllers;

import lostandfound.models.lostitem.Type;
import lostandfound.services.LostItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class FiltrationController {

    private final LostItemService lostItemService;

    public FiltrationController(LostItemService lostItemService) {
        this.lostItemService = lostItemService;
    }

    @GetMapping("/sort")
    public String sort(Model model) {

        model.addAttribute("items", lostItemService.sortByDate());

        return "item/main";
    }

    @PostMapping("/sort")
    public String sortAcs(@RequestParam String value,
                          @RequestParam String sortType,
                          Model model) {

        model.addAttribute("items", lostItemService.sort(value, sortType));

        return "item/main";
    }

    @GetMapping("/type/{type}")
    public String filterType(@PathVariable Type type,
                             Model model) {
        model.addAttribute("items", type.getLostItems());

        return "item/main";
    }

    @PostMapping("/search")
    public String search(@RequestParam String search,
                         Model model) {

        model.addAttribute("items", lostItemService.search(search));

        return "item/main";
    }
}
