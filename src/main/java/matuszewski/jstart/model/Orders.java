package matuszewski.jstart.model;

public class Orders {
    private Long orderid;
    private String author;
    private String title;

    public Orders() {
    }

    public Orders(Long orderid, String author, String title) {
        this.orderid = orderid;
        this.author = author;
        this.title = title;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
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

}
