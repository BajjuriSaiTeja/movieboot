package com.stackroute.movieboot.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.stackroute.movieboot.domain.Movie;
import com.stackroute.movieboot.exception.MovieAlreadyExistsException;
import com.stackroute.movieboot.exception.MovieNotFoundException;

public interface MovieService {

	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;

	public List<Movie> getAllMovies();

	public Movie getMovieById(int movieId) throws MovieNotFoundException;

	public boolean deleteMovie(int movieId) throws MovieNotFoundException;

	public Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	public List<Movie> getMovieByMovieTitle(String movieTitle);
	
	public List<Movie> getByMovieAlpha(String movieTitle);
}
