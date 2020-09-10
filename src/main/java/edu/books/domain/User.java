package edu.books.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.books.model.Auditable;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@ApiModelProperty(notes = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	@ApiModelProperty(notes = "firstName")
	private String firstName;
	
	@Column(name = "last_name")
	@ApiModelProperty(notes = "nalastNameme")
	private String lastName;
	
	@Column(name = "password")
	@NotNull
	@ApiModelProperty(notes = "password")
	private String password;
	  
    @Column(name="email")
    @ApiModelProperty(notes = "email")
    private String email;

    @Column(name="last_used")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @ApiModelProperty(notes = "last logged in")
    private Date lastUsed;
    
	@Column(name = "confirm_password", nullable = true)
	@ApiModelProperty(notes = "Confirm password")
	@Transient
	private String confirmPassword;

	@Column(name = "phone", length = 11)
	@ApiModelProperty(notes = "phone")
	private String phone;

	@Column(name = "street", length = 100, nullable = true)
	@ApiModelProperty(notes = "street")
	private String street;
	
	@Column(name = "enabled")
	@ApiModelProperty(notes = "enabled")
	@JsonIgnore
	private String enabled;

	@Column(name = "token")
	@ApiModelProperty(notes = "token")
	private String token;
	
	@Column(name = "avatar")
	@ApiModelProperty(notes = "avatar")
	private String avatar;

	@Column(name = "birth_date", nullable = true)
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(notes = "birth date")
	private Date birthDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ApiModelProperty(notes = "user role)")
	@JsonIgnore
	private Set<Role> roles = new HashSet<Role>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
	}
}