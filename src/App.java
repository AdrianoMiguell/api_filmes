import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

        KeyApi apiKey = new KeyApi();

        HttpClient client = HttpClient.newHttpClient();
        // pegar os filmes mais populares
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        "https://api.themoviedb.org/3/discover/movie?include_adult=true&include_video=true&language=en-US&page=1&sort_by=popularity.desc&api_key="
                                + apiKey))
                .GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String data = response.body();
        System.out.println(data);

        String[] dataJSON = data.split("\"results\":[|],\"total_pages\"");

        System.out.println("\n\n==========================\n\n");

        String[] dataFilmes = dataJSON[0].split("},");
        System.out.println(dataFilmes[0]);
        System.out.println("\n\n==========================\n\n");
        System.out.println(dataJSON[0]);

        ArrayList<String> titleFilme = new ArrayList<>();
        ArrayList<String> popularityFilme = new ArrayList<>();
        ArrayList<String> overviewFilme = new ArrayList<>();
        ArrayList<String> averageFilme = new ArrayList<>();
        ArrayList<String> dateReleaseFilme = new ArrayList<>();
        ArrayList<String> pathImgFilme = new ArrayList<>();
        ArrayList<String> imgFilme = new ArrayList<>();

        System.out.println("\n\n============== Separação ============\n\n");

        for (int i = 0; i < dataFilmes.length; i++) {
            String[] atributosJSON = dataFilmes[i].split(",");
            System.out.println("\n==========================");
            System.out.println(dataFilmes[i]);
            for (int j = 0; j < atributosJSON.length; j++) {
                atributosJSON[j] = atributosJSON[j].replaceAll("\"", "");
                if (!atributosJSON[j].contains("original_title") && atributosJSON[j].contains("title")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("title:", "");
                    System.out.println(" Contem titulo de filme: " + atributosJSON[j]);
                    titleFilme.add(atributosJSON[j]);
                } else if (atributosJSON[j].contains("popularity")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("popularity:", "");
                    System.out.println(" Contem Popularidade de relevância de filme:  " + atributosJSON[j]);
                    popularityFilme.add(atributosJSON[j]);
                } else if (atributosJSON[j].contains("overview")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("overview:", "");
                    System.out.println(" Contem Descrição de relevância de filme:  " + atributosJSON[j]);
                    overviewFilme.add(atributosJSON[j]);
                } else if (atributosJSON[j].contains("vote_average")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("vote_average:", "");
                    System.out.println(" Contem voto de relevância de filme:  " + atributosJSON[j]);
                    averageFilme.add(atributosJSON[j]);
                } else if (atributosJSON[j].contains("release_date")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("release_date:", "");
                    System.out.println(" Contem Data de relevância de filme:  " + atributosJSON[j]);
                    dateReleaseFilme.add(atributosJSON[j]);
                } else if (atributosJSON[j].contains("backdrop_path")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("backdrop_path:", "");
                    System.out.println(" Contem o link da imagem do filme:  " + "https://api.themoviedb.org/3/movie"
                            + atributosJSON[j] + "/images?api_key=" + apiKey);
                    imgFilme.add(atributosJSON[j]);
                    pathImgFilme.add(
                            "https://api.themoviedb.org/3/movie" + atributosJSON[j] + "/images?api_key=" + apiKey);
                }
            }
            System.out.println("==========================");
        }

        System.out.println("\n\nDados dos titulos de filmes: \n" + titleFilme);

    }
}