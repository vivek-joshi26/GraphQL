package com.example.rest.service;

import com.example.rest.model.City;
import com.example.rest.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll(){
        return cityRepository.findAll();
    }

    public City findById(Long id){
        return cityRepository.findById(id).get();
    }

    public List<City> findByName(String name){
        return cityRepository.findByName(name);
    }

    public City save(String name, Integer population){
        City city = new City();
        city.setName(name);
        city.setPopulation(population);
        return cityRepository.save(city);
    }

    public String delete(Long id){
        cityRepository.deleteById(id);
        return "Deleted city with id: " + id;
    }

}
