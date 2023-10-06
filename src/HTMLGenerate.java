// import com.aspose.html.HTMLDocument;
import java.util.ArrayList;

public class HTMLGenerate{

    ArrayList<String> listFilmesTitle = new ArrayList<String>();
    ArrayList<String> listFilmesUrlImage = new ArrayList<String>();
    ArrayList<String> listFilmesRating = new ArrayList<String>();
    ArrayList<String> listFilmesYear = new ArrayList<String>();

    public void getFilmesData(String title, String urlImage, String rating, String year) {
        listFilmesTitle.add(title);
        listFilmesUrlImage.add(urlImage);
        listFilmesRating.add(rating);
        listFilmesYear.add(year);
    }

    public String constructCards() {

        String htmlDataCards = "";
        
        htmlDataCards += """
                <div class=\"container-card\">
                """;
        
        for (int i = 0; i < listFilmesTitle.size(); i++) {
             htmlDataCards += """
                <div class=\"card\" style=\"background-image: """+ listFilmesUrlImage.get(i)+"""
                \";> 
                    <span class=\"card-title\"> """+ listFilmesTitle.get(i) +"""
                    <span class=\"card-year\"> """+ listFilmesYear.get(i) +"""
                    <span class=\"card-rating\"> """+ listFilmesRating.get(i) +"""
                </div>
                """;
        }

        htmlDataCards += """
                </div>
                """;


        return htmlDataCards;

    }

    // HTMLDocument document = new HTMLDocument(null);

}

// // import java.net.http.HttpHeaders;

// import com.a

// public class HTMLGenerate {

//     private Writer writer;
    
//     // HTMLGenerate(Writer writer) {
//     //     this.writer = writer;
//     // }

//     public void generare(Writer writer) {
//         System.out.println(this.writer);

//         // String divTemplate = """
//         //         <div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
//         //         	<h4 class=\"card-header\">%s</h4>
//         //         	<div class=\"card-body\">
//         //         		<img class=\"card-img\" src=\"%s\" alt=\"%s\">
//         //         		<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
//         //         	</div>
//         //         </div>
//         //         """;

//     }
// }
