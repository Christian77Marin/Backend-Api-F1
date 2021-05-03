package com.chmare.sprinboot.ApiF1.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.chmare.sprinboot.ApiF1.app.Controllers.AppController;
import com.chmare.sprinboot.ApiF1.app.domain.Datum;
import com.chmare.sprinboot.ApiF1.app.domain.Root;
import com.chmare.sprinboot.ApiF1.app.domain.Race;

@RestController
@RequestMapping("/races")
public class RaceRest {

	
	
	
	private Root r;

	
	@GetMapping
	public List<String> Races() {

		AppController.ReadJson();
		r = AppController.getRootdata();
		List<Datum> dataList;
		Datum driver = new Datum();
		dataList = r.getData();
		List<String> RaceList = new ArrayList<String>();

		for(int a = 0; a < dataList.get(0).races.size(); a++){
			
			RaceList.add(dataList.get(0).getRaces().get(a).getName());
		}

		return RaceList;
	}

}
