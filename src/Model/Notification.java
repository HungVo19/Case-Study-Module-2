package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Notification implements Serializable {
    public static Long INDEX = Long.valueOf(0);
    private Long id;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;

    public Notification(String title, String content, LocalDate startDate, LocalDate endDate) {
        this.id = Long.valueOf(++INDEX);
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus () {
        if((endDate.isAfter(LocalDate.now()) || endDate.isEqual(LocalDate.now())) && (startDate.isBefore(LocalDate.now())||startDate.isEqual(LocalDate.now()))) {
            return "Live";
        } else if (endDate.isBefore(LocalDate.now())) {
            return "Expired";
        } else if (startDate.isAfter(LocalDate.now())) {
            return "Not yet started";
        }
        return null;
    }
}
