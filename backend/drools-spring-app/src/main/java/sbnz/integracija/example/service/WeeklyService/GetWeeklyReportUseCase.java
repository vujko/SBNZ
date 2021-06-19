package sbnz.integracija.example.service.WeeklyService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.dto.WeeklyReportDto;
import sbnz.integracija.example.repository.NotificationRepository;


public interface GetWeeklyReportUseCase {
	public WeeklyReportDto getWeeklyReport();
}
