package com.example.rest.controller;


import com.example.rest.model.City;
import com.example.rest.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @QueryMapping
    public List<City> allCities(){
        return cityService.findAll();
    }

    @QueryMapping
    public City city(@Argument Long id){
        return cityService.findById(id);
    }

    @QueryMapping
    public List<City> citiesByName(@Argument String name){
        return cityService.findByName(name);
    }


    @MutationMapping
    public City addCity(@Argument String name, @Argument Integer population){
        return cityService.save(name, population);
    }

    @MutationMapping
    public String deleteCity(@Argument Long id){
        return cityService.delete(id);
    }

}
