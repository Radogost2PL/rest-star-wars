package dan.was.com.example.rest.star.wars.exceptions;

import java.util.Date;

public class BasicExceptionTemplate {

    private Date time;
    private String message;
    private String info;

    public BasicExceptionTemplate(Date time, String message, String info) {
        this.time = time;
        this.message = message;
        this.info = info;
    }

    public Date getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getInfo() {
        return info;
    }
}
