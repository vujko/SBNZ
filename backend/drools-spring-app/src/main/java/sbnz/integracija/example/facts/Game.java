package sbnz.integracija.example.facts;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "game")
public class Game {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	
	@OneToMany(mappedBy="game", fetch = FetchType.LAZY)
    private Set<Rating> ratings;
	
	@OneToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;
	
	@Column(name = "platform")
	private String platform;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "average_rating")
	private float averageRating;
	
	@Column(name = "downloads_num")
	private int numOfDownloads;
	
	@Column(name = "raters_num")
	private int numOfRaters;

}
