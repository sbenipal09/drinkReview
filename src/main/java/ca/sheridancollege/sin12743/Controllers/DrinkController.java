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
    @GetMapping("/edit/{id}")
    public String editDrink(@PathVariable int id, Model model){
        Drink drink = drinkRepo.getDrinkById(id);
        model.addAttribute("drink", drink);
        return "editDrinks";
    }

    @PostMapping("/edit")
    public String processEdit(@ModelAttribute Drink drink){
        Drink d = drink;
        drinkRepo.updateDrink(d);
        return "redirect:/view";
    }
    @GetMapping("/delete/{id}")
    public String deleteDrinkConfirmation(@PathVariable int id, Model model) {
        Drink drink = drinkRepo.getDrinkById(id);
        model.addAttribute("drink", drink);
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteDrink(@PathVariable int id) {
        drinkRepo.deleteDrinkById(id);
        return "redirect:/view";
    }
}
