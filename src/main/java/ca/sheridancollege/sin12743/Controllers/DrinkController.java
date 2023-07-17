package ca.sheridancollege.sin12743.Controllers;
import ca.sheridancollege.sin12743.bean.Drink;
import ca.sheridancollege.sin12743.repositories.DrinkRepository;
import org.springframework.ui.Model;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//@AllArgsConstructor
@Controller

public class DrinkController {
    private final DrinkRepository drinkRepo;
    public DrinkController(DrinkRepository drinkRepo) {
        this.drinkRepo = drinkRepo;
    }
    @GetMapping("/")
    public  String goHome(Model model){
        model.addAttribute("drinks", drinkRepo.getDrinks());
        return "home";
    }


    @GetMapping("/add")
    public String addDrinks(Model model){
        model.addAttribute("drink", new Drink());
        return "AddDrink";
    }
    @PostMapping("/add")
    public  String addingDrink(@ModelAttribute Drink drink, Model model){
        drinkRepo.addDrink(drink);
        return "redirect:/add";
    }
    @GetMapping("/view")
    public String viewDrinks(Model model) {
        model.addAttribute("drinks", drinkRepo.getDrinks());
        return "view";
    }
    @GetMapping("/editlink/{id}")
    public String editLink(Model model, @PathVariable int id){
        Drink d = drinkRepo.getDrinkById(id);

        model.addAttribute("drink", d);
        return "editDrinks";
    }

    @GetMapping("/modify")
    public String modifyDrink(@RequestParam int id,
                              @RequestParam String name, Model model) {
        Drink d = new Drink(id, name);
        drinkRepo.updateDrink(d);
        model.addAttribute("myDrinks", drinkRepo.getDrinks());
        return "redirect:/viewDrinks";
    }
    @GetMapping("/deletelink/{id}")
    public String deletePage(@PathVariable int id, Model model) {
        Drink da = drinkRepo.getDrinkById(id);
        drinkRepo.deleteDrink(id);
        model.addAttribute("myDrinks", drinkRepo.getDrinks());
        return "viewDrinks";
    }
}
