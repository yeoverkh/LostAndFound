package lostandfound.controllers;

import lostandfound.models.lostitem.LostItem;
import lostandfound.models.peculiarities.Peculiarities;
import lostandfound.services.LostItemService;
import lostandfound.services.PeculiaritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for all mappings with information about items or peculiarities.
 * @pathVariable id Long id of item.
 */
@Controller
@RequestMapping("/information/{id}")
public class InformationController {

    /**
     * Service that works with lost items.
     */
    private final LostItemService lostItemService;

    /**
     * Service that works with peculiarities.
     */
    private final PeculiaritiesService peculiaritiesService;

    /**
     * Default constructor that auto wires dependencies.
     */
    public InformationController(LostItemService lostItemService, PeculiaritiesService peculiaritiesService) {

        this.lostItemService = lostItemService;
        this.peculiaritiesService = peculiaritiesService;
    }

    /**
     * Shows all lost item peculiarities.
     * @param id path variable id of required item.
     * @return page with information of item.
     */
    @GetMapping
    public String show(@PathVariable Long id,
                       Model model) {

        LostItem item = lostItemService.findById(id);

        model.addAttribute("item", item);
        model.addAttribute("peculiarities", item.getPeculiarities());
        model.addAttribute("allPeculiarities", peculiaritiesService.findAll());

        return "item/information";
    }

    /**
     * Adds peculiarities to item.
     * @param id path variable id of some peculiarities.
     * @param item path variable id of some item.
     * @return page with information of item.
     */
    @PostMapping("/add/{item}")
    public String add(@PathVariable Long id,
                      @PathVariable Long item) {

        lostItemService.save(id, item);

        return "redirect:/information/" + id;
    }

    /**
     * Removes peculiarities to item.
     * @param id path variable id of some peculiarities.
     * @param item path variable id of some item.
     * @return page with information of item.
     */
    @PostMapping("/remove/{item}")
    public String remove(@PathVariable Long id,
                         @PathVariable Long item) {

        lostItemService.remove(id, item);

        return "redirect:/information/" + id;
    }

    /**
     * Shows items for peculiarities with present id.
     * @param id path variable id of some peculiarities.
     * @return page with all items with some peculiarities.
     */
    @GetMapping("/items")
    public String showItems(@PathVariable Long id,
                            Model model) {

        Peculiarities peculiarities = peculiaritiesService.findById(id);

        model.addAttribute("peculiarities", peculiarities);
        model.addAttribute("items", peculiarities.getLostItems());
        model.addAttribute("allLostItems", lostItemService.findAll());

        return "peculiarities/informationPeculiarities";
    }


    /**
     * Adds peculiarities to item.
     * @param id path variable id of some peculiarities.
     * @param item path variable id of some item.
     * @return page with all items with some peculiarities.
     */
    @PostMapping("/items/add/{item}")
    public String addItem(@PathVariable Long id,
                          @PathVariable Long item) {

        lostItemService.save(item, id);

        return "redirect:/information/" + id + "/items";
    }

    /**
     * Adds removes peculiarities from item.
     * @param id path variable id of some peculiarities.
     * @param item path variable id of some item.
     * @return page with all items with some peculiarities.
     */
    @PostMapping("/items/remove/{item}")
    public String removeItem(@PathVariable Long id,
                             @PathVariable Long item) {

        lostItemService.remove(item, id);

        return "redirect:/information/" + id + "/items";
    }
}
