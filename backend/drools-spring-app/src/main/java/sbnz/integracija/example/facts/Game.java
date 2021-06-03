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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "game")
public class Game {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "developer")
	private String developer;
	
	@Column(name = "publisher")
	private String publisher;
	
	@OneToMany(mappedBy="game", fetch = FetchType.LAZY)
    private Set<Rating> ratings;
	
	@ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;
	
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
	
	private int score;
	
	public void increaseScore(int bonus) {
		this.score += bonus;
	}
}
