package co.software.colombia.pruebatecnica.controller;

import co.software.colombia.pruebatecnica.model.Movie;
import co.software.colombia.pruebatecnica.service.IMovieService;
import co.software.colombia.pruebatecnica.utils.JsonResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*Controlador REST para los servicios asociados al modelo Movie*/
@RestController
@RequestMapping()
public class MovieController {

    //Inyección de depencias. Inyectando el servicio que implementa la interface IMovieService
    @Autowired
    private IMovieService movieService;

    /*Save a Movie POST Method*/
    @PostMapping("/movie")
    public ResponseEntity<JsonResponseMessage> save(@RequestBody Movie movie){
        //Comunicación con el repositorio a través de la capa de servicio
        Movie saved = this.movieService.save(movie);
            if(saved != null){ //Si el servicio logró guardar el registro
            return ResponseEntity.status(HttpStatus.CREATED).body(JsonResponseMessage.builder().message("La película fue creada con éxito").build());
        }
        //El servicio no logró guardar el registro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JsonResponseMessage.builder().message("La película no fue creada con éxito").build());

    }

    /*Get Movie By Id GET Method*/
    @GetMapping("/movie")
    public ResponseEntity<Movie> getById(@RequestParam("id") int id){
        Movie foundMovie = this.movieService.getById(id);
        if(foundMovie == null){ //Si no se obtuvieron resultados para el id dado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Movie.builder().build());
        }
        //Se envía la información encontrada
        return ResponseEntity.status(HttpStatus.OK).body(foundMovie);
    }

    /*Get Movies Order By Alphabet GET Method*/
    /*No es necesario poner entre paréntesis el nombre del parámetro en @RequestParam debido a que se mapean a atrobutos que se llaman igual*/
    @GetMapping("/movies")
    public ResponseEntity<?> getAllMoviesOrderByAlphabet(@RequestParam int total, @RequestParam String order){
        List<Movie> movies = this.movieService.getAllMoviesOrderByAlphabet(total, order);
        if(movies.isEmpty()){ //Si la lista devuelta es vacía
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResponseMessage.builder().message("No existen registros para los parámetros dados").build());
        }
        /*Se envían los registros obtenidos*/
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }
}
