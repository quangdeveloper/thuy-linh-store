package vn.free.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import vn.free.register.request.user.UserSearch;
import vn.free.register.request.user.UserRQ;
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
    public ActionRes createUser(UserRQ userRQ) {

        try {
            log.debug("Begin create user: {}", userRQ);
            if (StringUtils.isEmpty(userRQ.getUsername())
                    || StringUtils.isEmpty(userRQ.getPassword())
                    || userRQ.getGroupRoleId() == null
                    || userRQ.getGroupRoleId() == 0
                    || StringUtils.isEmpty(userRQ.getFullName())
                    || StringUtils.isEmpty(userRQ.getMobile())
                    || StringUtils.isEmpty(userRQ.getAddress())
                    || StringUtils.isEmpty(userRQ.getDateBorn())
            ) {
                log.debug("Data request invalid.");
                return ActionRes.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }
            User userOld = userRepository.findByUsernameAll(userRQ.getUsername());
            if (userOld != null) {
                log.debug("UserName Existed. userName: {} ", userRQ.getUsername());
                return ActionRes.builder()
                        .code(ResponseCode.EXISTS.getCode())
                        .message(ResponseCode.EXISTS.getDesc(OBJECT))
                        .build();
            }

            User user = User.builder()
                    .username(userRQ.getUsername())
                    .password(passwordEncoder.encode(userRQ.getPassword()))
                    .groupRoleId(userRQ.getGroupRoleId())
                    .fullName(userRQ.getFullName())
                    .email(userRQ.getEmail())
                    .mobile(userRQ.getMobile())
                    .address(userRQ.getAddress())
                    .dateBorn(userRQ.getDateBorn())
                    .status(StatusCode.ACTIVE.getCode())
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .createdDate(new Date())
                    .build();

            userRepository.save(user);
            log.debug("Create user successful.");
            return ActionRes.builder()
                    .code(ResponseCode.CREATE_SUCCESS.getCode())
                    .message(ResponseCode.CREATE_SUCCESS.getDesc(OBJECT))
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
    public ActionRes updateUser(UserRQ userRQ) {

        try {
            log.debug("Begin update user: {}", userRQ);
            User userOld = userRepository.findByID(userRQ.getId());
            if (userOld == null) {
                log.debug("UserName not exists. userId: {} ", userRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }

            User user = User.builder()
                    .id(userRQ.getId())
                    .username(userOld.getUsername())
                    .password(userOld.getPassword())
                    .groupRoleId(userRQ.getGroupRoleId())
                    .fullName(userRQ.getFullName())
                    .email(userRQ.getEmail())
                    .mobile(userRQ.getMobile())
                    .address(userRQ.getAddress())
                    .dateBorn(userRQ.getDateBorn())
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
    public ActionRes updateStatusUser(UserRQ userRQ) {
        try {
            log.debug("Begin update status user: {}", userRQ);
            User user = userRepository.findByID(userRQ.getId());
            if (user == null) {
                log.debug("User not Exists. userId: {} ", userRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }

            userRepository.updateStatus(userRQ.getId(), userRQ.getStatus());
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
