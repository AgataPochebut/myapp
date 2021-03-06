package com.it.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * The type User response dto.
 */
@NoArgsConstructor
@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private Set<RoleResponseDto> roles;

}
