package nvd.spring.structure.basic.exception;

import java.util.Date;

public class APIError {

    private Date timestamp;
    private Integer code;
    private String message;
    private String details;
    private String path;

    public Date getTimestamp() { return this.timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public Integer getCode() { return this.code; }
    public void setCode(Integer code) { this.code = code; }

    public String getMessage() { return this.message; }
    public void setMessage(String message) { this.message = message; }

    public String getDetails() { return this.details; }
    public void setDetails(String details) { this.details = details; }

    public String getPath() { return this.path; }
    public void setPath(String path) { this.path = path; }

    public static class Builder {
        private Integer code;
        private String message;
        private String details;
        private String path;

        public Builder(Integer code) {
            this.code = code;
        }

        public Builder() { }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder fromPath(String path) {
            this.path = path;
            return this;
        }

        public APIError build() {
            APIError error = new APIError();
            error.setCode(this.code);
            error.setMessage(this.message);
            error.setTimestamp(new Date());
            error.setDetails(this.details);
            error.setPath(this.path);
            return error;
        }
    }
}
