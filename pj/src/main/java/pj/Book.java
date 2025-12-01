package pj;

public class Book {
    private int id;
    private String title;
    private String author;
    private int price;

    @Override
   public String toString() {
      return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + "]";
   }
    
    // Getter / Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

   public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}