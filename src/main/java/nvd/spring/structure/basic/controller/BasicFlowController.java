/***************************************************************************
 * My own - All rights reserved.                *    
 **************************************************************************/
package nvd.spring.structure.basic.controller;

import nvd.spring.structure.basic.exception.ExpectException;
import nvd.spring.structure.basic.response.Payload;
import org.springframework.beans.factory.annotation.Autowired;

/**

 */


/**
 * Author : Nguyen Viet Duc
 * Email:duc91.nvd@gmai.com
 * Jul 14, 2019
 *
 * <h1> Abstract Controller
 * <br>
 * 
 * @author ducnv
 * @since 1.0
 * 
 * @param <IV> Input Validator
 * @param <BS> - Business Services - set of process need to be done 
 *  <ol>
 *    <li> Storage Validator
 *    <li> Business Services
 *    <li> O - Data output </li>
 *    <li> M - Metadata like 'total' or etc </li>
 *    <li> I - Input data </li>
 *  <ol>
 */
public class BasicFlowController<IV, BS> {
  
  protected @Autowired IV inputValidator;
  protected @Autowired BS business;

  /**
   * @param dataRequest
   * @param businessService
   * @param validator
   *
   * @return Payload
   * @throws ExpectException
   * 
   */
  protected  <O, I> Payload<O> perform(I dataRequest, InputValidator<I> validator, BusinessService<O, I> businessService) throws ExpectException {
    validator.validate(dataRequest);
    return businessService.invoke(dataRequest);
  }

  /**
   *   Stand for BusinessService class will do the Business Stuff
   * */
  private interface BusinessService<O, I> {
    Payload<O> invoke(I i) throws ExpectException;
  }

  /**
  *   Stand for InputValidator class will do the validations
  * */
  private interface InputValidator<I> {
    void validate(I i) throws ExpectException;
  }
}
