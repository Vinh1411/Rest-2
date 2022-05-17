package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import tacos.Ingredient;
@Component
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
