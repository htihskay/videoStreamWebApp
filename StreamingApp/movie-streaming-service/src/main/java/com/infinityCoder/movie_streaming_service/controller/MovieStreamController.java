package com.infinityCoder.movie_streaming_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class MovieStreamController {

    private static final Logger log = LoggerFactory.getLogger(MovieStreamController.class);

    public static final String VIDEO_DIRECTORY="/home/yakshith/";

    @Autowired
    private  MovieCatelogService movieCatelogService;

    @GetMapping("/stream/{videoPath}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String  videoPath) throws FileNotFoundException {

        File file=new File(VIDEO_DIRECTORY+videoPath);
        if(file.exists()){
            InputStreamResource inputStreamResource=new InputStreamResource(new FileInputStream(file));
            return  ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4"))
                    .body(inputStreamResource);
        }
        else {
             return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/stream/with-id/{videoInfoId}")
    public ResponseEntity<InputStreamResource> streamVideoById(@PathVariable Long  videoInfoId) throws FileNotFoundException {

       String moviePath= movieCatelogService.getMoviePath(videoInfoId);
         log.info("Resolved movie path {}",moviePath);
        return streamVideo(moviePath);

    }
}
