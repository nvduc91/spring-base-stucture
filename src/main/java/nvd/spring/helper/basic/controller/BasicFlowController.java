/***************************************************************************
 * My own - All rights reserved.                *    
 **************************************************************************/
package nvd.spring.helper.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;

import nvd.spring.helper.basic.model.exception.ExpectException;
import nvd.spring.helper.basic.model.response.DataResponse;

/**
 *  Author : Nguyen Viet Duc
 *          Email:duc91.nvd@gmai.com
 * Jul 14, 2019
 */


/**
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
 *    <li> Services (Business Service/ External Service)
 *  <ol>
 *  @param <O> Data output
 *  @param <M> Extra Metadata like 'total' or etc
 *  @param <I> Data input
 */
public class BasicFlowController<IV, BS> {
  
  protected @Autowired IV inputValidator;
  protected @Autowired BS business;

  /**
   * <li> I: data input
   * <li> O: data output
   *  
   * @param dataRequest
   * @param businessService
   * @param validator
   * @return SystemResponse
   * @throws SystemException
   * 
   */
  public <O, I> DataResponse<O> perform(I dataRequest, InputValidator<I> validator, BussinessService<O, I> businessService) throws ExpectException {
    validator.validate(dataRequest);
    return businessService.invoke(dataRequest);
  }
  
  public interface BussinessService<O, I> {
    DataResponse<O> invoke(I i) throws ExpectException;
  }
  
  public interface InputValidator<I> {
    void validate(I i) throws ExpectException;
  }

}
