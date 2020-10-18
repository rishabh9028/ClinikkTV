package com.clinikktv.api.controller;

import com.clinikktv.api.model.AudioContentDetailsResponse;
import com.clinikktv.service.business.AudioContentService;
import com.clinikktv.service.entity.AudioContentEntity;
import com.clinikktv.service.exception.AuthorizationFailedException;
import com.clinikktv.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class AudioContentController {

    @Autowired
    AudioContentService audioContentService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/AudioContent/all",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AudioContentDetailsResponse>> getAllAudioContents(
            @RequestHeader("authorization") final String accessToken)
            throws AuthorizationFailedException {
        List<AudioContentEntity> audioContents = audioContentService.getAllAudioContent(accessToken);
        List<AudioContentDetailsResponse> audioContentDetailsResponses = new ArrayList<>();
        for (AudioContentEntity audioContentEntity : audioContents) {
            AudioContentDetailsResponse audioContentDetailsResponse = new AudioContentDetailsResponse();
            audioContentDetailsResponse.setId(audioContentEntity.getUuid());
            audioContentDetailsResponse.setAudioName(audioContentEntity.getAudioName());
            audioContentDetailsResponse.setDescription(audioContentEntity.getDescription());
            audioContentDetailsResponse.setIsPaid(audioContentEntity.getPaid());
            audioContentDetailsResponses.add(audioContentDetailsResponse);
        }
        return new ResponseEntity<>(
                audioContentDetailsResponses, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/AudioContent/all/free",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AudioContentDetailsResponse>> getAllFreeAudioContents(
            @RequestHeader("authorization") final String accessToken)
            throws AuthorizationFailedException {
        List<AudioContentEntity> audioContents = audioContentService.getAllFreeAudioContent(accessToken);
        List<AudioContentDetailsResponse> audioContentDetailsResponses = new ArrayList<>();
        for (AudioContentEntity audioContentEntity : audioContents) {
            AudioContentDetailsResponse audioContentDetailsResponse = new AudioContentDetailsResponse();
            audioContentDetailsResponse.setId(audioContentEntity.getUuid());
            audioContentDetailsResponse.setAudioName(audioContentEntity.getAudioName());
            audioContentDetailsResponse.setDescription(audioContentEntity.getDescription());
            audioContentDetailsResponse.setIsPaid(audioContentEntity.getPaid());
            audioContentDetailsResponses.add(audioContentDetailsResponse);
        }
        return new ResponseEntity<>(
                audioContentDetailsResponses, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/AudioContent/all/paid",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AudioContentDetailsResponse>> getAllPaidAudioContents(
            @RequestHeader("authorization") final String accessToken)
            throws AuthorizationFailedException {
        List<AudioContentEntity> audioContents = audioContentService.getAllPaidAudioContent(accessToken);
        List<AudioContentDetailsResponse> audioContentDetailsResponses = new ArrayList<>();
        for (AudioContentEntity audioContentEntity : audioContents) {
            AudioContentDetailsResponse audioContentDetailsResponse = new AudioContentDetailsResponse();
            audioContentDetailsResponse.setId(audioContentEntity.getUuid());
            audioContentDetailsResponse.setAudioName(audioContentEntity.getAudioName());
            audioContentDetailsResponse.setDescription(audioContentEntity.getDescription());
            audioContentDetailsResponse.setIsPaid(audioContentEntity.getPaid());
            audioContentDetailsResponses.add(audioContentDetailsResponse);
        }
        return new ResponseEntity<>(
                audioContentDetailsResponses, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "AudioContent/all/{uuid}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getAudioById(
            @RequestHeader("authorization") final String accessToken,
            @PathVariable("uuid") String uuid)
            throws AuthorizationFailedException, UserNotFoundException {

        AudioContentEntity audioContents = audioContentService.getAllAudioContentById(uuid, accessToken);

            AudioContentDetailsResponse audioContentDetailsResponse = new AudioContentDetailsResponse();
            audioContentDetailsResponse.setId(audioContents.getUuid());
            audioContentDetailsResponse.setAudioName(audioContents.getAudioName());
            audioContentDetailsResponse.setDescription(audioContents.getDescription());
            audioContentDetailsResponse.setIsPaid(audioContents.getPaid());

        return new ResponseEntity<>(
                audioContentDetailsResponse, HttpStatus.OK);
    }
}
