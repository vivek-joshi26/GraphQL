package com.example.rest.service;

import com.example.rest.model.City;
import com.example.rest.model.Country;
import com.example.rest.repository.CityRepository;
import com.example.rest.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    public Country findById(Long id){
        return countryRepository.findById(id).get();
    }

    public List<Country> findByName(String name){
        return countryRepository.findByName(name);
    }

    public Country addCountry(String name, String language){
        Country country = new Country();
        country.setName(name);
        country.setLanguage(language);
        return countryRepository.save(country);
    }

    public Country addCountryWithCapital(String name, String language, City capital){
        Country country = new Country();
        country.setName(name);
        country.setLanguage(language);
        country.setCapital(cityRepository.findById(capital.getId()).get());
        return countryRepository.save(country);
    }

    public Country setCapital(Long countryId, Long cityId) {
        Country country = countryRepository.findById(countryId).get();
        country.setCapital(cityRepository.findById(cityId).get());
        return countryRepository.save(country);
    }
}
