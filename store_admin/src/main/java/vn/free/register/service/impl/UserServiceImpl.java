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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseDTO searchUser(UserSearch search) {

        try {
            Pageable pageable = PageRequest.of(
                    search.getPageIndex() - 1,
                    search.getPageSize(),
                    Sort.by("id").descending());

            Page<User> page = userRepository.search(
                    search.getKeyword(),
                    search.getStatus(),
                    pageable);

            final long total = page.getTotalElements();

            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc())
                    .data(page.toList())
                    .total(total)
                    .build();
        } catch (Exception ex) {
            log.error("Search user ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc())
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
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
                    .groupRoleId(newUserRQ.getGroupRoleId())
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

            log.error("Create user ...fail. ", exception);
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
            if (newUserRQ.getPassword() != null && !newUserRQ.getPassword().equalsIgnoreCase("")) {
                pass = passwordEncoder.encode(newUserRQ.getPassword());
            }

            User user = User.builder()
                    .id(newUserRQ.getId())
                    .username(newUserRQ.getUsername())
                    .password(pass)
                    .fullName(newUserRQ.getFullName())
                    .email(newUserRQ.getEmail())
                    .phone(newUserRQ.getPhone())
                    .groupRoleId(newUserRQ.getGroupRoleId())
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

            log.error("Update user ....fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc())
                    .build();
        }
    }

    @Override
    public ActionRes updateStatusUser(NewUserRQ newUserRQ) {
        try {
log.debug("");
            userRepository.updateStatus(
                    newUserRQ.getId(),
                    newUserRQ.getStatus());

            return ActionRes.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc())
                    .build();

        } catch (Exception exception) {

            log.error("Update status user ...fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc())
                    .build();
        }
    }

}
