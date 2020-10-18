package com.clinikktv.api.controller;


import com.clinikktv.api.model.VideoContentDetailsResponse;
import com.clinikktv.service.business.VideoContentService;
import com.clinikktv.service.entity.VideoContentEntity;
import com.clinikktv.service.exception.AuthorizationFailedException;
import com.clinikktv.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/")
public class VideoContentController {

    @Autowired
    VideoContentService videoContentService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/VideoContent/all",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VideoContentDetailsResponse>> getAllVideoContents(
            @RequestHeader("authorization") final String accessToken)
            throws AuthorizationFailedException, SQLException {
        List<VideoContentEntity> videoContents = videoContentService.getAllVideoContent(accessToken);
        List<VideoContentDetailsResponse> videoContentDetailsResponses = new ArrayList<>();
        for (VideoContentEntity videoContentEntity : videoContents) {
            VideoContentDetailsResponse videoContentDetailsResponse = new VideoContentDetailsResponse();
            videoContentDetailsResponse.setId(videoContentEntity.getUuid());
            videoContentDetailsResponse.setVideoName(videoContentEntity.getVideoName());
            videoContentDetailsResponse.setDescription(videoContentEntity.getDescription());
            videoContentDetailsResponse.setIsPaid(videoContentEntity.getPaid());

            videoContentDetailsResponses.add(videoContentDetailsResponse);
        }
        return new ResponseEntity<>(
                videoContentDetailsResponses, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/VideoContent/all/free",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VideoContentDetailsResponse>> getAllFreeVideoContents(
            @RequestHeader("authorization") final String accessToken)
            throws AuthorizationFailedException {
        List<VideoContentEntity> videoContents = videoContentService.getAllFreeVideoContent(accessToken);
        List<VideoContentDetailsResponse> videoContentDetailsResponses = new ArrayList<>();
        for (VideoContentEntity videoContentEntity : videoContents) {
            VideoContentDetailsResponse videoContentDetailsResponse = new VideoContentDetailsResponse();
            videoContentDetailsResponse.setId(videoContentEntity.getUuid());
            videoContentDetailsResponse.setVideoName(videoContentEntity.getVideoName());
            videoContentDetailsResponse.setDescription(videoContentEntity.getDescription());
            videoContentDetailsResponse.setIsPaid(videoContentEntity.getPaid());
            videoContentDetailsResponses.add(videoContentDetailsResponse);
        }
        return new ResponseEntity<>(
                videoContentDetailsResponses, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/VideoContent/all/paid",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VideoContentDetailsResponse>> getAllPaidVideoContents(
            @RequestHeader("authorization") final String accessToken)
            throws AuthorizationFailedException {
        List<VideoContentEntity> videoContents = videoContentService.getAllPaidVideoContent(accessToken);
        List<VideoContentDetailsResponse> videoContentDetailsResponses = new ArrayList<>();
        for (VideoContentEntity videoContentEntity : videoContents) {
            VideoContentDetailsResponse videoContentDetailsResponse = new VideoContentDetailsResponse();
            videoContentDetailsResponse.setId(videoContentEntity.getUuid());
            videoContentDetailsResponse.setVideoName(videoContentEntity.getVideoName());
            videoContentDetailsResponse.setDescription(videoContentEntity.getDescription());
            videoContentDetailsResponse.setIsPaid(videoContentEntity.getPaid());
            videoContentDetailsResponses.add(videoContentDetailsResponse);
        }
        return new ResponseEntity<>(
                videoContentDetailsResponses, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "VideoContent/all/{uuid}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getVideobyId(
            @RequestHeader("authorization") final String accessToken,
            @PathVariable("uuid") String uuid)
            throws AuthorizationFailedException, UserNotFoundException {

        VideoContentEntity videoContents = videoContentService.getAllVideoContentById(uuid, accessToken);

        VideoContentDetailsResponse videoContentDetailsResponse = new VideoContentDetailsResponse();
        videoContentDetailsResponse.setId(videoContents.getUuid());
        videoContentDetailsResponse.setVideoName(videoContents.getVideoName());
        videoContentDetailsResponse.setDescription(videoContents.getDescription());
        videoContentDetailsResponse.setIsPaid(videoContents.getPaid());
        videoContentDetailsResponse.setVideo(videoContents.getVideo());

        return new ResponseEntity<>(
                videoContentDetailsResponse, HttpStatus.OK);
    }

}
