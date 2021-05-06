package com.chmare.sprinboot.ApiF1.app.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chmare.sprinboot.ApiF1.app.Controllers.AppController;
import com.chmare.sprinboot.ApiF1.app.domain.Datum;
import com.chmare.sprinboot.ApiF1.app.domain.Root;

@RestController
@RequestMapping("/ranking")
public class RootRest {

	private Root r;

	// Esta funcion si le pasas parametros te devuelve los datos de una sola
	// carrera, si no pasas parametros te devuelve los datos de todas las carreras
	@GetMapping
	public List<Datum> Ranking(@RequestParam(name = "id", required = false, defaultValue = "all") String race) {

		AppController.ReadJson();
		r = AppController.getRootdata();
		List<Datum> dataList;
		dataList = r.getData();
		dataList = AppController.GivePositionAll(dataList);
		if (!race.equals("all")) {
			for (int a = 0; a < dataList.size(); a++) {
				for (int b = 0; b < dataList.get(a).races.size(); b++) {
					if (!race.equals(dataList.get(a).getRaces().get(b).getName())) {
						dataList.get(a).getRaces().remove(b);
						b--;
					}
				}
			}

		}

		dataList = RemoveLeftOverData(dataList);
		
	if(race.equals("all")){Collections.sort(dataList, (o1, o2) -> o1.getGlobalPos().compareTo(o2.getGlobalPos()));}
	if(!race.equals("all")){Collections.sort(dataList, (o1, o2) -> o1.getRaces().get(0).getPosition().compareTo(o2.getRaces().get(0).getPosition()));}
	
		return dataList;
	}

	private List<Datum> RemoveLeftOverData(List<Datum> data) {

		for (int a = 0; a < data.size(); a++) {

			data.get(a)._id = null;
			data.get(a).age = null;

		}

		return data;

	}

}
