package com.sgarcia.subcription.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Long newsletterId;
}
