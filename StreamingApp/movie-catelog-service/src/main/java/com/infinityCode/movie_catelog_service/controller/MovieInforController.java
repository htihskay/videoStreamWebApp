package com.infinityCode.movie_catelog_service.controller;

import com.infinityCode.movie_catelog_service.modal.MovieInfo;
import com.infinityCode.movie_catelog_service.modal.MovieInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieInforController {

    private static final Logger log = LoggerFactory.getLogger(MovieInforController.class);
    @Autowired
    private MovieInfoRepository repository;

    @PostMapping("/movie-info/save")
    public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> movieInforList){
      return  repository.saveAll(movieInforList);
    }

    @GetMapping("movie-info/list")
    public List<MovieInfo> findAll(){
        log.info("list movie api");
        return repository.findAll();
    }

    @GetMapping("movie-info/find-movie-by-id/{movieId}")
    public String findMoiveById(@PathVariable Long movieId){
        var videoInfoOptional=repository.findById(movieId);
        return videoInfoOptional.map(MovieInfo::getPath).orElse(null
        );
    }

}
