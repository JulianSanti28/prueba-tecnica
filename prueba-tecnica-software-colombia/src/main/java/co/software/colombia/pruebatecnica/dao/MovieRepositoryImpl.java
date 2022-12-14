package co.software.colombia.pruebatecnica.dao;

import co.software.colombia.pruebatecnica.model.Movie;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*Inversión de control*/
@Repository
public class MovieRepositoryImpl implements  IMovieRepository{

    //Repository using CSV File: Inyectando el Bean asociado al archivo de repositorio
    @Autowired
    private File fileRepository;

    @SneakyThrows
    @Override
    public Movie getById(int id) {
        try{
            FileReader input = new FileReader(this.fileRepository); //Archivo de lectura
            CSVReader readerFile =  new CSVReader(input, ',');//Lectura CSV ára el archivo
            List<Movie> movies = new ArrayList<>();//Lista para guardar los registros
            readerFile.readAll().stream().forEach(registro ->{
                if(registro[0].toLowerCase().equals("id")) return;//Omitiendo la primer fila (nombre de las columnas)
                if(Integer.parseInt(registro[0]) == id)//Si el id del registro es el que se está buscando, se guarda
                    movies.add(Movie.builder().id(Integer.parseInt(registro[0])).film(registro[1]).genre(registro[2]).studio(registro[3]).score(Integer.parseInt(registro[4])).year(Integer.parseInt(registro[5])).build());
            });
            readerFile.close();
            return movies.stream().findFirst().orElse(null);//Retorme el elemento obtenido con ese id, si no hay nada, retorne nulo
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null; //En caso de excepción
    }


    @Override
    public List<Movie> getAllMoviesOrderByAlphabet(int total, String order)
    {
        try{
            FileReader input = new FileReader(this.fileRepository);
            CSVReader readerFile =  new CSVReader(input, ',');
            List<Movie> movies = new ArrayList<>();
            readerFile.readAll().stream().forEach(registro ->{
                if(registro[0].toLowerCase().equals("id")) return;//Omitiendo la primer fila (nombre de las columnas)
                movies.add(Movie.builder().id(Integer.parseInt(registro[0])).film(registro[1]).genre(registro[2]).studio(registro[3]).score(Integer.parseInt(registro[4])).year(Integer.parseInt(registro[5])).build()); //Creando y guardando todas las películas
            });
            readerFile.close();
            var byNameAsc = (Comparator<Movie>) (Movie a, Movie b) -> a.getFilm().compareTo(b.getFilm());
            var byNameDesc = (Comparator<Movie>) (Movie a, Movie b) -> b.getFilm().compareTo(a.getFilm());
            //Se ordena dependiendo lo que desee el cliente y posteriormente el stream ordenado se limita al número dado por el parámetro
            return  movies.stream().sorted(order.equals("asc") ? byNameAsc : byNameDesc).limit(total).collect(Collectors.toList());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();//En caso de excepción
    }


    @Override
    public Movie save(Movie movieSave) {
        if (movieSave.isValid()){
            //Leer los registros existentes
            FileReader input = null;
            try {
                input = new FileReader(this.fileRepository);
                CSVReader readerFile =  new CSVReader(input, ',');
                List<String[]> movies = new ArrayList<>();
                readerFile.readAll().stream().forEach(registro ->{movies.add(registro);});
                //Agregar el nuevo registro
                movies.add(new String[] {String.valueOf(movieSave.getId()), movieSave.getFilm(), movieSave.getGenre(), movieSave.getStudio(), String.valueOf(movieSave.getScore()), String.valueOf(movieSave.getYear())});
                FileWriter writeNewMovie = new FileWriter(this.fileRepository);
                CSVWriter writeFile = new CSVWriter(writeNewMovie);
                //Guardar todos los registros
                writeFile.writeAll(movies);
                writeFile.close();//Cerrar el archivo
                return movieSave;//Retornar el registro guardado
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;//Información inválida, no se puede guardar
    }
}
