package co.software.colombia.pruebatecnica.service;

import co.software.colombia.pruebatecnica.dao.IMovieRepository;
import co.software.colombia.pruebatecnica.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements IMovieService{
    //Inyección de dependencias
    @Autowired
    private IMovieRepository movieRepository;

    @Override
    public Movie getById(int id) {
        return this.movieRepository.getById(id);
    }

    @Override
    public List<Movie> getAllMoviesOrderByAlphabet(int total, String order) {
        return this.movieRepository.getAllMoviesOrderByAlphabet(total, order);
    }

    @Override
    public Movie save(Movie movieSave) {
        return this.movieRepository.save(movieSave);
    }
}
