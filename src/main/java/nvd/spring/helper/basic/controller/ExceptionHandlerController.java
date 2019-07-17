/***************************************************************************
 * My own - All rights reserved.                *    
 **************************************************************************/
package nvd.spring.helper.basic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import nvd.spring.helper.basic.model.exception.AbstractExceptionHandler;
import nvd.spring.helper.basic.model.exception.ExpectException;

/**
 *  Author : Nguyen Viet Duc
 *          Email:duc91.nvd@gmai.com
 * Jul 14, 2019
 */
@RestController
@ControllerAdvice
public class ExceptionHandlerController extends AbstractExceptionHandler implements ErrorController {

  @Autowired
  ExceptionHandlerController(HttpServletRequest servlet) {
    this.servletRequest = servlet;
  }

  @Override
  public String getErrorPath() {
    return null;
  }

  @RequestMapping("/error")
  ResponseEntity<ExpectException> handleError() {
      return new ResponseEntity<ExpectException>(HttpStatus.NOT_FOUND);
  }
  
}
