package sbnz.integracija.example.dto;

import lombok.Value;

@Value
public class RecommendDto {
	
	String genre;
	float lowerPrice;
	float higherPrice;
	String platform;
	String theme;
	String playerSupport;
	String specialSection;
}
