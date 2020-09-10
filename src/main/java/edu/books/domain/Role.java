package edu.books.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import edu.books.model.Auditable;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="role")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Role extends Auditable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@ApiModelProperty(notes = "id")
    private int id;
	
	@Column(name="name")
	@NotNull
	@ApiModelProperty(notes = "name")
    private String name;
	
}