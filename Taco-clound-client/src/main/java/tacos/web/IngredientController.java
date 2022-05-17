package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;

@Slf4j
@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	private RestTemplate rest=new RestTemplate();
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("ingredient", new Ingredient(null, null, null));
	return "addIngredient";
	}
	
	@PostMapping
	public String addIngredient(Ingredient ingredient, Model model) {
		log.info("Ingredient saved: " + ingredient);
		rest.postForObject("http://localhost:8080/ingredients", ingredient, Ingredient.class);
		model.addAttribute(ingredient);
		return "addIngredientSuccess";
	}
	
	
}