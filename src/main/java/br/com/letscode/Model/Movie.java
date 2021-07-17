package br.com.letscode.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter

@NoArgsConstructor
public class Movie {

    private String title;
    private Integer year;
    private String imdbId;
    private String type;
    private Double rating;
    private Long votes;
    private Double score;

    public Double setScore(){
        return this.rating*this.votes;
    }



}
