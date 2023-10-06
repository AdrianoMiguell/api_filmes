import com.aspose.html.HTMLDocument;
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

        String[] dataJSON = data.split("\"results\":[|],\"total_pages\"");

        String[] dataFilmes = dataJSON[0].split("},");

        // Variavel que guarda todos os dados do filme;
        ArrayList<String> filmesArray = new ArrayList<>();

        ArrayList<String> titleFilme = new ArrayList<>();
        ArrayList<String> popularityFilme = new ArrayList<>();
        ArrayList<String> overviewFilme = new ArrayList<>();
        ArrayList<String> averageFilme = new ArrayList<>();
        ArrayList<String> dateReleaseFilme = new ArrayList<>();
        ArrayList<String> pathImgFilme = new ArrayList<>();
        ArrayList<String> imgFilme = new ArrayList<>();

        HTMLGenerate htmlGenerate = new HTMLGenerate();

        // loop para dividir o json, coletando o array de dados dos filmes
        for (int i = 0; i < dataFilmes.length; i++) {
            String[] atributosJSON = dataFilmes[i].split(",");

            // loop para percorrer cada um dos dados dos filmes que estão no json
            for (int j = 0; j < atributosJSON.length; j++) {
                atributosJSON[j] = atributosJSON[j].replaceAll("\"", "");
                // Dividir o json para coletar os dados especificas, como nome, url, data do
                // filme
                if (!atributosJSON[j].contains("original_title") && atributosJSON[j].contains("title")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("title:", "");
                    titleFilme.add(atributosJSON[j]);
                } else if (atributosJSON[j].contains("popularity")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("popularity:", "");
                } else if (atributosJSON[j].contains("overview")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("overview:", "");
                } else if (atributosJSON[j].contains("vote_average")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("vote_average:", "");
                    averageFilme.add(atributosJSON[j]);
                } else if (atributosJSON[j].contains("release_date")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("release_date:", "");
                    dateReleaseFilme.add(atributosJSON[j]);
                } else if (atributosJSON[j].contains("backdrop_path")) {
                    atributosJSON[j] = atributosJSON[j].replaceAll("backdrop_path:", "");
                    imgFilme.add(atributosJSON[j]);
                    pathImgFilme.add(
                            "https://api.themoviedb.org/3/movie" + atributosJSON[j] + "/images?api_key=" + apiKey);
                }
            }

            Filmes filmes = new Filmes(titleFilme.get(i), pathImgFilme.get(i), averageFilme.get(i),
                    dateReleaseFilme.get(i));
            
                    
            filmesArray.add(i, filmes.getData());

            htmlGenerator.getFilmesData(titleFilme.get(i), pathImgFilme.get(i), averageFilme.get(i),
                    dateReleaseFilme.get(i));
        }

        // HTMLGenerate htmlGenerate = new HTMLGenerate();
        
        for (int i = 0; i < filmesArray.size(); i++) {
            System.out.println("\nDados do "+(i+1)+"° filme: \n" + filmesArray.get(i));
            
        }

        String textHtml = html.constructData();
        
        HTMLDocument document = new HTMLDocument();
        document.save("./page/index.html");

    }

}