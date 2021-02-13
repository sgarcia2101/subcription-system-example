package domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class Subcription {

    private Long id;

    @NonNull
    private String email;

    private String firstName;

    private Gender gender;

    @NonNull
    private LocalDate birthdate;

    @NonNull
    private Boolean consent;

    @NonNull
    private Newsletter newsletter;
}
