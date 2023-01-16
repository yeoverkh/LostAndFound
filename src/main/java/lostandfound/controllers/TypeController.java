package lostandfound.controllers;

import lostandfound.models.lostitem.Type;
import lostandfound.services.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for all mappings that works with types.
 */
@Controller
@RequestMapping("/types")
public class TypeController {

    /**
     * Service that works with types.
     */
    private final TypeService typeService;

    /**
     * Default constructor that auto wires dependency.
     */
    public TypeController(TypeService typeService) {

        this.typeService = typeService;
    }

    /**
     * Shows all types.
     * @return page with all types.
     */
    @GetMapping
    public String all(Model model) {

        addTypes(model);

        return "item/types";
    }

    /**
     * Adds new type.
     * @param type adding type.
     * @return types page with information about success adding new type.
     */
    @PostMapping
    public String add(@Valid Type type,
                      Model model) {

        typeService.save(type);

        model.addAttribute("message", "success");

        addTypes(model);

        return "item/types";
    }

    /**
     * Edits type.
     * @param id path variable id of editing type.
     * @param name input String new name for editing type.
     * @return types page with information about success editing type.
     */
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam(required = false) String name,
                       Model model) {

        Type type = typeService.findById(id);

        if (name != null && !name.equals(""))  type.setName(name);

        typeService.save(type);

        model.addAttribute("message", "info");

        addTypes(model);

        return "item/types";
    }

    /**
     * Deletes type.
     * @param id path variable of deleting type.
     * @return types page with information about success deleting type.
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        typeService.deleteById(id);

        model.addAttribute("message", "danger");

        addTypes(model);

        return "item/types";
    }

    /**
     * Adds types to view.
     */
    private void addTypes(Model model) {
        model.addAttribute("types", typeService.findAll());
    }
}
