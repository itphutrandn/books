package edu.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

    private String deviceCode;
    private String phoneNumber;
    private Integer pageSize;
    private Integer pageIndex;
}
