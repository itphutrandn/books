package edu.books.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.books.model.Auditable;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Book extends Auditable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "id")
    private Integer id;
	
	@Column(name="title")
	@NotNull
	@ApiModelProperty(notes = "title")
    private String title;
	
	@Column(name="author")
	@NotNull
	@ApiModelProperty(notes = "author")
    private String author;
	
	@Column(name="description")
	@ApiModelProperty(notes = "description")
    private String description;
	
	@Column(name="image")
	@ApiModelProperty(notes = "image")
    private String image;
	
	@Column(name="enabled")
	@NotNull
	@ApiModelProperty(notes = "enabled")
    private String enabled;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "book_user", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ApiModelProperty(notes = "book user)")
	@JsonIgnore
	private Set<User> users = new HashSet<User>();
	
}