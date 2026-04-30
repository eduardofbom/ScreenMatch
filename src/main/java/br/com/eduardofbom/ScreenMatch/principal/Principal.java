package br.com.eduardofbom.ScreenMatch.principal;


import br.com.eduardofbom.ScreenMatch.infrastructure.model.ConvertData;
import br.com.eduardofbom.ScreenMatch.infrastructure.model.DataSeason;
import br.com.eduardofbom.ScreenMatch.infrastructure.model.DataSeries;
import br.com.eduardofbom.ScreenMatch.service.ApiConsumptionService;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final Scanner scanner = new Scanner(System.in);
    private final ApiConsumptionService apiConsumptionService = new ApiConsumptionService();
    private final ConvertData converter = new ConvertData();

    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=d4a220b9";

    public void showMenu() {
        System.out.println("Enter the title of the series you want to search:");
        String nameSeries = scanner.nextLine();

        String jsonResponse = apiConsumptionService.consumeApi(
                ADDRESS +
                        URLEncoder.encode(nameSeries) +
                        API_KEY);
        DataSeries dataSeries = converter.getData(jsonResponse, DataSeries.class);
        System.out.println(dataSeries + "\n");

        List<DataSeason> seasonsList = new ArrayList<>();
        for (int i = 1; i < dataSeries.quantSeasons(); i++) {
            var json = apiConsumptionService.consumeApi(
                    ADDRESS +
                            URLEncoder.encode(nameSeries) +
                            "&season=" + i +
                            API_KEY);
            seasonsList.add(converter.getData(json, DataSeason.class));
        }
        seasonsList.forEach(System.out::println);
        System.out.println();

        seasonsList.forEach(s -> s.episodesList().forEach(e -> System.out.println(e.title())));

//		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
//
//		List<Integer> temp = numbers.stream()
//				.filter(n -> n % 2 == 0)
//				.limit(5)
//				.map(n -> n*n)
//				.collect(CollectorstoList());
//		System.out.println(temp);
//		System.out.println(temp
//				.stream()
//				.reduce(0, Integer::sum));

    }

}
