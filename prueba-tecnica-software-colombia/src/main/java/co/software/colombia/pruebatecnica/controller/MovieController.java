package co.software.colombia.pruebatecnica.controller;

import co.software.colombia.pruebatecnica.model.Movie;
import co.software.colombia.pruebatecnica.service.IMovieService;
import co.software.colombia.pruebatecnica.utils.JsonResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping()
public class MovieController {

    //Inyección de depencias del servicio
    @Autowired
    private IMovieService movieService;

    @PostMapping("/movie")
    public ResponseEntity<JsonResponseMessage> save(@RequestBody Movie movie){

        Movie saved = this.movieService.save(movie);
            if(saved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(JsonResponseMessage.builder().message("La película fue creada con éxito").build());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JsonResponseMessage.builder().message("La película no fue creada con éxito").build());

    }

    @GetMapping("/movie")
    public ResponseEntity<Movie> getById(@RequestParam("id") int id){
        Movie foundMovie = this.movieService.getById(id);
        if(foundMovie == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Movie.builder().build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundMovie);
    }

    @GetMapping("/movies")
    public ResponseEntity<?> getAllMoviesOrderByAlphabet(@RequestParam int total, @RequestParam String order){
        List<Movie> movies = this.movieService.getAllMoviesOrderByAlphabet(total, order);
        if(movies.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(JsonResponseMessage.builder().message("No existen registros para los parámetros dados").build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }
}
