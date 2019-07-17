/***************************************************************************
 * My own - All rights reserved. *
 **************************************************************************/
package nvd.spring.helper.basic.model.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * Author : Nguyen Viet Duc
 * Email:duc91.nvd@gmai.com Jul 14, 2019
 */
public class AbstractExceptionHandler extends ResponseEntityExceptionHandler {

  protected Logger             log = LoggerFactory.getLogger(getClass());

  protected HttpServletRequest servletRequest;

  @ExceptionHandler(ExpectException.class)
  protected ResponseEntity<ExpectException> ewayException(ExpectException ex, WebRequest request) {

    log.error(ex.getMessage());
    
    return new ResponseEntity<ExpectException>(new ExpectException.UNKNOWN().build(ex.getDetail()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Throwable.class)
  protected ResponseEntity<ExpectException> exception(Exception ex, WebRequest request) {
    log.error(ex.getMessage());
    
    return new ResponseEntity<ExpectException>(
        new ExpectException.UNKNOWN().build(ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
                                                           HttpHeaders headers, HttpStatus status,
                                                           WebRequest request) {

    log.error("Request from \t{}\ngot an error \t{}\n", servletRequest.getRemoteAddr(), ex.getMessage());

    if (HttpStatus.INTERNAL_SERVER_ERROR.value() == status.value()) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
    }

    return new ResponseEntity<Object>(
        new ExpectException.UNKNOWN().build(ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
