package tacos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ingredient {
	@Id
	private String id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Type type;
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	public Ingredient() {
		this.id=null;
		this.name=null;
		this.type=null;
	}
	public Ingredient(String string, String string2, Type wrap) {
		this.id=string;
		this.name=string2;
		this.type=wrap;
	}

}
