package sbnz.integracija.example.facts;

import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Data
@NoArgsConstructor
public class LoginEvent {

    private Date executionTime;
    private String email;

    public LoginEvent(String email){
        this.executionTime = new Date();
        this.email = email;
    }
}