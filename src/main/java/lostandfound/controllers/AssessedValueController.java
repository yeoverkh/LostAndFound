package lostandfound.controllers;

import lostandfound.models.lostitem.AssessedValue;
import lostandfound.models.lostitem.Currency;
import lostandfound.services.AssessedValueService;
import lostandfound.services.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for assessed value mappings
 */
@Controller
@RequestMapping("/values")
public class AssessedValueController {

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
    public AssessedValueController(AssessedValueService assessedValueService, CurrencyService currencyService) {

        this.assessedValueService = assessedValueService;
        this.currencyService = currencyService;
    }

    /**
     * Mapping for show all assessed values and currencies.
     * @return page with all assessed values and currencies.
     */
    @GetMapping
    public String all(Model model) {

        addValuesAndCurrencies(model);

        return "item/assessedValues";
    }

    /**
     * Added new assessed value.
     * @param value input Double parameter of value
     * @param currency input index of currency
     * @return page with all assessed values and currencies with information about success adding new assessed value.
     */
    @PostMapping
    public String add(@RequestParam Double value,
                      @RequestParam Long currency,
                      Model model) {

        if (currency == -1) {
            model.addAttribute("isEmpty", true);
        } else {
            AssessedValue assessedValue = new AssessedValue(value, currencyService.findById(currency));
            assessedValueService.save(assessedValue);

            model.addAttribute("message", "success");
        }

        addValuesAndCurrencies(model);

        return "item/assessedValues";
    }

    /**
     * Edits assessed value.
     * @param id input Long id of assessed value that edited..
     * @param value input Double value of assessed value, nullable.
     * @param currency input currency of assessed value, nullable.
     * @return page with all assessed values and currencies with information about success editing assessed value.
     */
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

        addValuesAndCurrencies(model);

        return "item/assessedValues";
    }

    /**
     * Deletes assessed value.
     * @param id input Long id of assessed value that must be deleted.
     * @return page with all assessed values and currencies with information about success deleting assessed value.
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        assessedValueService.deleteById(id);

        model.addAttribute("message", "danger");

        addValuesAndCurrencies(model);

        return "item/assessedValues";
    }

    /**
     * Adds to page values and currencies.
     */
    private void addValuesAndCurrencies(Model model) {
        model.addAttribute("values", assessedValueService.findAll());
        model.addAttribute("currencies", currencyService.findAll());
    }
}

