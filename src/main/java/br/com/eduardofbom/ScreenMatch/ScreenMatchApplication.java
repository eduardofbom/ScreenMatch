package br.com.eduardofbom.ScreenMatch;

import br.com.eduardofbom.ScreenMatch.infrastructure.model.ConvertData;
import br.com.eduardofbom.ScreenMatch.infrastructure.model.DataSeries;
import br.com.eduardofbom.ScreenMatch.service.ApiConsumptionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URLEncoder;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var apiConsumptionService = new ApiConsumptionService();
		String omdbApiKey = "d4a220b9";

		String title = "Gilmore girls";
		String address = "http://www.omdbapi.com/?" +
				"t=" + URLEncoder.encode(title) +
				"&apikey=" + omdbApiKey;

		var jsonResponse = apiConsumptionService.consumeApi(address);
//		System.out.println(jsonResponse);

		ConvertData converter = new ConvertData();
		DataSeries data = converter.getData(jsonResponse, DataSeries.class);
		System.out.println(data);

	}
}
