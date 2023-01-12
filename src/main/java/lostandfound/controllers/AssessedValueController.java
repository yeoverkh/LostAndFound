package lostandfound.controllers;

import lostandfound.models.lostitem.AssessedValue;
import lostandfound.models.lostitem.Currency;
import lostandfound.services.AssessedValueService;
import lostandfound.services.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/values")
public class AssessedValueController {

    private final AssessedValueService assessedValueService;
    private final CurrencyService currencyService;

    public AssessedValueController(AssessedValueService assessedValueService, CurrencyService currencyService) {

        this.assessedValueService = assessedValueService;
        this.currencyService = currencyService;
    }

    @GetMapping
    public String all(Model model) {

        model.addAttribute("values", assessedValueService.findAll());
        model.addAttribute("currencies", currencyService.findAll());

        return "item/assessedValues";
    }

    @PostMapping
    public String add(@Valid AssessedValue assessedValue,
                      Model model) {

        assessedValueService.save(assessedValue);

        model.addAttribute("message", "success");

        model.addAttribute("values", assessedValueService.findAll());
        model.addAttribute("currencies", currencyService.findAll());

        return "item/assessedValues";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam(required = false) Double value,
                       @RequestParam(required = false) Currency currency,
                       Model model) {

        AssessedValue assessedValue = assessedValueService.findById(id);

        if (value != null) assessedValue.setValue(value);
        if (currency != null) assessedValue.setCurrency(currency);

        assessedValueService.save(assessedValue);

        model.addAttribute("message", "info");

        model.addAttribute("values", assessedValueService.findAll());
        model.addAttribute("currencies", currencyService.findAll());

        return "item/assessedValues";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        assessedValueService.deleteById(id);

        model.addAttribute("message", "danger");

        model.addAttribute("values", assessedValueService.findAll());
        model.addAttribute("currencies", currencyService.findAll());

        return "item/assessedValues";
    }
}

