package co.software.colombia.pruebatecnica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    private int id;
    private String film;
    private String genre;
    private String studio;
    private int score;
    private int year;

    @JsonIgnore
    public boolean isValid(){
       return this.getId() != 0 && this.getYear() != 0 && (!this.getFilm().isEmpty()) && !this.getGenre().isEmpty() && !this.getStudio().isEmpty();
    }
}
