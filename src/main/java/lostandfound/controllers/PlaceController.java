package lostandfound.controllers;

import lostandfound.models.lostitem.Place;
import lostandfound.services.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for all mappings that works with places.
 */
@Controller
@RequestMapping("/places")
public class PlaceController {

    /**
     * Service that works with places.
     */
    private final PlaceService placeService;

    /**
     * Default constructor that auto wires dependency.
     */
    public PlaceController(PlaceService placeService) {

        this.placeService = placeService;
    }

    /**
     * Shows all places.
     * @return page with information of all places.
     */
    @GetMapping
    public String places(Model model) {

        model.addAttribute("places", placeService.findAll());

        return "item/places";
    }

    /**
     * Shows page for adding new place.
     * @return page for adding new place.
     */
    @GetMapping("/add")
    public String addPlace() {

        return "item/addPlace";
    }

    /**
     * Adds new place.
     * @param place place that must be added to lost and found.
     * @return places page with information about success adding new place.
     */
    @PostMapping
    public String add(@Valid Place place,
                      Model model) {

        placeService.save(place);

        model.addAttribute("message", "success");

        model.addAttribute("places", placeService.findAll());

        return "item/places";
    }

    /**
     * Edits place.
     * @param id input Long id of editing place.
     * @param address input String new address for editing place.
     * @param latitude input Double latitude for editing place.
     * @param longitude input Double longitude for editing place.
     * @param shortDescription input String short description for editing place.
     * @return places page with information about success editing place.
     */
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam(required = false) String address,
                       @RequestParam(required = false) Double latitude,
                       @RequestParam(required = false) Double longitude,
                       @RequestParam(required = false) String shortDescription,
                       Model model) {

        Place place = placeService.findById(id);
        if (address != null && !address.equals("")) place.setAddress(address);
        if (latitude != null) place.setLatitude(latitude);
        if (longitude != null) place.setLongitude(longitude);
        if (shortDescription != null && !shortDescription.equals("")) place.setShortDescription(shortDescription);

        placeService.save(place);

        model.addAttribute("message", "info");
        model.addAttribute("places", placeService.findAll());

        return "item/places";
    }

    /**
     * Deletes place.
     * @param id path variable id of deleting place.
     * @return places page with information about success deleting place.
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        placeService.deleteById(id);

        model.addAttribute("message", "danger");
        model.addAttribute("places", placeService.findAll());

        return "item/places";
    }
}
