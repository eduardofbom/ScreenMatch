package br.com.eduardofbom.ScreenMatch.principal;


import br.com.eduardofbom.ScreenMatch.infrastructure.model.*;
import br.com.eduardofbom.ScreenMatch.service.ApiConsumptionService;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List<DataEpisode> episodesList = seasonsList.stream()
                .flatMap(s -> s.episodesList().stream())
                .collect(Collectors.toList());

//        System.out.println("\nTOP 5: Episodes");
//        episodesList.stream()
//                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Filter (N/A): " + e))
//                .sorted(Comparator.comparing(DataEpisode::rating).reversed())
//                .peek(e -> System.out.println("Sorted: " + e))
//                .limit(5)
//                .peek(e -> System.out.println("Limit (5): " + e + "\n"))
//                .forEach(System.out::println);
//        System.out.println();

        List<Episode> episodes = seasonsList.stream()
                .flatMap(s -> s.episodesList().stream()
                        .map(e -> new Episode(s.season(), e))
                ).collect(Collectors.toList());

        System.out.println("Enter a portion of the episode title:");
        String portionTitle = scanner.nextLine();
        Optional<Episode> episodeSought = episodes.stream()
                .filter(e -> e.getTitle().toUpperCase().contains(portionTitle.toUpperCase()))
                .findFirst();
        if (episodeSought.isPresent()) {
            System.out.println("Episode found: " + episodeSought.get());
        } else {
            System.out.println("Episode not found!");
        }

//        episodes.stream()
//                .sorted((s1,s2) -> s1.getRating().compareTo(s2.getRating()))
//        .forEach(System.out::println);
//
//        System.out.println("Which year of episodes would you like to watch?");
//        Integer yearSearch = scanner.nextInt();
//        scanner.nextLine();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodes.stream()
//                .filter(e -> e.getReleased() != null && e.getReleased().isAfter(LocalDate.of(yearSearch, 1, 1)))
//                .forEach(e -> System.out.printf("S%dE%d - %s - %s%n",
//                        e.getSeason(),
//                        e.getEpisode(),
//                        e.getTitle(),
//                        e.getReleased().format(formatter)));

        Map<Integer, Double>  ratingsPerSeason = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getRating)));
        System.out.println(ratingsPerSeason);

        DoubleSummaryStatistics statistics = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getRating));
        System.out.println("Quantity: " + statistics.getCount());
        System.out.println("Average: " + statistics.getAverage());
        System.out.println("Worst Episode: " + statistics.getMin());
        System.out.println("Best Episode: " + statistics.getMax());

    }

}
