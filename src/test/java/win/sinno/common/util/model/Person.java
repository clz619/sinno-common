package win.sinno.common.util.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * win.sinno.es.model.Person
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/2/23
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(
    value = {"_id"},
    ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Person extends EsModel {

  private String fname;

  private String lname;

  private int age;

  private List<String> mobiles;

  public Person(String fname, String lname, int age) {
    this.fname = fname;
    this.lname = lname;
    this.age = age;
  }

  public Person(String fname, String lname, int age, List<String> mobiles) {
    this.fname = fname;
    this.lname = lname;
    this.age = age;
    this.mobiles = mobiles;
  }

}
