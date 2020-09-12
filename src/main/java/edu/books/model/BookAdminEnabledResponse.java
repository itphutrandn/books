package edu.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAdminEnabledResponse {

    private Integer id;
    
    private String title;
    
    private String author;
    
    private String enabled;
    
    private String image;
    
    private String desciption;

}
