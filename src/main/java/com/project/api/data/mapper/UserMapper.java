package com.project.api.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.api.data.model.user.UserSearchRequest;
import com.project.common.model.User;

/**
 * 
 * http://www.chenjianjx.com/myblog/entry/how-to-let-mybatis-annotation
 ***/

@Mapper
public interface UserMapper {

//    @Insert("INSERT INTO project.user(first_name, last_name, email, password) VALUES(#{firstName}, #{lastName}, #{email}, #{password})")
//    @SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
    void createUser(User user);

    @Update("UPDATE project.user SET first_name = #{firstName}, last_name = #{lastName} WHERE id = #{id}")
    int updateUser(User user);
    
    @Update("UPDATE project.user SET facebook_id = if(#{facebookId} IS NULL,facebook_id, #{facebookId}), "
    		+ "vkontakte_id = if(#{vkontakteId} IS NULL,vkontakte_id, #{vkontakteId}), google_id = if(#{googleId} IS NULL,google_id, #{googleId}), picture_url = if(#{pictureUrl} IS NULL,picture_url, #{pictureUrl})  WHERE email = #{email}")
    int updateSocialUserByEmail(User user);

    @Select("SELECT id, username, first_name, last_name, email, password, facebook_id, google_id, vkontakte_id, picture_url FROM project.user u WHERE id = #{id}")
    @Results(value = {@Result(property = "roles", column = "user_id", javaType = List.class, many = @Many(select = "getRolesByUserId")) })
    User getUserById(Long id);

    @Select("SELECT COUNT(*) FROM project.user WHERE email = #{email}")
    boolean existsByEmail(String email);
    
    @Select("SELECT COUNT(*) FROM project.user WHERE email = #{emailOrUsername} or username = #{emailOrUsername}")
    boolean existsByEmailOrUsername(String emailOrUsername);


    @Select("SELECT r.name AS name FROM user_role ur LEFT JOIN project.role r ON ur.role_id = r.id WHERE  ur.user_id = #{userId}")
    List<String> getRolesByUserId(Long userId);
    
    @Select("SELECT a.name AS name FROM user_authority ua LEFT JOIN project.authority a ON ua.authority_id = a.id WHERE  ua.user_id = #{userId}")
    List<String> getAuthorityByUserId(Long userId);
    
    @Select("SELECT id, username, first_name, last_name, email, password, facebook_id, google_id, vkontakte_id, picture_url FROM project.user u WHERE email = #{emailOrUsername} or username = #{emailOrUsername}")
    @Results(value = {@Result(property = "roles", column = "user_id", javaType = List.class, many = @Many(select = "getRolesByUserId")) })
    User getUserByEmailOrUsername(String emailOrUsername);
    
//    @Select("SELECT u.id FROM project.user u")
    List<User> findAllUsers(UserSearchRequest userSearchRequest);
}
