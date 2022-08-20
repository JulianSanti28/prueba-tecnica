package co.software.colombia.pruebatecnica.dao;

import co.software.colombia.pruebatecnica.model.Movie;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepositoryImpl implements  IMovieRepository{

    //Repository using CSV File (Dependence inyection)
    @Autowired
    private File fileRepository;

    @SneakyThrows
    @Override
    public Movie getById(int id) {
        FileReader input = new FileReader(this.fileRepository);
        CSVReader readerFile =  new CSVReader(input, ',');
        List<Movie> movies = new ArrayList<>();
        readerFile.readAll().stream().forEach(registro ->{
            if(registro[0].toLowerCase().equals("id")) return;
            if(Integer.parseInt(registro[0]) == id)
            movies.add(Movie.builder().id(Integer.parseInt(registro[0])).film(registro[1]).genre(registro[2]).studio(registro[3]).score(Integer.parseInt(registro[4])).year(Integer.parseInt(registro[5])).build());
        });
        return movies.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @SneakyThrows
    @Override
    public List<Movie> getAllMoviesOrderByAlphabet(int total, String order)
    {
        FileReader input = new FileReader(this.fileRepository);
        CSVReader readerFile =  new CSVReader(input, ',');
        List<Movie> movies = new ArrayList<>();
        readerFile.readAll().stream().forEach(registro ->{
            if(registro[0].toLowerCase().equals("id")) return;
            movies.add(Movie.builder().id(Integer.parseInt(registro[0])).film(registro[1]).genre(registro[2]).studio(registro[3]).score(Integer.parseInt(registro[4])).year(Integer.parseInt(registro[5])).build());
        });
        List<Movie> returnList = new ArrayList<>();
        switch (order.toLowerCase()){
            case "asc":
                returnList = movies.stream().sorted(Comparator.comparingInt(Movie::getId)).collect(Collectors.toList());
                break;
            case "desc":
                returnList = movies.stream().sorted(Comparator.comparingInt(Movie::getId).reversed()).collect(Collectors.toList());
                break;
        }
        return returnList.stream().limit(total).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public Movie save(Movie movieSave) {
        if (movieSave.isValid()){
            //Leer los registros existentes
            FileReader input = new FileReader(this.fileRepository);
            CSVReader readerFile =  new CSVReader(input, ',');
            List<String[]> movies = new ArrayList<>();
            readerFile.readAll().stream().forEach(registro ->{movies.add(registro);});
            //Agregar el nuevo registro
            movies.add(new String[] {String.valueOf(movieSave.getId()), movieSave.getFilm(), movieSave.getGenre(), movieSave.getStudio(), String.valueOf(movieSave.getScore()), String.valueOf(movieSave.getYear())});
            FileWriter writeNewMovie = new FileWriter(this.fileRepository);
            CSVWriter writeFile = new CSVWriter(writeNewMovie);
            //Guardar todos los registros
            writeFile.writeAll(movies);
            writeFile.close();
            return movieSave;
        }
        return null;
    }
}
