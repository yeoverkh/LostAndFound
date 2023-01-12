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

@Controller
@RequestMapping("/information/{id}")
public class InformationController {

    private final LostItemService lostItemService;
    private final PeculiaritiesService peculiaritiesService;

    public InformationController(LostItemService lostItemService, PeculiaritiesService peculiaritiesService) {

        this.lostItemService = lostItemService;
        this.peculiaritiesService = peculiaritiesService;
    }

    @GetMapping
    public String show(@PathVariable Long id,
                       Model model) {

        LostItem item = lostItemService.findById(id);

        model.addAttribute("item", item);
        model.addAttribute("peculiarities", item.getPeculiarities());
        model.addAttribute("allPeculiarities", peculiaritiesService.findAll());

        return "item/information";
    }

    @PostMapping("/add/{item}")
    public String add(@PathVariable Long id,
                      @PathVariable Long item) {

        lostItemService.save(id, item);

        return "redirect:/information/" + id;
    }

    @PostMapping("/remove/{item}")
    public String remove(@PathVariable Long id,
                         @PathVariable Long item) {

        lostItemService.remove(id, item);

        return "redirect:/information/" + id;
    }

    @GetMapping("/items")
    public String showItems(@PathVariable Long id,
                            Model model) {

        Peculiarities peculiarities = peculiaritiesService.findById(id);

        model.addAttribute("peculiarities", peculiarities);
        model.addAttribute("items", peculiarities.getLostItems());
        model.addAttribute("allLostItems", lostItemService.findAll());

        return "peculiarities/informationPeculiarities";
    }

    @PostMapping("/items/add/{item}")
    public String addItem(@PathVariable Long id,
                          @PathVariable Long item) {

        lostItemService.save(item, id);

        return "redirect:/information/" + id + "/items";
    }

    @PostMapping("/items/remove/{item}")
    public String removeItem(@PathVariable Long id,
                             @PathVariable Long item) {

        lostItemService.remove(item, id);

        return "redirect:/information/" + id + "/items";
    }
}
