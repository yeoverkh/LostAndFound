package lostandfound.controllers;

import lostandfound.models.lostitem.Type;
import lostandfound.services.LostItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for all filters or sorts:
 * @Search by name.
 * @Filter by type.
 * @Sort ascending or descending by assessed value, date, quantity.
 */
@Controller
@RequestMapping("/main")
public class FiltrationController {

    /**
     * Service that works with lost items.
     */
    private final LostItemService lostItemService;

    /**
     * Default constructor that auto wires dependency.
     */
    public FiltrationController(LostItemService lostItemService) {
        this.lostItemService = lostItemService;
    }

    /**
     * Natural ordering sort for items by date.
     * @return main page with sorted items using natural ordering.
     */
    @GetMapping("/sort")
    public String sort(Model model) {

        model.addAttribute("items", lostItemService.sortByDate());

        return "item/main";
    }

    /**
     * Non-natural ordering sort for items by value
     * @param value may be assessed value, date or quantity.
     * @param sortType ascending or descending.
     * @return main page with sorted items according to parameters.
     */
    @PostMapping("/sort")
    public String sort(@RequestParam String value,
                       @RequestParam String sortType,
                       Model model) {

        model.addAttribute("items", lostItemService.sort(value, sortType));

        return "item/main";
    }

    /**
     * Filters items on main page by type.
     * @param type type of items that will be on main page after filtering.
     * @return main page with items with only entered type.
     */
    @GetMapping("/type/{type}")
    public String filterType(@PathVariable Type type,
                             Model model) {
        model.addAttribute("items", type.getLostItems());

        return "item/main";
    }

    /**
     * Searches for items by search String.
     * @param search input String that must be in filtered items.
     * @return main page with all items name of which contains search String.
     */
    @PostMapping("/search")
    public String search(@RequestParam String search,
                         Model model) {

        model.addAttribute("items", lostItemService.search(search));

        return "item/main";
    }
}
