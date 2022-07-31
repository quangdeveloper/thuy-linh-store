package vn.free.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.free.register.constant.ResponseCode;
import vn.free.register.entity.Course;
import vn.free.register.entity.Register;
import vn.free.register.entity.User;
import vn.free.register.repository.CourseRepository;
import vn.free.register.repository.UserRepository;
import vn.free.register.request.CourseSearch;
import vn.free.register.request.UserSearch;
import vn.free.register.request.register.RegisterResponse;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;
import vn.free.register.service.CourseService;
import vn.free.register.service.UserService;
import vn.free.register.util.SecurityUtil;

import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageResponse searchUser(UserSearch search) {

        Pageable pageable = PageRequest.of(
                search.getPageIndex() - 1,
                search.getPageSize(),
                Sort.by("id").descending());

        Page<User> page = userRepository.search(
                search.getKeyword(),
                search.getStatus(),
                pageable);

        final long total = page.getTotalElements();

        return PageResponse.builder()
                .data(page.toList())
                .total(total)
                .build();
    }

    @Override
    public ActionRes createUser(NewUserRQ newUserRQ) {

        try {
            User user = User.builder()
                    .username(newUserRQ.getUsername())
                    .password(passwordEncoder.encode(newUserRQ.getPassword()))
                    .fullName(newUserRQ.getFullName())
                    .email(newUserRQ.getEmail())
                    .phone(newUserRQ.getPhone())
                    .roleId(newUserRQ.getRoleId())
                    .status(1)
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .createdDate(new Date())
                    .build();
            
            userRepository.save(user);
            return ActionRes.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc())
                    .build();
        } catch (Exception exception) {

            log.error("System error. JPA create user fail ");
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc())
                    .build();
        }
    }

    @Override
    public ActionRes updateUser(NewUserRQ newUserRQ) {


        try {

            User userOld = userRepository.findByID(newUserRQ.getId());

            String pass = userOld.getPassword();
            if (newUserRQ.getPassword() != null && !newUserRQ.getPassword().equalsIgnoreCase("")){
                pass = passwordEncoder.encode(newUserRQ.getPassword());
            }

            User user = User.builder()
                    .id(newUserRQ.getId())
                    .username(newUserRQ.getUsername())
                    .password(pass)
                    .fullName(newUserRQ.getFullName())
                    .email(newUserRQ.getEmail())
                    .phone(newUserRQ.getPhone())
                    .roleId(newUserRQ.getRoleId())
                    .status(1)
                    .createdBy(userOld.getCreatedBy())
                    .createdDate(userOld.getCreatedDate())
                    .updatedBy(SecurityUtil.getCurrentUsernameId())
                    .updatedDate(new Date())
                    .build();

            userRepository.save(user);
            return ActionRes.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc())
                    .build();
        } catch (Exception exception) {

            log.error("System error. JPA update user fail ");
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc())
                    .build();
        }
    }

    @Override
    public ActionRes updateStatusUser(NewUserRQ newUserRQ) {
        try {

            userRepository.updateStatus(
                    newUserRQ.getId(),
                    newUserRQ.getStatus());

            return ActionRes.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc())
                    .build();

        } catch (Exception exception) {

            log.error("System error. JPA update status user fail ");
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc())
                    .build();
        }
    }

    @Override
    public Object getByID(Long userId) {

        return null;
    }
}
