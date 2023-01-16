package lostandfound.controllers;

import lostandfound.models.peculiarities.Unit;
import lostandfound.services.PeculiaritiesService;
import lostandfound.services.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Controller that works with all units mappings.
 */
@Controller
@RequestMapping("/peculiarities/units")
public class UnitController {

    /**
     * Service that works with units.
     */
    private final UnitService unitService;

    /**
     * Service that works with peculiarities.
     */
    private final PeculiaritiesService peculiaritiesService;

    /**
     * Default constructor that auto wires dependencies.
     */
    public UnitController(UnitService unitService, PeculiaritiesService peculiaritiesService) {
        this.unitService = unitService;
        this.peculiaritiesService = peculiaritiesService;
    }

    /**
     * Adds new unit.
     * @param unit unit that must be added.
     * @return peculiarities page with information about success adding new unit.
     */
    @PostMapping
    public String add(@Valid Unit unit,
                          Model model) {

        unitService.save(unit);

        model.addAttribute("messageUnit", "success");

        addPeculiaritiesAndUnits(model);

        return "peculiarities/peculiarities";
    }

    /**
     * Edits unit.
     * @param unit input Unit that must be edited.
     * @param name input String of new name for edited unit.
     * @return peculiarities page with information about success editing unit.
     */
    @PostMapping("/edit/{unit}")
    public String edit(@PathVariable Unit unit,
                       @RequestParam(required = false) String name,
                       Model model) {

        if (name != null && !name.equals("")) unit.setName(name);

        model.addAttribute("messageUnit", "info");
        addPeculiaritiesAndUnits(model);

        unitService.save(unit);

        return "peculiarities/peculiarities";
    }

    /**
     * Deletes unit.
     * @param id path variable id of unit that must be deleted.
     * @return peculiarities page with information about success deleting unit.
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        unitService.deleteById(id);

        model.addAttribute("messageUnit", "danger");
        addPeculiaritiesAndUnits(model);

        return "peculiarities/peculiarities";
    }

    /**
     * Adds to view peculiarities and units.
     */
    private void addPeculiaritiesAndUnits(Model model) {
        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());
    }
}
