package com.chmare.sprinboot.ApiF1.app.Controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.chmare.sprinboot.ApiF1.app.domain.Datum;
import com.chmare.sprinboot.ApiF1.app.domain.Root;
import com.google.gson.Gson;

@Controller
public class AppController {

	public static Root rootdata;

	public static Root getRootdata() {
		return rootdata;
	}

	// lee el json
	public static void leerJson() {

		String json = "";

		String ruta = "C:\\Users/cmarinre/Downloads/data.json";
		try {
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			String linea = "";
			while ((linea = br.readLine()) != null) {
				json += linea;
			}

			br.close();

		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}

		Gson gson = new Gson();
		rootdata = gson.fromJson(json, Root.class);

	}

	// Da la posicion por cada carrera
	public static List<Datum> DarPosicion(List<Datum> listaDeDatos, String Carrera) {
		List<Date> listaDeTiempos = new ArrayList<Date>();
		listaDeDatos = rootdata.getData();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.SSS");

		for (int a = 0; a < listaDeDatos.size(); a++) {
			for (int b = 0; b < listaDeDatos.get(a).races.size(); b++) {
				if (listaDeDatos.get(a).races.get(b).name.equals(Carrera)) {

					try {

						listaDeTiempos.add((Date) dateFormat.parse(listaDeDatos.get(a).races.get(b).getTime()));
					} catch (ParseException e) {

						e.printStackTrace();
					}
				}
			}
		}

		Collections.sort(listaDeTiempos);

		for (int a = 0; a < rootdata.getData().size(); a++) {
			for (int b = 0; b < rootdata.getData().get(a).getRaces().size(); b++) {
				if (rootdata.getData().get(a).races.get(b).name.equals(Carrera)) {
					for (int c = 0; c < listaDeTiempos.size(); c++) {
						Date date;
						try {
							date = (Date) dateFormat.parse(rootdata.getData().get(a).races.get(b).getTime());

							if (date.equals(listaDeTiempos.get(c))) {
								rootdata.getData().get(a).races.get(b).setPosition(c + 1);
							}
						} catch (ParseException e) {

							e.printStackTrace();
						}
					}
				}
			}
		}
		return listaDeDatos;
	}

	public static List<Datum> DarPosicionATodos(List<Datum> listaDeDatos) {
		DarPosicion(listaDeDatos, "GP Barein");
		DarPosicion(listaDeDatos, "GP Portugal");
		DarPosicion(listaDeDatos, "GP Spain");
		DarPosicion(listaDeDatos, "GP Monaco");
		DarPosicion(listaDeDatos, "GP Italy");
		DarPosicion(listaDeDatos, "GP Singapore");
		DarPosicion(listaDeDatos, "GP Japan");
		DarPosicion(listaDeDatos, "GP USA");
		DarPosicion(listaDeDatos, "GP Australia");
		DarPosicion(listaDeDatos, "GP Abu Dabi");

		return listaDeDatos;

	}

}
