package com.ivan4usa.utilityBills.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @Email(message = "Email format accepted only")
    @NotBlank(message = "Email is required")
    private String email;

    private String firstName;

    private String lastName;
}
