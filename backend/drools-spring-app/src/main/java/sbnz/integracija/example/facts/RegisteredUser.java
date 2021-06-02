package sbnz.integracija.example.facts;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "registered_user")
public class RegisteredUser extends User {
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Purchase> purchases;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Rating> ratings;
	
	@OneToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;
}
