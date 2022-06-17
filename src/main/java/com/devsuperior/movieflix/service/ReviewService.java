package com.devsuperior.movieflix.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;
	
	@Transactional
    public ReviewDTO insert(ReviewDTO dto){
        
		Review entity = new Review();
		entity.setText(dto.getText());
		User user = authService.authenticated();
		entity.setUser(user);
        Movie movie = movieRepository.getOne(dto.getMovieId());
        entity.setMovie(movie);
        
        entity = repository.save(entity);
        
        return new ReviewDTO(entity,user);
    }
}
