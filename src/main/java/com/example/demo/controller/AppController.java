package com.example.demo.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entity.Covers;
import com.example.demo.repo.SampleRepository;

@RestController
@RequestMapping("/demoapp/v1")
@CrossOrigin
@PropertySource("classpath:application.yml")
public class AppController {

	@Autowired
	SampleRepository repository;
	
	@Autowired
	RestTemplate template;
	
	@Value("${apikey}")
	String apiKey;
	
	
	@GetMapping("/mongo-data")
	public Map<Object, Object> getStudentData(HttpServletResponse response){
		
		List<Covers> covers = repository.findAll();
		covers.sort(new Comparator<Covers>() {

			@Override
			public int compare(Covers o1, Covers o2) {
				if(o1.getRatingval() > o2.getRatingval()) {
					return -1;
				}else if(o1.getRatingval() < o2.getRatingval()){
					return 1;
				}else {
					return 0;
				}
			}
		});
		Map<Object, Object> data = new HashMap<>();
		
		if(covers.size() == 0 && covers.isEmpty()) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			data.put("status", "Failure");
			data.put("message", "No Student records found in Database");
			return data;
		}
		
		response.setStatus(HttpStatus.OK.value());
		data.put("data", covers);
		data.put("status", "success");
		return data;
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/weather-data/{cityId}")
	public Map<Object, Object> getWeatherData(@PathVariable String cityId, HttpServletResponse response){
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://api.openweathermap.org/data/2.5/weather")
															.queryParam("id", cityId)
															.queryParam("appid", apiKey);
		
		String uri = builder.build().toUriString();
		
		Map<Object, Object> data = new HashMap<>();
				
		data = template.getForObject(uri, Map.class);
		if((int)data.get("cod") == 200) {
			response.setStatus(HttpStatus.OK.value());
			data.put("status", "success");
			return data;
		}
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		data.put("status", "Failure");
		data.put("message", "Failed to contact weather API");
		return data;
	}
		
}
