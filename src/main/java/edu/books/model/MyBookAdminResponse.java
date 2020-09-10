package edu.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBookAdminResponse extends BookAdminResponse{

    private Integer userId;
}
