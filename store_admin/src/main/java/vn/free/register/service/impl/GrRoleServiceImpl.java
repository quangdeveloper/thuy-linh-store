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
import vn.free.register.entity.GroupRole;
import vn.free.register.entity.User;
import vn.free.register.repository.GroupRoleRepository;
import vn.free.register.repository.UserRepository;
import vn.free.register.request.UserSearch;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.GroupRoleService;
import vn.free.register.service.UserService;
import vn.free.register.util.SecurityUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class GrRoleServiceImpl implements GroupRoleService {

    private static String OBJECT = "nhóm quyền";
    @Autowired
    private GroupRoleRepository grRoleRepository;


    @Override
    public ResponseDTO getAllGrRole() {
        try {
            log.debug("Begin get all group role");

            List<GroupRole> list = grRoleRepository.findAll();

            final long total = list.size();
            log.debug("Get all group role successful. {}", list);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(list)
                    .total(total)
                    .build();
        } catch (Exception ex) {
            log.error("Get all group role ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }
}
