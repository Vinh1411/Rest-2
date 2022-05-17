package tacos;


import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import lombok.Data;

@Data
@Entity
public class Taco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date createdAt;
	private String name;
	@ManyToMany(targetEntity=Ingredient.class)
	private List<Ingredient> ingredients;
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
