package projectuas.streamingPlatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectuas.streamingPlatform.data.entity.Genre;
import projectuas.streamingPlatform.data.repository.GenreRepository;
import projectuas.streamingPlatform.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(long id) {
        return genreRepository.findById(id).orElse(null);
    }
}
