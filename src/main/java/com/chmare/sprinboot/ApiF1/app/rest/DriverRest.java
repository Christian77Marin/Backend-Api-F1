package com.chmare.sprinboot.ApiF1.app.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chmare.sprinboot.ApiF1.app.Controllers.AppController;
import com.chmare.sprinboot.ApiF1.app.domain.Datum;
import com.chmare.sprinboot.ApiF1.app.domain.Root;

@RestController
@RequestMapping("/driver")
public class DriverRest {

	private Root r;

	// Se especifica un piloto y la api te devuelve los datos del mismo y todos sus
	// datos en carreras en forma de json
	@GetMapping
	public Datum Driver(@RequestParam(name = "id", required = false, defaultValue = "all") String nameDriver) {

		AppController.ReadJson();
		r = AppController.getRootdata();
		List<Datum> dataList;
		Datum driver = new Datum();
		dataList = r.getData();
		dataList = AppController.GivePositionAll(dataList);

		for (int a = 0; a < dataList.size(); a++) {

			if (nameDriver.equals(dataList.get(a).getName())) {
				driver = dataList.get(a);
			}

		}

		return driver;
	}

}
