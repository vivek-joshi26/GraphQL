package com.example.rest.controller;

import com.example.rest.model.City;
import com.example.rest.model.Country;
import com.example.rest.service.CityService;
import com.example.rest.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CountryContoller {

    @Autowired
    private CountryService countryService;


    @Autowired
    private CityService cityService;

    @QueryMapping
    private List<Country> allCountries(){
       return countryService.findAll();
    }

    @QueryMapping
    private Country country(@Argument Long id){
        return countryService.findById(id);
    }

    @QueryMapping
    private List<Country> countriesByName(@Argument String name){
        return countryService.findByName(name);
    }

    @SchemaMapping(typeName = "City", field = "capital")
    public City capital(Country country){
        return cityService.findById(country.getCapital().getId());
    }

    @MutationMapping
    public Country addCountry(@Argument String name, @Argument String language){
        return countryService.addCountry(name, language);
    }

    @MutationMapping
    public Country addCountryWithCapital(@Argument String name, @Argument String language, @Argument Long id,@Argument City capital){
        return countryService.addCountryWithCapital(name, language, capital);
    }

    @MutationMapping
    public Country setCapital(@Argument Long countryId, @Argument Long cityId){
        return countryService.setCapital(countryId, cityId);
    }

}
