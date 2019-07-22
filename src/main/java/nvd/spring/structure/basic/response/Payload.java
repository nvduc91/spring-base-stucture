/***************************************************************************
 * My own - All rights reserved.                *    
 **************************************************************************/
package nvd.spring.structure.basic.response;

/**
 *  Author : Nguyen Viet Duc
 *          Email:duc91.nvd@gmai.com
 * Jul 14, 2019
 *
 */
public class Payload<O> {

  public Payload(){}
  public Payload(O data){
    this.data = data;
  }
  
  public Payload(O data, Object meta, int stt){
      this.data = data;
      this.meta = meta;
      this.status_code = stt;
  }

  private O data;
  public O getData() { return data; }
  public void setData(O data) { this.data = data; }

  private int status_code;
  public int getStatus_code() { return status_code; }
  public void setStatus_code(int status_code) { this.status_code = status_code; }
  
  private Object meta;
  public Object getMeta() { return meta; }
  public void setMeta(Object meta) { this.meta = meta; }
  
  @Override
  public String toString() {
    return String.format("{\"data\":%s, \"status_code\":%s, \"meta\"=%s}", data, status_code, meta);
  }
}
