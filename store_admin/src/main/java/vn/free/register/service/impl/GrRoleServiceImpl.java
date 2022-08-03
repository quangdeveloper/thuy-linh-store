package vn.free.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.free.register.constant.ResponseCode;
import vn.free.register.entity.GroupRole;
import vn.free.register.repository.GroupRoleRepository;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.GroupRoleService;

import java.util.Collections;
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
