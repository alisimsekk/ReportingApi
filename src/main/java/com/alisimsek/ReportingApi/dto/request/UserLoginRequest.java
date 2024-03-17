package com.alisimsek.ReportingApi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @NotNull (message = "Email can't be empty")
    @Size(max = 128)
    @Email
    private String email;

    @NotNull (message = "Password can't be empty")
    @Size(max = 32)
    private String password;
}
