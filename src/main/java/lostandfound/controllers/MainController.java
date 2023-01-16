package lostandfound.controllers;

import lostandfound.models.lostitem.AssessedValue;
import lostandfound.models.lostitem.LostItem;
import lostandfound.models.lostitem.Place;
import lostandfound.models.lostitem.Type;
import lostandfound.services.AssessedValueService;
import lostandfound.services.LostItemService;
import lostandfound.services.PlaceService;
import lostandfound.services.TypeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

/**
 * Controller for all mappings that works with items.
 */
@Controller
@RequestMapping("/main")
public class MainController {

    /**
     * Service that works with lost items.
     */
    private final LostItemService lostItemService;

    /**
     * Service that works with places.
     */
    private final PlaceService placeService;

    /**
     * Service that works with types.
     */
    private final TypeService typeService;

    /**
     * Service that works with assessed values.
     */
    private final AssessedValueService assessedValueService;

    /**
     * Takes images from this upload path.
     */
    @Value("${upload.path}")
    private String uploadPath;

    /**
     * Default constructor that auto wires dependencies.
     */
    public MainController(LostItemService lostItemService, PlaceService placeService, TypeService typeService, AssessedValueService assessedValueService) {

        this.lostItemService = lostItemService;
        this.placeService = placeService;
        this.typeService = typeService;
        this.assessedValueService = assessedValueService;
    }

    /**
     * Shows all lost items.
     * @return main page with all lost items.
     */
    @GetMapping
    public String main(Model model) {

        model.addAttribute("items", lostItemService.findAll());

        return "item/main";
    }

    /**
     * Redirects on page to add new item.
     * @return page with input fields to add new item.
     */
    @GetMapping("/add")
    public String addItem(Model model) {

        model.addAttribute("places", placeService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("assessedValues", assessedValueService.findAll());

        return "item/addItem";
    }

    /**
     * Adds new lost item.
     * @param lostItem lost item that creates from input fields.
     * @param place input Long id of lost item place.
     * @param type input Long id of lost item type.
     * @param assessedValue input Long id of lost item assessed value.
     * @param file input image that belongs to lost item.
     * @param stringTime input String time when lost item was found.
     * @return page with information about success adding item to lost and found or page for adding new lost item
     * if user entered invalid data.
     */
    @PostMapping
    public String add(@Valid LostItem lostItem,
                      @RequestParam Long place,
                      @RequestParam Long type,
                      @RequestParam Long assessedValue,
                      @RequestParam(required = false) MultipartFile file,
                      String stringTime,
                      Model model) throws IOException {

        if (place == -1 || type == -1 || assessedValue == -1) {
            if (place == -1) model.addAttribute("message", "You must choose place");
            else if (type == -1) model.addAttribute("message", "You must choose type");
            else model.addAttribute("message", "You must choose assessed value");

            model.addAttribute("places", placeService.findAll());
            model.addAttribute("types", typeService.findAll());
            model.addAttribute("assessedValues", assessedValueService.findAll());

            return "item/addItem";
        }

        addFile(lostItem, file);

        lostItemService.save(lostItem, stringTime);

        model.addAttribute("lostItem", lostItem);

        return "information/printAdd";
    }

    /**
     * Opens page for editing item.
     * @param lostItem item that will be edited.
     * @return page of editing item.
     */
    @GetMapping("/edit/{lostItem}")
    public String editItem(@PathVariable LostItem lostItem,
                           Model model) {
        model.addAttribute("item", lostItem);
        model.addAttribute("places", placeService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("assessedValues", assessedValueService.findAll());

        return "item/editItem";
    }

    /**
     * Edits lost item.
     * @param item item that will be edited.
     * @param name new name for item.
     * @param quantity new quantity for item.
     * @param place new place for item.
     * @param type new type for item.
     * @param assessedValue new assessed value for item.
     * @param date new date for item.
     * @param stringTime new time represents in string for item.
     * @param file new image for item.
     * @return main page with information about success editing item.
     */
    @PostMapping("/edited/{item}")
    public String edit(@PathVariable LostItem item,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Integer quantity,
                       @RequestParam(required = false) Place place,
                       @RequestParam(required = false) Type type,
                       @RequestParam(required = false) AssessedValue assessedValue,
                       @RequestParam(required = false) String date,
                       @RequestParam(required = false) String stringTime,
                       @RequestParam(required = false) MultipartFile file,
                       Model model) throws IOException {

        if (name != null && !name.equals("")) item.setName(name);
        if (quantity != null) item.setQuantity(quantity);
        if (place != null) item.setPlace(place);
        if (type != null) item.setType(type);
        if (assessedValue != null) item.setAssessedValue(assessedValue);
        if (date != null && !date.equals("")) item.setDate(Date.valueOf(date));
        addFile(item, file);

        if (stringTime != null && !stringTime.equals("")) lostItemService.save(item, stringTime);
        else lostItemService.save(item);

        model.addAttribute("message", "info");
        model.addAttribute("items", lostItemService.findAll());

        return "item/main";
    }

    /**
     * Configures filename for files.
     * @param lostItem item for file.
     * @param file uploading file.
     */
    private void addFile(LostItem lostItem, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadPath = new File(this.uploadPath);

            if (!uploadPath.exists()) {
                uploadPath.mkdir();
            }

            String uuid = UUID.randomUUID().toString();
            String filename = uuid + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + filename));

            lostItem.setFilename(filename);
        }
    }

    /**
     * Deletes item.
     * @param id if of item that must be deleted.
     * @return page with information about success deleting item.
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        LostItem item = lostItemService.findById(id);
        model.addAttribute("lostItem", item);
        model.addAttribute("date", new Date(System.currentTimeMillis()));

        lostItemService.deleteById(id);

        return "information/print";
    }
}
