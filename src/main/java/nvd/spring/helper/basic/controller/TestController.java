/***************************************************************************
 * My own - All rights reserved.                *    
 **************************************************************************/
package nvd.spring.helper.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nvd.spring.helper.basic.model.exception.ExpectException;
import nvd.spring.helper.basic.model.response.DataResponse;

/**
 *  Author : Nguyen Viet Duc
 *          Email:duc91.nvd@gmai.com
 * Jul 14, 2019
 */
@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping("")
  public DataResponse<String> test(@RequestParam String str) throws ExpectException {
    return new DataResponse<String>(str);
  }
}
