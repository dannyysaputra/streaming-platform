package projectuas.streamingPlatform.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @Column(nullable = false)
    private String genre;
}
