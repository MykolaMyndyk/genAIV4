package com.example.genaiv4.controller;

import com.example.genaiv4.Model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/countries")
public class CountriesController {

    @GetMapping("/get")
    @ResponseBody
    public List<Country> getFoos(@RequestParam(required = false) String country, @RequestParam(required = false) String population,
                          @RequestParam(required = false) String sort, @RequestParam(required = false) String pagination) {

        List<Country> result = getResponse();


        return result;
    }

    private List<Country> getResponse() {
        try {
            URL url = new URL("https://restcountries.com/v3.1/all");
//            String query = INSERT_HERE_YOUR_URL_PARAMETERS;

            //make connection
            URLConnection urlc = url.openConnection();

            //use post mode
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            InputStream inputStream = urlc.getInputStream();


            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            List<Country> myObjects = mapper.readValue(sb.toString(), new TypeReference<List<Country>>(){});
//            List<Country> countries = mapper.readValue(sb.toString(), List<>Country.class);

            return myObjects;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
