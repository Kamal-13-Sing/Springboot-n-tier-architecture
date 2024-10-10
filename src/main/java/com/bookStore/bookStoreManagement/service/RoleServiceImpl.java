package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.helper.RoleHelper;
import com.bookStore.bookStoreManagement.model.Role;
import com.bookStore.bookStoreManagement.repository.RoleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements  RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean createRole(String jsonData) throws JsonProcessingException {

      Role role  =   RoleHelper.convertJsonToObject(jsonData);

      Role roleObj = new Role(
              role.getRoleId(),
              role.getName(),
              role.getDescription(),
              true
      );

      roleRepository.save(roleObj);
        return true;
    }
}
