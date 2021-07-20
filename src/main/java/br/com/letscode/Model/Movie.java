package br.com.letscode.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String year;
    private String imdbId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double rating;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Long votes;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Double score;

    public Double setScore(){
        return this.rating*this.votes;
    }



}
