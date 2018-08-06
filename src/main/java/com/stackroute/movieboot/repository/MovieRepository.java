package com.stackroute.movieboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.movieboot.domain.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
	public Movie getByMovieId(int movieId);

	public List<Movie> getByMovieTitle(String movieTitle);

	public List<Movie> getByMovieRating(String movieRating);

	public List<Movie> getByMovieRatingAndYearOfRelease(String movieRating, int yearOfRelease);

	// @Query("select movie from Movie movie where movie.movieTitle LIKE
	// CONCAT(:movieTitle,'%')")
	// public List<Movie> getByMovieAlpha(@Param("movieTitle") String movieTitle);
}
