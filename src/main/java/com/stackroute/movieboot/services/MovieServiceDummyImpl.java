package com.stackroute.movieboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.stackroute.movieboot.domain.Movie;
import com.stackroute.movieboot.exception.MovieAlreadyExistsException;
import com.stackroute.movieboot.exception.MovieNotFoundException;
@Qualifier("MovieServiceDummyImpl")
@Service
public class MovieServiceDummyImpl implements MovieService{

	// private Logger logger = (Logger) LoggerFactory.getLogger(MovieService.class);
	
	
	@Override
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
		System.out.println("movie is saved");
		return null;
	}

	@Override
	public List<Movie> getAllMovies() {
		System.out.println("getting all movies in database");
		return null;
	}

	@Override
	public Movie getMovieById(int movieId) throws MovieNotFoundException {
		System.out.println("getting movie by id");
		return null;
	}

	@Override
	public boolean deleteMovie(int movieId) throws MovieNotFoundException {
		System.out.println("deleted movie by id");
		return false;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		System.out.println("updated the movie");
		return null;
	}

	@Override
	public List<Movie> getMovieByMovieTitle(String movieTitle) {
		System.out.println("we are getting movie by title");
		return null;
	}

//	@Override
//	public List<Movie> getByMovieAlpha(String movieTitle) {
//		System.out.println("we are getting movie title by first character");
//		return null;
//	}

}
