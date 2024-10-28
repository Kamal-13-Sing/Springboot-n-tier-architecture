package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.helper.RoleHelper;
import com.bookStore.bookStoreManagement.helper.UserRoleHelper;
import com.bookStore.bookStoreManagement.model.Role;
import com.bookStore.bookStoreManagement.model.UserRole;
import com.bookStore.bookStoreManagement.repository.RoleRepository;
import com.bookStore.bookStoreManagement.repository.UserRoleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements  RoleService{

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;


    public RoleServiceImpl(RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
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

    @Override
    public boolean saveUserRole(String jsonData) throws JsonProcessingException {
        UserRole userRole = UserRoleHelper.convertJsonToObject(jsonData);
        userRoleRepository.save(userRole);

        return true;
    }

}
