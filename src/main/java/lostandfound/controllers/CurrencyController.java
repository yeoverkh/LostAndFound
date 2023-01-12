package lostandfound.controllers;

import lostandfound.models.lostitem.Currency;
import lostandfound.services.AssessedValueService;
import lostandfound.services.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/values")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final AssessedValueService assessedValueService;

    public CurrencyController(CurrencyService currencyService, AssessedValueService assessedValueService) {
        this.currencyService = currencyService;
        this.assessedValueService = assessedValueService;
    }

    @PostMapping("/currencies")
    public String addCurrency(@Valid Currency currency,
                              Model model) {

        currencyService.save(currency);

        model.addAttribute("messageCurrency", "success");

        model.addAttribute("values", assessedValueService.findAll());
        model.addAttribute("currencies", currencyService.findAll());

        return "item/assessedValues";
    }

    @PostMapping("/currencies/edit/{id}")
    public String editCurrency(@PathVariable Long id,
                               @RequestParam(required = false) String name,
                               Model model) {

        Currency currency = currencyService.findById(id);

        if (name != null && !name.equals("")) currency.setName(name);

        currencyService.save(currency);

        model.addAttribute("messageCurrency", "info");

        model.addAttribute("values", assessedValueService.findAll());
        model.addAttribute("currencies", currencyService.findAll());

        return "item/assessedValues";
    }

    @PostMapping("/currencies/delete/{id}")
    public String deleteCurrency(@PathVariable Long id,
                                 Model model) {

        currencyService.deleteById(id);

        model.addAttribute("messageCurrency", "danger");

        model.addAttribute("values", assessedValueService.findAll());
        model.addAttribute("currencies", currencyService.findAll());

        return "item/assessedValues";
    }
}
