package sbnz.integracija.example.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.integracija.example.facts.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeeklyReportDto {
	 List<Game> games;
	 Double profit;
}
