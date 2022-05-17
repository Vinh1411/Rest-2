package tacos.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	private RestTemplate rest=new RestTemplate();
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients=Arrays.asList(rest.getForObject("http://localhost:8080/ingredients", Ingredient[].class));
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@GetMapping
	public String showDesignForm(Model model) {
		return "design";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		List<Ingredient> ingrList = new ArrayList<Ingredient>();
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType().equals(type)) {
				ingrList.add(ingredient);
			}
		}
		return ingrList;
	}

	@PostMapping
	public String processDesign(@RequestParam("ingredientIds") String ingredientIds, @RequestParam("name") String name) {
		Taco taco=new Taco();
		taco.setName(name);
		ArrayList <Ingredient> list=new ArrayList<>();
		for(String i:ingredientIds.split(",")) {
			Ingredient ingredient=rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class,i);
			list.add(ingredient);
		}
		taco.setIngredients(list);
		rest.postForObject("http://localhost:8080/design", taco, Taco.class);
		log.info("Processing design: " + taco);
		return "redirect:/orders/current";
	}
}
