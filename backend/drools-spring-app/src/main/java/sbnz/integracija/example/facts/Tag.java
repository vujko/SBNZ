package sbnz.integracija.example.facts;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "tag")
public class Tag {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TagType type;
	
	@Column(name = "name")
	private String name;

	public enum TagType {
		GENRE, THEME, PLAYER_SUPPORT, SPECIAL_SECTION
	}
	
}
