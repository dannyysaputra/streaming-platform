package projectuas.streamingPlatform.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String movieName;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer durationInMinute;

    @Column(nullable = false)
    private String trailerLink;

    @Column(nullable = false, length = 512)
    private String moviePosterUrl;

    @Column(nullable = false, length = 512)
    private String movieBackdropUrl;

    @Column(nullable = false)
    private String movieTags;

    @Column(nullable = false)
    private Integer rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private Set<Genre> genres = new HashSet<>();

    public Movie(String movieName, String year, String description, Integer durationInMinute, String trailerLink, String moviePosterUrl, String movieBackdropUrl, String movieTags, Integer rating) {
        this.movieName = movieName;
        this.year = year;
        this.description = description;
        this.durationInMinute = durationInMinute;
        this.trailerLink = trailerLink;
        this.moviePosterUrl = moviePosterUrl;
        this.movieBackdropUrl = movieBackdropUrl;
        this.movieTags = movieTags;
        this.rating = rating;
    }

    public void addGenre(Genre genre) {
//        genres.add(genre);
        this.genres.add(genre);
        genre.getMovies().add(this);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getMovies().remove(this);
    }
}
