import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {

        KeyApi apiKey = new KeyApi();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=" + apiKey))
                .GET().build();

        System.out.println(request.toString());

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String data = response.body();
        System.out.println(data);
        
        // KeyApi apiKey = new KeyApi();

        // HttpClient client = HttpClient.newHttpClient();
        // HttpRequest request = HttpRequest.newBuilder()
        //         .uri(new URI("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=" + apiKey))
        //         .GET().build();

        // System.out.println(request.toString());

        // HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // String data = response.body();
        // System.out.println(data);
    }
}