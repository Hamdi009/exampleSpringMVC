package com.example.springmvcproject.models.requests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class categoryForm {
    private long id;
    @Size(min=2, max=20, message="Name must be between 2 and 20 characters")
    private String name;
}
