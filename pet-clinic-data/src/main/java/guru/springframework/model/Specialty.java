package guru.springframework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import guru.springframework.model.PetType.PetTypeBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity {
	
	@Column(name = "description")
	private String description;
	
}
