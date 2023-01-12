package lostandfound.controllers;

import lostandfound.models.peculiarities.Peculiarities;
import lostandfound.models.peculiarities.Unit;
import lostandfound.services.PeculiaritiesService;
import lostandfound.services.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/peculiarities")
public class PeculiaritiesController {

    private final PeculiaritiesService peculiaritiesService;
    private final UnitService unitService;

    public PeculiaritiesController(PeculiaritiesService peculiaritiesService, UnitService unitService) {

        this.peculiaritiesService = peculiaritiesService;
        this.unitService = unitService;
    }

    @GetMapping
    public String all(Model model) {

        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());

        return "peculiarities/peculiarities";
    }

    @GetMapping("/add")
    public String addPeculiarities(Model model) {

        model.addAttribute("units", unitService.findAll());
        return "peculiarities/addPeculiarities";
    }

    @PostMapping
    public String add(@RequestParam String name,
                      @RequestParam String value,
                      @RequestParam Unit unit,
                      Model model) {

        peculiaritiesService.save(new Peculiarities(name, value, unit));

        model.addAttribute("message", "success");

        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());

        return "peculiarities/peculiarities";
    }

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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        peculiaritiesService.deleteById(id);

        model.addAttribute("message", "danger");
        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());

        return "peculiarities/peculiarities";
    }


    @PostMapping("/units")
    public String addUnit(@Valid Unit unit,
                          Model model) {

        unitService.save(unit);

        model.addAttribute("messageUnit", "success");

        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());

        return "peculiarities/peculiarities";
    }

    @PostMapping("/units/edit/{id}")
    public String editUnit(@PathVariable Long id,
                           @RequestParam String name,
                           Model model) {

        Unit unit = unitService.findById(id);
        if (name != null && !name.equals("")) unit.setName(name);

        model.addAttribute("messageUnit", "info");
        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());

        return "peculiarities/peculiarities";
    }

    @PostMapping("/units/delete/{id}")
    public String deleteUnit(@PathVariable Long id,
                             Model model) {

        unitService.deleteById(id);

        model.addAttribute("messageUnit", "danger");
        model.addAttribute("peculiarities", peculiaritiesService.findAll());
        model.addAttribute("units", unitService.findAll());

        return "peculiarities/peculiarities";
    }
}
