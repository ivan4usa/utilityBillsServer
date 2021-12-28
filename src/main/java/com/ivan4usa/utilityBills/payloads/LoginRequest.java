package com.ivan4usa.utilityBills.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Incorrect email format")
    private String email;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 6, max = 30)
    private String password;

    private boolean longExpirationMode;
}
