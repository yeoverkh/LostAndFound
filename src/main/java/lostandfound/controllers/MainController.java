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

@Controller
@RequestMapping("/main")
public class MainController {

    private final LostItemService lostItemService;
    private final PlaceService placeService;
    private final TypeService typeService;
    private final AssessedValueService assessedValueService;

    @Value("${upload.path}")
    private String uploadPath;


    public MainController(LostItemService lostItemService, PlaceService placeService, TypeService typeService, AssessedValueService assessedValueService) {

        this.lostItemService = lostItemService;
        this.placeService = placeService;
        this.typeService = typeService;
        this.assessedValueService = assessedValueService;
    }

    @GetMapping
    public String main(Model model) {

        model.addAttribute("items", lostItemService.findAll());

        return "item/main";
    }

    @GetMapping("/add")
    public String addItem(Model model) {

        model.addAttribute("places", placeService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("assessedValues", assessedValueService.findAll());

        return "item/addItem";
    }

    @PostMapping
    public String add(@Valid LostItem lostItem,
                      @RequestParam(required = false) MultipartFile file,
                      String stringTime,
                      Model model) throws IOException {

        addFile(lostItem, file);

        lostItemService.save(lostItem, stringTime);

        model.addAttribute("lostItem", lostItem);

        return "information/printAdd";
    }

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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        LostItem item = lostItemService.findById(id);
        model.addAttribute("lostItem", item);
        model.addAttribute("date", new Date(System.currentTimeMillis()));

        lostItemService.deleteById(id);

        return "information/print";
    }



    @GetMapping("/edit/{lostItem}")
    public String editItem(@PathVariable LostItem lostItem,
                           Model model) {
        model.addAttribute("item", lostItem);
        model.addAttribute("places", placeService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("assessedValues", assessedValueService.findAll());

        return "item/editItem";
    }
}
