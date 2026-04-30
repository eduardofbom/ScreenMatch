package br.com.eduardofbom.ScreenMatch;

import br.com.eduardofbom.ScreenMatch.infrastructure.model.ConvertData;
import br.com.eduardofbom.ScreenMatch.infrastructure.model.DataEpisode;
import br.com.eduardofbom.ScreenMatch.infrastructure.model.DataSeason;
import br.com.eduardofbom.ScreenMatch.infrastructure.model.DataSeries;
import br.com.eduardofbom.ScreenMatch.principal.Principal;
import br.com.eduardofbom.ScreenMatch.service.ApiConsumptionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.showMenu();

	}
}
