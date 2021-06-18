package sbnz.integracija.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecommendDto {
	
	String genre;
	float lowerPrice;
	float higherPrice;
	String platform;
	String theme;
	String playerSupport;
	String specialSection;
}
