package com.ccc.projects.lavoisier_api_v3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRecord(
    @Email @NotBlank String email,
    @NotBlank @Size(max = 8) String password
) {

}
