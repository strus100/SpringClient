package matuszewski.jstart.model;

public class Borrows {
    private Long borrowid;
    private boolean isborrow;

    public Borrows(Long borrowid, boolean isborrow) {
        this.borrowid = borrowid;
        this.isborrow = isborrow;
    }

    public Long getBorrowid() {
        return borrowid;
    }

    public void setBorrowid(Long borrowid) {
        this.borrowid = borrowid;
    }

    public boolean isIsborrow() {
        return isborrow;
    }

    public void setIsborrow(boolean isborrow) {
        this.isborrow = isborrow;
    }
}
