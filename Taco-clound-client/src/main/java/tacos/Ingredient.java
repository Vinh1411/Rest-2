package tacos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force=true)
@Data
public class Ingredient {
	private String id;
	private String name;
	private Type type;
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	public Ingredient(String string, String string2, Type wrap) {
		this.id=string;
		this.name=string2;
		this.type=wrap;
	}

}
