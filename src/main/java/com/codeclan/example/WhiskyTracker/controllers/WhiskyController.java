package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

//    @GetMapping(value = "/whiskies")
//    public ResponseEntity<List<Whisky>> getAllWhiskies(){
//        return new ResponseEntity<List<Whisky>>(whiskyRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value="/whiskies/year")
    public ResponseEntity<List<Whisky>> getAllWhiskiesByYearQuery(@RequestParam(name="year") int year){
        return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
    }

//    @GetMapping(value="whiskies/age")
//    public ResponseEntity<List<Whisky>> getAllWhiskiesOfCertainAgeAndDistilleryQuery(@RequestParam(name="age") int age,
//                                                                                     @RequestParam(name="distillery") String distillery){
//        return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByAgeAndDistilleryName(age, distillery), HttpStatus.OK);
//    }

    @GetMapping(value="whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskiesByRegionQuery(
            @RequestParam(name="region", required = false) String region){
        if(region != null){
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<List<Whisky>>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="whiskies/age")
    public ResponseEntity<List<Whisky>> getAllWhiskiesOfCertainAgeGreaterQuery(@RequestParam(name="age") int age){
        return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByAgeGreaterThan(age), HttpStatus.OK);
    }
}
