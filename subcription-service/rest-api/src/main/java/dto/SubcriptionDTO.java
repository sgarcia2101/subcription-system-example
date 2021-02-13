package dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import domain.Gender;
import lombok.Data;

@Data
public class SubcriptionDTO implements Serializable {
    
	private static final long serialVersionUID = -2030784308853756407L;

	private Long id;

    @NotNull
    private String email;

    private String firstName;

    private Gender gender;

    @NotNull
    private LocalDate birthdate;

    @NotNull
    private Boolean consent;

    @NotNull
    private Long newsletterId;
    
}
