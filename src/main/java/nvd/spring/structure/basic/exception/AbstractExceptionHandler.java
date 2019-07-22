/***************************************************************************
 * My own - All rights reserved. *
 **************************************************************************/
package nvd.spring.structure.basic.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * Author : Nguyen Viet Duc
 * Email:duc91.nvd@gmai.com Jul 14, 2019
 *
 * This config can deal with Custom Exception and logging the information of error's request:
 *  <li>ExpectException</li>
 *  <li>Exception</li>
 *  <li>Topup MVC Exception</li>
 *  <li>Any Exception cause to /error path</li>
 *
 * @apiNote Required implement this method below:
 *
 *     <li>RequestMapping("/error")</li>
 *     ResponseEntity<SystemError> handleError() {
 *         return new ResponseEntity<SystemError>(
 *                 (new SystemError.Builder(HttpStatus.NOT_FOUND.value()))
 *                         .fromPath(this.servletRequest.getRequestURI())
 *                         .withMessage("Unknown exception").build(),
 *                         HttpStatus.NOT_FOUND)
 *     }
 */
public class AbstractExceptionHandler extends ResponseEntityExceptionHandler implements ErrorController {

  private Logger             log = LoggerFactory.getLogger(getClass());

  /**
   * Required to init on Sub Class
   */
  protected HttpServletRequest servletRequest;

  @ExceptionHandler(ExpectException.class)
  protected ResponseEntity<APIError> expectedException(ExpectException ex, WebRequest request) {
    logErrorRequest();
    
    return new ResponseEntity<APIError>(new ExpectException.UNKNOWN().build(ex.getDetail()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<APIError> exception(Exception ex, WebRequest request) {
    logErrorRequest();
    
    return new ResponseEntity<APIError>(
        new ExpectException.UNKNOWN().build(ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Deal with top exception in spring MVC
   */
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
                                                           HttpHeaders headers, HttpStatus status,
                                                           WebRequest request) {
    logErrorRequest();

    if (HttpStatus.INTERNAL_SERVER_ERROR.value() == status.value()) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
    }

    return new ResponseEntity(
        new ExpectException.UNKNOWN().build(ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

  protected void logErrorRequest() {
    String ip = servletRequest.getHeader("X-Forwarded-For");
    if (StringUtils.isEmpty(ip)) ip = servletRequest.getRemoteAddr();

    log.warn("Error information -- From IP: {} -- Method: {} -- To Path -- {}",
            ip, servletRequest.getMethod(), servletRequest.getServletPath());
  }
}
