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
@RequestMapping("/ranking")
public class RootRest {

	private Root r;

	// Esta funcion si le pasas parametros te devuelve los datos de una sola
	// carrera, si no pasas parametros te devuelve los datos de todas las carreras
	@GetMapping
	public List<Datum> Ranking(@RequestParam(name = "id", required = false, defaultValue = "all") String carrera) {

		AppController.leerJson();
		r = AppController.getRootdata();
		List<Datum> listaDeDatos;
		listaDeDatos = r.getData();
		listaDeDatos = AppController.DarPosicionATodos(listaDeDatos);
		if (!carrera.equals("all")) {
			for (int a = 0; a < listaDeDatos.size(); a++) {
				for (int b = 0; b < listaDeDatos.get(a).races.size(); b++) {
					if (!carrera.equals(listaDeDatos.get(a).getRaces().get(b).getName())) {
						listaDeDatos.get(a).getRaces().remove(b);
						b--;
					}
				}
			}

		}

		listaDeDatos = QuitarDatosSobrantes(listaDeDatos);

		return listaDeDatos;
	}

	private List<Datum> QuitarDatosSobrantes(List<Datum> Datos) {

		for (int a = 0; a < Datos.size(); a++) {

			Datos.get(a)._id = null;
			Datos.get(a).age = null;

		}

		return Datos;

	}

}
