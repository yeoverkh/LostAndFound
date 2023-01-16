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

/**
 * Controller for currencies mappings
 */
@Controller
@RequestMapping("/values/currencies")
public class CurrencyController {

    /**
     * Service that works with assessed values.
     */
    private final AssessedValueService assessedValueService;

    /**
     * Service that works with currencies.
     */
    private final CurrencyService currencyService;

    /**
     * Default constructor that auto wires dependencies.
     */
    public CurrencyController(AssessedValueService assessedValueService, CurrencyService currencyService) {

        this.assessedValueService = assessedValueService;
        this.currencyService = currencyService;
    }

    /**
     * Adds new currency.
     * @param currency currency that must be added to database.
     * @return page with all assessed values and currencies with information about success adding new currency.
     */
    @PostMapping
    public String addCurrency(@Valid Currency currency,
                              Model model) {

        currencyService.save(currency);

        model.addAttribute("messageCurrency", "success");

        addValuesAndCurrencies(model);

        return "item/assessedValues";
    }

    /**
     * Edits currency.
     * @param id input Long id of currency that must be edited.
     * @param name input String new name of currency, nullable.
     * @return page with all assessed values and currencies with information about success editing currency.
     */
    @PostMapping("/edit/{id}")
    public String editCurrency(@PathVariable Long id,
                               @RequestParam(required = false) String name,
                               Model model) {

        Currency currency = currencyService.findById(id);

        if (name != null && !name.equals("")) currency.setName(name);

        currencyService.save(currency);

        model.addAttribute("messageCurrency", "info");

        addValuesAndCurrencies(model);

        return "item/assessedValues";
    }

    /**
     * Deletes currency.
     * @param id input Long id of currency that must be deleted.
     * @return page with all assessed values and currencies with information about success deleting currency.
     */
    @PostMapping("/delete/{id}")
    public String deleteCurrency(@PathVariable Long id,
                                 Model model) {

        currencyService.deleteById(id);

        model.addAttribute("messageCurrency", "danger");

        addValuesAndCurrencies(model);

        return "item/assessedValues";
    }

    /**
     * Added to page values and currencies.
     */
    private void addValuesAndCurrencies(Model model) {
        model.addAttribute("values", assessedValueService.findAll());
        model.addAttribute("currencies", currencyService.findAll());
    }
}
