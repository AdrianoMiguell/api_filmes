public class Filmes {

    private String title;
    private String urlImage;
    private String rating;
    private String year;

    Filmes(String title, String urlImage, String rating, String year) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
        this.year = year;
    }
    
    public String getData() {
        return "[Title: " + this.title + ", url_image: " + this.urlImage + ", rating: " + this.rating + ", year: "
                + this.year + "]";
    }
}



    // public void setTitle(String title) {
    //     this.title = title;
    // }

    // public void setRating(String rating) {
    //     this.rating = rating;
    // }

    // public void setYear(String year) {
    //     this.year = year;
    // }

    // public void setUrlImage(String urlImage) {
    //     this.urlImage = urlImage;
    // }
