package com.stackroute.movieboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stackroute.movieboot.domain.Movie;
import com.stackroute.movieboot.exception.MovieAlreadyExistsException;
import com.stackroute.movieboot.exception.MovieNotFoundException;
import com.stackroute.movieboot.repository.MovieRepository;
@Qualifier("MovieServiceImpl")
@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException  {
		if (!movieRepository.existsById(movie.getMovieId())) {
			Movie savedMovie = movieRepository.save(movie);
			return savedMovie;
		} else
			throw new MovieAlreadyExistsException("movie already exists");
	}

	@Override
	public List<Movie> getAllMovies() {
		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		return movies;
	}

	@Override
	public Movie getMovieById(int movieId) throws MovieNotFoundException {
		if (movieRepository.existsById(movieId)) {
			Movie movie = movieRepository.getByMovieId(movieId);
			return movie;
		} else
			throw new MovieNotFoundException("Movie not found");

	}

	@Override
	public boolean deleteMovie(int movieId) throws MovieNotFoundException {
		if (movieRepository.existsById(movieId)) {
			Movie movie = movieRepository.getByMovieId(movieId);
			movieRepository.delete(movie);
			return true;
		} else
			throw new MovieNotFoundException("Movie not found");
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		if (movieRepository.existsById(movie.getMovieId())) {
			Movie updatedMovie = movieRepository.save(movie);
			return updatedMovie;
		} else
			throw new MovieNotFoundException("Movie Not Found");

	}

	@Override
	public List<Movie> getMovieByMovieTitle(String movieTitle) {
		List<Movie> movies = movieRepository.getByMovieTitle(movieTitle);
		return movies;
	}

	@Override
	public List<Movie> getByMovieAlpha(String movieTitle) {
		List<Movie> movies = movieRepository.getByMovieAlpha(movieTitle);
		return movies;
	}

}
