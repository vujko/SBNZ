package sbnz.integracija.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.dto.WeeklyReportDto;
import sbnz.integracija.example.service.WeeklyService.GetWeeklyReportUseCase;
import sbnz.integracija.example.service.notification.GetNotificationsQuery;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/report", produces = "application/json")
public class ReportController {
	
	
	private final GetWeeklyReportUseCase getReport;
	
	@GetMapping(value="")
	public WeeklyReportDto getWeeklyReport() {
		return this.getReport.getWeeklyReport();
	}

}
