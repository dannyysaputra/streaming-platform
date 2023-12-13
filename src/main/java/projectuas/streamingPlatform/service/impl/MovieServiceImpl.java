package projectuas.streamingPlatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import projectuas.streamingPlatform.data.entity.Genre;
import projectuas.streamingPlatform.data.entity.Movie;
import projectuas.streamingPlatform.data.repository.GenreRepository;
import projectuas.streamingPlatform.data.repository.MovieRepository;
import projectuas.streamingPlatform.service.GenreService;
import projectuas.streamingPlatform.service.MovieService;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long movie_id) {
        return movieRepository.findById(movie_id).orElse(null);
    }

    @Override
    public Movie pushMovie(Movie newMovie, Long genreId) {
        Genre genre = genreService.getGenreById(genreId);

        if (genre != null) {
            newMovie.addGenre(genre);
            genre.addMovie(newMovie);

            return movieRepository.save(newMovie);
        } else {
            return null;
        }
    }

    @Override
    public List<Movie> getByMovieName(String keyword) {
        List<Movie> movies = getByMovieNameAsc();
        List<Movie> result = new ArrayList<>();

        String[] keywords = keyword.trim().toLowerCase().split("\\s+");

        for (Movie movie : movies) {
            String movieName = movie.getMovieName().toLowerCase();

            boolean isMatch = true;
            for (String key : keywords) {
                if (!movieName.contains(key)) {
                    isMatch = false;
                    break;
                }
            }

            if (isMatch) {
                result.add(movie);
            }
        }

        return result;
    }


    private int binarySearchByMovieName(List<Movie> movies, String keyword) {
        int left = 0;
        int right = movies.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int res = movies.get(mid).getMovieName().compareToIgnoreCase(keyword);

            if (res == 0) {
                return mid;
            }

            if (res < 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }

    @Override
    public List<Movie> getByMovieNameAsc() {
//        return movieRepository.findAllByOrderByMovieNameAsc();
        List<Movie> movies = getAllMovies();
        bubbleSortByNameAsc(movies);
        return movies;
    }

    private void bubbleSortByNameAsc(List<Movie> movies) {
        int n = movies.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (movies.get(j).getMovieName().compareToIgnoreCase(movies.get(j + 1).getMovieName()) > 0) {
                    Movie temp = movies.get(j);
                    movies.set(j, movies.get(j + 1));
                    movies.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public List<Movie> getByMovieNameDesc() {
//        return movieRepository.findAllByOrderByMovieNameDesc();
        List<Movie> movies = getAllMovies();
        insertionSortByNameDesc(movies);
        return movies;
    }

    private void insertionSortByNameDesc(List<Movie> movies) {
        int n = movies.size();
        for (int i = 1; i < n; ++i) {
            Movie key = movies.get(i);
            int j = i - 1;

            while (j >= 0 && movies.get(j).getMovieName().compareToIgnoreCase(key.getMovieName()) < 0) {
                movies.set(j + 1, movies.get(j));
                j = j - 1;
            }
            movies.set(j + 1, key);
        }
    }

    @Override
    public List<Movie> getByRatingAsc() {
//        return movieRepository.findAllByOrderByRatingAsc();
        List<Movie> movies = getAllMovies();
        selectionSortByRatingAsc(movies);
        return movies;
    }

    private void selectionSortByRatingAsc(List<Movie> movies) {
        int n = movies.size();

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (movies.get(j).getRating() < movies.get(minIdx).getRating()) {
                    minIdx = j;
                }
            }
            Movie temp = movies.get(minIdx);
            movies.set(minIdx, movies.get(i));
            movies.set(i, temp);
        }
    }

    @Override
    public List<Movie> getByRatingDesc() {
//        return movieRepository.findAllByOrderByRatingDesc();
        List<Movie> movies = getAllMovies();
        quickSortByRatingDesc(movies, 0, movies.size() - 1);
        return movies;
    }

    private void quickSortByRatingDesc(List<Movie> movies, int low, int high) {
        if (low < high) {
            int pivot = partition(movies, low, high);
            quickSortByRatingDesc(movies, low, pivot - 1);
            quickSortByRatingDesc(movies, pivot + 1, high);
        }
    }

    private int partition(List<Movie> movies, int low, int high) {
        int pivot = movies.get(high).getRating();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (movies.get(j).getRating() >= pivot) {
                i++;
                Movie temp = movies.get(i);
                movies.set(i, movies.get(j));
                movies.set(j, temp);
            }
        }
        Movie temp = movies.get(i + 1);
        movies.set(i + 1, movies.get(high));
        movies.set(high, temp);
        return i + 1;
    }

    @Override
    public Movie updateMovie(Movie updatedMovie, Long movie_id) {
        return movieRepository.findById(movie_id).map(movie -> {
            movie.setMovieName(updatedMovie.getMovieName());
            movie.setYear(updatedMovie.getYear());
            movie.setMovieBackdropUrl(updatedMovie.getMovieBackdropUrl());
            movie.setMoviePosterUrl(updatedMovie.getMoviePosterUrl());
            movie.setDurationInMinute(updatedMovie.getDurationInMinute());
            movie.setRating(updatedMovie.getRating());
            movie.setTrailerLink(updatedMovie.getTrailerLink());
            movie.setDescription(updatedMovie.getDescription());
            movie.setMovieTags(updatedMovie.getMovieTags());

            movie.getGenres().clear(); // Remove existing genres

            for (Genre genre : updatedMovie.getGenres()) {
                Genre existingGenre = genreRepository.findById(genre.getId()).orElse(null);
                if (existingGenre != null) {
                    movie.addGenre(existingGenre); // Add updated genres to the movie
                }
            }
            
            return movieRepository.save(movie);
        }).orElse(null);
    }

    @Override
    public void deleteMovie(Long movie_id) {
        movieRepository.deleteById(movie_id);
    }
}
