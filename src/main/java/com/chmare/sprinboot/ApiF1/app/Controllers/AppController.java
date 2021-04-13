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
	public static void ReadJson() {

		String json = "";

		String path = "C:\\Users/cmarinre/Downloads/data.json";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = "";
			while ((line = br.readLine()) != null) {
				json += line;
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
	public static List<Datum> GivePosition(List<Datum> dataList, String Race) {
		List<Date> timeList = new ArrayList<Date>();
		dataList = rootdata.getData();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.SSS");

		for (int a = 0; a < dataList.size(); a++) {
			for (int b = 0; b < dataList.get(a).races.size(); b++) {
				if (dataList.get(a).races.get(b).name.equals(Race)) {

					try {

						timeList.add((Date) dateFormat.parse(dataList.get(a).races.get(b).getTime()));
					} catch (ParseException e) {

						e.printStackTrace();
					}
				}
			}
		}

		Collections.sort(timeList);

		for (int a = 0; a < rootdata.getData().size(); a++) {
			for (int b = 0; b < rootdata.getData().get(a).getRaces().size(); b++) {
				if (rootdata.getData().get(a).races.get(b).name.equals(Race)) {
					for (int c = 0; c < timeList.size(); c++) {
						Date date;
						try {
							date = (Date) dateFormat.parse(rootdata.getData().get(a).races.get(b).getTime());

							if (date.equals(timeList.get(c))) {
								rootdata.getData().get(a).races.get(b).setPosition(c + 1);
							}
						} catch (ParseException e) {

							e.printStackTrace();
						}
					}
				}
			}
		}
		return dataList;
	}

	
	public static List<Datum> GivePositionAll(List<Datum> dataList) {
		
		
		for(int a = 0; a < dataList.get(0).races.size(); a++){
			
			GivePosition(dataList, dataList.get(0).getRaces().get(a).getName());			
		}
		
		
		return dataList;

	}

}
