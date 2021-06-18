package sbnz.integracija.example.facts;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "registered_user")
public class RegisteredUser extends User {
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Purchase> purchases;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Rating> ratings;
	
	@ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;

	public RegisteredUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private UserRelation status;
    
    public enum UserRelation {
		NA, USER_SIMIRAL,  FORM_SIMILAR
	}

	public RegisteredUser(Long id, String email, String password, String firstName, String lastName,
			Set<Authority> authorities) {
		super(id, email, password, firstName, lastName, authorities);
		// TODO Auto-generated constructor stub
    	this.status = UserRelation.NA;
	}
	
	
}
