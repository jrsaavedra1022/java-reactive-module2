package edu.co.cedesistemas.reactiva.modulo2.streaming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Integer id;
    private String title;
    private String genre;
    private String creator;
    private Integer duration;
    private String releaseYear;
    private Boolean viewed;
    private Integer timeViewed;

}
