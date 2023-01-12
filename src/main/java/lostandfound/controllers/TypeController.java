package lostandfound.controllers;

import lostandfound.models.lostitem.Type;
import lostandfound.services.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/types")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {

        this.typeService = typeService;
    }

    @GetMapping
    public String all(Model model) {

        model.addAttribute("types", typeService.findAll());

        return "item/types";
    }

    @PostMapping
    public String add(@Valid Type type,
                      Model model) {

        typeService.save(type);

        model.addAttribute("message", "success");

        model.addAttribute("types", typeService.findAll());

        return "item/types";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam(required = false) String name,
                       Model model) {

        Type type = typeService.findById(id);

        if (name != null && !name.equals(""))  type.setName(name);

        typeService.save(type);

        model.addAttribute("message", "info");

        model.addAttribute("types", typeService.findAll());

        return "item/types";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        typeService.deleteById(id);

        model.addAttribute("message", "danger");

        model.addAttribute("types", typeService.findAll());

        return "item/types";
    }
}
