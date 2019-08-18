package win.sinno.common.util.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * win.sinno.es.model.EsModel
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/2/23
 */
@JsonIgnoreProperties(
    value = {"_id"},
    ignoreUnknown = true)
public class EsModel {

  private String _id;

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  @Override
  public String toString() {
    return "EsModel{" +
        "_id='" + _id + '\'' +
        '}';
  }
}
