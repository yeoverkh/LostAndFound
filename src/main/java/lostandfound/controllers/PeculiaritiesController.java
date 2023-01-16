package lostandfound.controllers;

import lostandfound.models.peculiarities.Peculiarities;
import lostandfound.services.PeculiaritiesService;
import lostandfound.services.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for all mappings that works with peculiarities.
 */
@Controller
@RequestMapping("/peculiarities")
public class PeculiaritiesController {

    /**
     * Service that works with peculiarities.
     */
    private final PeculiaritiesService peculiaritiesService;

    /**
     * Service that works with units.
     */
    private final UnitService unitService;

    /**
     * Default constructor that auto wires dependencies.
     */
    public PeculiaritiesController(PeculiaritiesService peculiaritiesService, UnitService unitService) {

        this.peculiaritiesService = peculiaritiesService;
        this.unitService = unitService;
    }

    /**
     * Shows all peculiarities.
     * @return page with all peculiarities and units.
     */
    @GetMapping
    public String all(Model model) {

        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());

        return "peculiarities/peculiarities";
    }

    /**
     * Shows page for adding new peculiarities.
     * @return page for adding new peculiarities.
     */
    @GetMapping("/add")
    public String addPeculiarities(Model model) {

        model.addAttribute("units", unitService.findAll());

        return "peculiarities/addPeculiarities";
    }

    /**
     * Adds new peculiarities.
     * @param name input String of name for new peculiarities.
     * @param value input String of value for new peculiarities.
     * @param unit input Unit for new peculiarities.
     * @return peculiarities page with information about success adding new peculiarities.
     */
    @PostMapping
    public String add(@RequestParam String name,
                      @RequestParam String value,
                      @RequestParam Long unit,
                      Model model) {

        model.addAttribute("units", unitService.findAll());

        if (unit == -1) { // if user did not choose any unit, new peculiarities will not create.
            model.addAttribute("isEmpty", true);
            model.addAttribute("name", name);
            model.addAttribute("value", value);

            return "peculiarities/addPeculiarities";
        }
        peculiaritiesService.save(new Peculiarities(name, value, unitService.findById(unit)));

        model.addAttribute("message", "success");
        model.addAttribute("peculiarities", peculiaritiesService.findAll());

        return "peculiarities/peculiarities";
    }

    /**
     * Edits peculiarities.
     * @param id input Long id of editing peculiarities.
     * @param name input String new name for peculiarities, nullable.
     * @param unit input Long id of new unit for peculiarities may be -1(undefined).
     * @param value input String new value for peculiarities, nullable.
     * @return peculiarities page with information about success editing peculiarities.
     */
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Long unit,
                       @RequestParam(required = false) String value,
                       Model model) {

        Peculiarities peculiarities = peculiaritiesService.findById(id);
        if (name != null && !name.equals("")) peculiarities.setName(name);
        if (unit != null && unit != -1) peculiarities.setUnit(unitService.findById(unit));
        if (value != null && !value.equals("")) peculiarities.setValue(value);

        peculiaritiesService.save(peculiarities);

        model.addAttribute("message", "info");
        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());

        return "peculiarities/peculiarities";
    }

    /**
     * Deletes peculiarities.
     * @param id path variable Long id of deleting peculiarities.
     * @return peculiarities page with information about success deleting peculiarities.
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        peculiaritiesService.deleteById(id);

        model.addAttribute("message", "danger");
        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());

        return "peculiarities/peculiarities";
    }
}
