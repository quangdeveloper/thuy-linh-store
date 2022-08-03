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
import vn.free.register.constant.StatusCode;
import vn.free.register.entity.User;
import vn.free.register.repository.UserRepository;
import vn.free.register.request.UserSearch;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.UserService;
import vn.free.register.util.SecurityUtil;

import java.util.Collections;
import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static String OBJECT = "người dùng";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseDTO searchUser(UserSearch search) {

        try {
            log.debug("Begin search user: {}", search);
            Pageable pageable = PageRequest.of(
                    search.getPageIndex() - 1,
                    search.getPageSize(),
                    Sort.by("id").descending());

            Page<User> page = userRepository.search(
                    search.getKeyword(),
                    search.getStatus(),
                    pageable);

            final long total = page.getTotalElements();
            log.debug("Search user successful. {}", page);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(page.toList())
                    .total(total)
                    .build();
        } catch (Exception ex) {
            log.error("Search user ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }

    @Override
    public ActionRes createUser(NewUserRQ newUserRQ) {

        try {
            log.debug("Begin create user: {}", newUserRQ);
            User userOld = userRepository.findByUsernameAll(newUserRQ.getUsername());
            if (userOld != null) {
                log.debug("UserName Existed. userName: {} ", newUserRQ.getUsername());
                return ActionRes.builder()
                        .code(ResponseCode.EXISTS.getCode())
                        .message(ResponseCode.EXISTS.getDesc(OBJECT))
                        .build();
            }

            User user = User.builder()
                    .username(newUserRQ.getUsername())
                    .password(passwordEncoder.encode(newUserRQ.getPassword()))
                    .fullName(newUserRQ.getFullName())
                    .email(newUserRQ.getEmail())
                    .phone(newUserRQ.getPhone())
                    .groupRoleId(newUserRQ.getGroupRoleId())
                    .status(StatusCode.ACTIVE.getCode())
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .createdDate(new Date())
                    .build();

            userRepository.save(user);
            log.debug("Create user successful.");
            return ActionRes.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .build();
        } catch (Exception exception) {

            log.error("Create user ...fail.", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

    @Override
    public ActionRes updateUser(NewUserRQ newUserRQ) {

        try {
            log.debug("Begin update user: {}", newUserRQ);

            User userOld = userRepository.findByID(newUserRQ.getId());
            if (userOld == null) {
                log.debug("UserName not exists. userId: {} ", newUserRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }

            User user = User.builder()
                    .id(newUserRQ.getId())
                    .username(userOld.getUsername())
                    .password(userOld.getPassword())
                    .fullName(newUserRQ.getFullName())
                    .email(newUserRQ.getEmail())
                    .phone(newUserRQ.getPhone())
                    .groupRoleId(newUserRQ.getGroupRoleId())
                    .status(userOld.getStatus())
                    .createdBy(userOld.getCreatedBy())
                    .createdDate(userOld.getCreatedDate())
                    .updatedBy(SecurityUtil.getCurrentUsernameId())
                    .updatedDate(new Date())
                    .build();
            log.debug("User update: {} ", user);
            userRepository.save(user);
            log.debug("Update user successful");
            return ActionRes.builder()
                    .code(ResponseCode.UPDATE_SUCCESS.getCode())
                    .message(ResponseCode.UPDATE_SUCCESS.getDesc(OBJECT))
                    .build();

        } catch (Exception exception) {

            log.error("Update user ....fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

    @Override
    public ActionRes updateStatusUser(NewUserRQ newUserRQ) {
        try {
            log.debug("Begin update status user: {}", newUserRQ);
            User user = userRepository.findByID(newUserRQ.getId());
            if (user == null) {
                log.debug("User not Exists. userId: {} ", newUserRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }

            userRepository.updateStatus(newUserRQ.getId(), newUserRQ.getStatus());
            log.error("Update status user successful.");
            return ActionRes.builder()
                    .code(ResponseCode.UPDATE_SUCCESS.getCode())
                    .message(ResponseCode.UPDATE_SUCCESS.getDesc(OBJECT))
                    .build();

        } catch (Exception exception) {
            log.error("Update status user ...fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

}
