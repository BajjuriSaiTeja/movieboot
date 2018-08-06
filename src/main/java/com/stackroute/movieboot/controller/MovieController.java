package com.stackroute.movieboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.movieboot.domain.Movie;
import com.stackroute.movieboot.exception.MovieAlreadyExistsException;
import com.stackroute.movieboot.exception.MovieNotFoundException;
import com.stackroute.movieboot.services.MovieService;

@RestController
@RequestMapping("/api/v1/movieboot/")
public class MovieController {
	
	
	//@Autowired
	@Qualifier("MovieServiceImpl")
	@Autowired
	private MovieService movieService;

//	
//	public MovieController(MovieService movieService) {
//		this.movieService = movieService;
//	}

	// save
	@PostMapping(value = "/movie", produces = { "application/json" })
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
		Movie savedMovie;
		try {
			savedMovie = movieService.saveMovie(movie);
		} catch (MovieAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Movie>(savedMovie, HttpStatus.OK);

	}

	// getall
	@GetMapping(value = "/movies", produces = { "application/json" })

	public ResponseEntity<List<Movie>> getAllMovies() {

		List<Movie> movies = movieService.getAllMovies();
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}

	// delete
	@RequestMapping(value = "/movie/{movieId}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<?> deleteMoviefromDB(@PathVariable int movieId) {
		try {
			boolean result = movieService.deleteMovie(movieId);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("{ \"message\": \"" + "deleted" + "\"}", HttpStatus.OK);
	}

	// update
	@RequestMapping(value = "/movie", method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<?> updateMovieToDB(@RequestBody Movie movie, @RequestParam int movieId) {
		Movie movieUpdated;
		try {
			movieUpdated = movieService.updateMovie(movie);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Movie>(movieUpdated, HttpStatus.FOUND);
	}

	// getbyid
	@RequestMapping(value = "/byid/movie", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getMovieByIdFromDB(@RequestParam int movieId) {
		Movie movie;
		try {
			movie = movieService.getMovieById(movieId);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Movie>(movie, HttpStatus.FOUND);
	}

	// getbytitle
	@RequestMapping(value = "/getbytitle/movie", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getMovieByTitleFromDB(@RequestParam String movieTitle) {
		List<Movie> movie = movieService.getMovieByMovieTitle(movieTitle);
		if(!movie.isEmpty())
		return new ResponseEntity<List<Movie>>(movie, HttpStatus.FOUND);
		else
			return new ResponseEntity<String>("{ \"message\": \"" + "no movies  with this name" + "\"}", HttpStatus.OK);
	}

	// search
//	@RequestMapping(value = "/bycharacter/movie", method = RequestMethod.GET, produces = { "application/json" })
//	public ResponseEntity<?> getMovieByMovieTitleFromDB(@RequestParam("term") String movieCheck) {
//		List<Movie> movie = movieService.getByMovieAlpha(movieCheck);
//		return new ResponseEntity<List<Movie>>(movie, HttpStatus.FOUND);
//	}

}
