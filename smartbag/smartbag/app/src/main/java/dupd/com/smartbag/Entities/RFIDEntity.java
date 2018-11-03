package dupd.com.smartbag.Entities;

/**
 * Created by Dhaval on 03-11-2018.
 */

public class RFIDEntity {

    private String id;
    private String bookName;
    private Integer inBag;

    public RFIDEntity() {
    }

    public RFIDEntity(String id, String bookName, Integer inBag) {
        this.id = id;
        this.bookName = bookName;
        this.inBag = inBag;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getInBag() {
        return inBag;
    }

    public void setInBag(Integer inBag) {
        this.inBag = inBag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

