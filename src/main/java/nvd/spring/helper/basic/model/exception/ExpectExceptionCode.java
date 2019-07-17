/***************************************************************************
 * My own - All rights reserved. *
 **************************************************************************/
package nvd.spring.helper.basic.model.exception;

/**
 * Author : Nguyen Viet Duc
 * Email:duc91.nvd@gmai.com Jul 14, 2019
 * 
 * Any custom Exception code will placed down here
 */
public enum ExpectExceptionCode {

  UNKOWN(999, "Exception may occur please try latter");

  ExpectExceptionCode(Integer code, String message) {
    this.value = code;
    this.message = message;
  }

  private Integer value;
  private String message;

  public Integer value() { return value; }
  public String message() { return this.message; }
}
