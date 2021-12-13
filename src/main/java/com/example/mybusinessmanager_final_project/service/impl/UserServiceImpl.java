package com.example.mybusinessmanager_final_project.service.impl;

import com.example.mybusinessmanager_final_project.model.binding.ChangeUserAccountSettingsBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.UserEntity;
import com.example.mybusinessmanager_final_project.model.entity.UserRoleEntity;
import com.example.mybusinessmanager_final_project.model.entity.enums.UserRoleEnum;
import com.example.mybusinessmanager_final_project.model.service.UserRegistrationServiceModel;
import com.example.mybusinessmanager_final_project.model.service.UserServiceModel;
import com.example.mybusinessmanager_final_project.model.view.UserDetailsView;
import com.example.mybusinessmanager_final_project.model.view.UserViewModel;
import com.example.mybusinessmanager_final_project.repository.UserRepository;
import com.example.mybusinessmanager_final_project.repository.UserRoleRepository;
import com.example.mybusinessmanager_final_project.service.UserService;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final MBMUserServiceImpl mbmUserService;
    private final ModelMapper modelMapper;


    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           MBMUserServiceImpl mbmUserService, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;

        this.mbmUserService = mbmUserService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("11111"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setEmail("admin@adminov")
                    .setActive(true).setCreated(Instant.now());


            admin.setRoles(List.of(adminRole, userRole));
            userRepository.save(admin);

            UserEntity pesho = new UserEntity();
            pesho
                    .setUsername("member")
                    .setPassword(passwordEncoder.encode("11111"))
                    .setFirstName("member")
                    .setLastName("memberov")
                    .setEmail("member@member")
                    .setActive(true).setCreated(Instant.now());

            pesho.setRoles(List.of(userRole));
            userRepository.save(pesho);
        }
    }

    private void initializeRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser.
                setUsername(userRegistrationServiceModel.getUsername()).
                setFirstName(userRegistrationServiceModel.getFirstName()).
                setLastName(userRegistrationServiceModel.getLastName()).
                setEmail(userRegistrationServiceModel.getEmail()).
                setActive(true).
                setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword())).
                setRoles(List.of(userRole));

        newUser = userRepository.save(newUser);

        UserDetails principal = mbmUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }

    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public Optional<UserViewModel> findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .map(userEntity -> modelMapper
                        .map(userEntity, UserViewModel.class));
    }

    @Override
    public void changePassword(String newPassword, String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with name %s not found!", username)));
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userEntity);
    }

    @Override
    public void changeUserRole(String role, Long id) {

        UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity userEntity = userRepository.findByUserId(id);

            if(role.equals("ADMIN")){
                userEntity.setRoles(List.of(adminRole, userRole));
            }else if(role.equals("USER")){
                userEntity.setRoles(List.of(userRole));
            }
            userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public List<UserServiceModel> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(this::mapToServiceModel).collect(Collectors.toList());
    }

    @Override
    public void editAccount(ChangeUserAccountSettingsBindingModel userSettingsModel) {
        UserEntity userEntity = userRepository
                .findByUsername(userSettingsModel.getUsername())
                .orElseThrow(() -> new ObjectNotFoundException("User with " + userSettingsModel.getUsername() + " not found!"));

        userEntity
                .setFirstName(userSettingsModel.getFirstName())
                .setLastName(userSettingsModel.getLastName())
                .setEmail(userSettingsModel.getEmail());

        userRepository.save(userEntity);

    }

    @Override
    public UserDetailsView findUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with name %s not found!", username)));
        return modelMapper.map(userEntity, UserDetailsView.class);

    }

    @Override
    public Optional<UserViewModel> findById(Long id) {
       return userRepository
                .findById(id)
                .map(userEntity -> modelMapper
                        .map(userEntity, UserViewModel.class));
    }

    private UserServiceModel mapToServiceModel(UserEntity entity) {
        UserServiceModel userServiceModel = modelMapper.map(entity, UserServiceModel.class);
        List<String> roles = entity.getRoles().stream().map(ent -> ent.getRole().name()).collect(Collectors.toList());
        userServiceModel.setRoles(roles);
        return userServiceModel;
    }
}
