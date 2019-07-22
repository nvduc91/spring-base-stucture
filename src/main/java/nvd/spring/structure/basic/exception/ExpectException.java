/***************************************************************************
 * Author : Nguyen Viet Duc
 *          Email:duc91.nvd@gmai.com
 * Jul 14, 2019
 * 
 * My own - All rights reserved.
 **************************************************************************/
package nvd.spring.structure.basic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Represent for all exceptions occur while application running.
 *  <li> code - Internal code for customize 
 *  <li> message - Message for UI
 *  <li> detail - A detailed error for debugging or any development purpose
 *  
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ExpectException extends Exception {

  private static final long serialVersionUID = 1L;
  
  public ExpectException() {}
  
  public ExpectException(Integer code, String message, String detail) {
    this.code = code;
    this.message = message;
    this.detail = detail;
  }
  
  private Integer code;
  private String message;
  private String detail;

  public int getCode() { return code; }
  public void setCode(int code) { this.code = code; }

  public String getMessage() { return message; }
  public void setMessage(String userMessage) { this.message = userMessage; }

  public String getDetail() { return detail; }
  public void setDetail(String detail) { this.detail = detail; }
  
  @Override
  public String toString() {
    return String.format("{ \"code\" : %s, \"message\" : %s, \"detail\" : %s}", code, message, detail);
  }
  
  public static class UNKNOWN {
    public APIError build(String detail) {
      return new APIError.Builder(ExpectExceptionCode.UNKNOWN)
              .withMessage(ExpectExceptionCode.UNKNOWN_MESSAGE)
              .details(detail)
              .build();
    }
  }
}
