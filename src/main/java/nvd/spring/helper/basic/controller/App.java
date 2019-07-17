/***************************************************************************
 * My own - All rights reserved.                *    
 **************************************************************************/
package nvd.spring.helper.basic.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *  Author : Nguyen Viet Duc
 *          Email:duc91.nvd@gmai.com
 * Jul 14, 2019
 */
@SpringBootApplication
@ComponentScan(value = "nvd.spring.helper.basic")
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
