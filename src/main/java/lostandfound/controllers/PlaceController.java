package lostandfound.controllers;

import lostandfound.models.lostitem.Place;
import lostandfound.services.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {

        this.placeService = placeService;
    }

    @GetMapping
    public String places(Model model) {

        model.addAttribute("places", placeService.findAll());

        return "item/places";
    }

    @GetMapping("/add")
    public String addPlace() {

        return "item/addPlace";
    }

    @PostMapping
    public String add(@Valid Place place,
                      Model model) {

        placeService.save(place);

        model.addAttribute("message", "success");

        model.addAttribute("places", placeService.findAll());

        return "item/places";
    }

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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        placeService.deleteById(id);

        model.addAttribute("message", "danger");
        model.addAttribute("places", placeService.findAll());

        return "item/places";
    }
}
