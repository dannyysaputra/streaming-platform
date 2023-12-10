//package projectuas.streamingPlatform.controller;
//
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import projectuas.streamingPlatform.data.entity.Movie;
//import projectuas.streamingPlatform.service.MovieService;
//
//@Controller
//public class TestMovieControllerForm {
//    private MovieService movieService;
//
//    @Autowired
//    public TestMovieControllerForm(MovieService movieService) {
//        this.movieService = movieService;
//    }
//    @GetMapping("/movie-form")
//    public String showMovieForm(Model model){
//        Movie movie = new Movie();
//        model.addAttribute("movies", movie);
//        return "movie-form";
//    }
//
//    @PostMapping("/movie-form/save")
//    public String addMovie(@Valid @ModelAttribute("movie") Movie movie,
//                           BindingResult result,
//                           Model model) {
//        movieService.pushMovie(movie);
//        return "redirect:/movie";
//    }
//}
