package br.com.eduardofbom.ScreenMatch.principal;


import br.com.eduardofbom.ScreenMatch.infrastructure.model.*;
import br.com.eduardofbom.ScreenMatch.service.OmdbApiConsumption;
import br.com.eduardofbom.ScreenMatch.service.ConvertData;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner scanner = new Scanner(System.in);
    private final OmdbApiConsumption omdbApiConsumption = new OmdbApiConsumption();
    private final ConvertData converter = new ConvertData();
    private final Dotenv dotenv = Dotenv.load();

    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=" + dotenv.get("OMDB_API_KEY");
    private List<DataSeries> searchedSeries = new ArrayList<>();

    public void showMenu() {

        String menu = """
                1 - Search series
                2 - Search episodes
                3 - List searched series
                
                0 - Go out
                """;

        Integer userOption = -1;
        while (userOption != 0) {
            System.out.println(menu);
            userOption = scanner.nextInt();
            scanner.nextLine();

            switch (userOption) {
                case 1:
                    searchSeriesWeb();
                    break;
                case 2:
                    searchEpisodePerSeries();
                    break;
                case 3:
                    showSearchedSeries();
                    break;
                case 0:
                    System.out.println("Going out...");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

    }

    private void searchSeriesWeb() {
        DataSeries data = getDataSeries();
        searchedSeries.add(data);
        System.out.println(data);
    }

    private DataSeries getDataSeries() {
        System.out.println("Enter the name of series to search");
        String nameSeries = scanner.nextLine();
        String json = omdbApiConsumption.consume(ADDRESS + URLEncoder.encode(nameSeries) + API_KEY);
        DataSeries data = converter.getData(json, DataSeries.class);
        return data;
    }

    private void searchEpisodePerSeries() {
        DataSeries dataSeries = getDataSeries();
        List<DataSeason> seasons = new ArrayList<>();

        for (int i = 1; i < Integer.parseInt(dataSeries.quantSeasons()); i++) {
            String json = omdbApiConsumption.consume(ADDRESS + URLEncoder.encode(dataSeries.title()) + "&season=" + 1 + API_KEY);
            DataSeason dataSeason = converter.getData(json, DataSeason.class);
            seasons.add(dataSeason);
        }
        seasons.forEach(System.out::println);
    }

    private void showSearchedSeries() {
        List<Series> seriesList;
        seriesList = this.searchedSeries.stream()
                .map(Series::new)
                .collect(Collectors.toList());
        seriesList.stream()
                .sorted(Comparator.comparing(Series::getGenre))
                .forEach(System.out::println);
    }

}
