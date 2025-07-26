package com.ccc.projects.lavoisier_api_v3.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ccc.projects.lavoisier_api_v3.dto.ValidRoles;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity @Table(name = "personal")
@NoArgsConstructor
public class Personal {
    @Id
    @GeneratedValue @UuidGenerator
    private UUID id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "Phone is required")
    @Column(nullable = false)
    private String telefono;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ValidRoles role;
}
