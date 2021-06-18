package sbnz.integracija.example.facts;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Notification {
	
	private String message;
	private Date date; 
	private int code;

}
