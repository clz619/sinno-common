package win.sinno.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * base model .
 * extends {@link Serializable}
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/3/28 09:45
 */
public interface IBaseModel extends Serializable {

    Long getId();

    void setId(Long id);

    Date getAddTs();

    void setAddTs(Date addTs);

    Date getUpdateTs();

    void setUpdateTs(Date updateTs);
}
