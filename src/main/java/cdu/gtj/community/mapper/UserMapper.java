package cdu.gtj.community.mapper;

import cdu.gtj.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * 功能描述
 *
 * @author wshg
 * @date 2022/07/02  1:38 PM
 */
@Mapper
public interface UserMapper {

    @Insert("insert into User (name,account_id,token,gmt_create,gmt_modified) values (#{user.name},#{user.accountId},#{user.token},#{user.gmtCreate},#{user.gmtModified})")
   public void inster(@Param("user") User user);
}
