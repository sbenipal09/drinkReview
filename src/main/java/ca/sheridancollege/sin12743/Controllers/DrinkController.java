package ca.sheridancollege.sin12743.Controllers;
import ca.sheridancollege.sin12743.bean.Drink;
import ca.sheridancollege.sin12743.repositories.DrinkRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//@AllArgsConstructor
@Controller

public class DrinkController {
    private final DrinkRepository drinkRepo;
    public DrinkController(DrinkRepository drinkRepo) {
        this.drinkRepo = drinkRepo;
    }



    @GetMapping(value = {"/"})
    public String goHome(){

        return  "home";
    }
    @GetMapping("/view")
    public String viewDrinks(Model model) {
        model.addAttribute("drinks", drinkRepo.getDrinks());
        return "view";
    }




    @GetMapping("/AddDrink")
    public String addDrinks(Model model){
        model.addAttribute("drink", new Drink());
        return "AddDrink";
    }
    @PostMapping("/AddDrink")
    public  String addingDrink(@ModelAttribute Drink drink, Model model){
        drinkRepo.addDrink(drink);
        return "redirect:/AddDrink";
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
        return "deleteDrink";
    }

    @PostMapping("/delete/{id}")
    public String deleteDrink(@PathVariable int id) {
        drinkRepo.deleteDrinkById(id);
        return "redirect:/view";
    }

    @GetMapping("/Multiple")
    public String goMultiple(){
        return "Multiple";
    }
    @GetMapping("/accessdenied")
    public String accessDenied(){
        return "accessdenied";
    }
    @GetMapping("/user")
    public String goUser(Authentication authentication){
        //authentication is null if you have not yet logged in
        String name = authentication.getName();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority grant : authentication.getAuthorities()){
            roles.add(grant.getAuthority());
        }
        return "user";
    }

}
