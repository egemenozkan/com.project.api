package com.vantalii.api.data.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.project.common.model.User;

@Mapper
public interface UserAccountMapper {
	@Select("SELECT id, first_name, last_name, email, password FROM project.user u")
	Collection<User> findAll();
	@Select("SELECT id as user_id, username, first_name, last_name, email, count(*) FROM project.user u WHERE username = #{username}")
	@Results(value = {
			@Result(property="id", column="user_id"),
			@Result(property="roles", column="user_id", javaType=List.class, many=@Many(select="findRolesByUserId"))
	})
	User findByUsername(String username);
	@Select("SELECT id, username, first_name, last_name, email, password FROM project.user u WHERE id = #{id}")
	User findById(Long id);
	@Select("SELECT COUNT(*) FROM project.user WHERE username = #{username}")
	Integer countByUsername(String username);

	User save(User UserAccount);

	void deleteUserAccountById(Long id);
	
	@Select("SELECT r.name AS name FROM user_role ur LEFT JOIN project.role r ON ur.role_id = r.id WHERE  ur.user_id = #{userId}")
	List<String> findRolesByUserId(Long userId);
	
	@Select("SELECT count(*) FROM project.user u WHERE u.email = #{email}")
	boolean existsByEmail(String email);
}