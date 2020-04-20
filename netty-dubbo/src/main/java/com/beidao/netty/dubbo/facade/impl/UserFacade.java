package com.beidao.netty.dubbo.facade.impl;

import java.util.Date;

import com.beidao.netty.dubbo.facade.api.IUserFacade;
import com.beidao.netty.dubbo.facade.api.UserDTO;

/**
 * dubbo服务端实现类
 * 
 * @author beidao
 *
 */
public class UserFacade implements IUserFacade {

	public String getUserName(Long id) {

		return "I love you, " + id;
	}

	public UserDTO getUserDTO(UserDTO userDTO) {
		UserDTO resultUserDTO = new UserDTO();
		resultUserDTO.setId(userDTO.getId());
		resultUserDTO.setName("北国的候鸟");
		resultUserDTO.setCreatedDate(new Date());
		return resultUserDTO;
	}

}
