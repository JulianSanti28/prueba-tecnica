package co.software.colombia.pruebatecnica.dao;

import co.software.colombia.pruebatecnica.model.Movie;

import java.util.List;

public interface IMovieRepository {
    public Movie getById(int id);
    public List<Movie> getAllMoviesOrderByAlphabet(int total, String order);
    public Movie save(Movie movieSave);
}
