package matuszewski.jstart.model;

public class Books {

    private Long bookid;
    private String author;
    private String title;

    public Books() {
    }

    public Books(Long bookid,String author, String title) {
        this.bookid = bookid;
        this.author = author;
        this.title = title;
    }

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookid=" + bookid +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

