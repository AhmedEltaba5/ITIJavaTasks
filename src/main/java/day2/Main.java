/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed eltabakh
 */
public class Main {
    
    public static void main(String[] args) {
        
        //list of countries
        CountryDAO countryDAO = new CountryDAO();
        List<Country> countries = countryDAO.readAllCountries("countries.csv");
        System.out.println("=== list of countries ===");
        System.out.println(countries);
        
        //list of cities
        CityDAO cityDAO = new CityDAO();
        List<City> cities = cityDAO.readAllCites("cities.csv");
        System.out.println("=== list of cities ===");
        System.out.println(cities);
        
        //map of country=>cities
        Map<Integer, List<City>> countryCities = new HashMap<>();

        for (City c : cities) {
            if (!countryCities.containsKey(c.code)) {
                List<City> city = new ArrayList<>();
                city.add(c);
                countryCities.put(c.code, city);
            } else {
                countryCities.get(c.code).add(c);
            }
        }
        System.out.println("=== country=>cities Map ===");
        countryCities.forEach((i, c) -> {
            System.out.println("key = " + i + " cities= " + c);
        });
        
        //sort cities in each country according to population
        Iterator<Integer> it = countryCities.keySet().iterator();
        it.forEachRemaining(key -> {
            countryCities.get(key).sort((o1, o2) -> {
                if (o1.population < o2.population)
                    return 1;
                else if (o1.population == o2.population)
                    return 0;
                return -1;
            });
        });
        System.out.println("=== sorted Map ===");
        countryCities.forEach((i, c) -> {
            System.out.println("key = " + i + " cities= " + c);
        });
    }
    
}