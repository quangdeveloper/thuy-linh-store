package vn.free.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.free.register.constant.ResponseCode;
import vn.free.register.entity.*;
import vn.free.register.repository.FamilyRepository;
import vn.free.register.repository.LanguageRepository;
import vn.free.register.repository.PersonNotifyRepository;
import vn.free.register.repository.RegisterRepository;
import vn.free.register.request.*;
import vn.free.register.request.register.RegisterRequest;
import vn.free.register.request.register.RegisterResponse;
import vn.free.register.request.register.RegisterSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;
import vn.free.register.service.RegisterService;
import vn.free.register.util.DateUtil;
import vn.free.register.util.SecurityUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private PersonNotifyRepository personNotifyRepository;

    @Override
    public PageResponse searchRegister(RegisterSearch search) {

        Pageable pageable = PageRequest.of(
                search.getPageIndex() - 1,
                search.getPageSize(),
                Sort.by("id").descending());

        Date fromDate = null;
        Date toDate = null;
        if (search.getFromDate() != null && !search.getFromDate().equalsIgnoreCase("")) {
            fromDate = DateUtil.convertStringToDate(search.getFromDate(), "yyyy-MM-dd");
        }
        if (search.getToDate() != null && !search.getToDate().equalsIgnoreCase("")) {
            toDate = DateUtil.convertStringToDate(search.getToDate(), "yyyy-MM-dd");
        }

        Page<Register> page = registerRepository.search(
                search.getKeyword(),
                search.getStatus(),
                fromDate,
                toDate,
                pageable);

        final long total = page.getTotalElements();

        return PageResponse.builder()
                .data(page.toList())
                .total(total)
                .build();
    }

    @Override
    public ActionRes changeStatus(RegisterRequest registerRequest) {
        try {
            String reason = registerRequest.getReasonCancel();
            if (registerRequest.getStatus() != 3) {
                reason = "";
            }
            registerRepository.updateStatus(
                    registerRequest.getId(),
                    registerRequest.getStatus(),
                    reason,
                    SecurityUtil.getCurrentUsernameId(),
                    new Date());

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
    public Object getByID(Long id) {

        try {
            Register register = registerRepository.findById(id).orElseThrow(
                    () -> new Exception()
            );

            List<Family> familyList = new ArrayList<>();
            List<Language> languageList = new ArrayList<>();
            PersonNotify personNotify = new PersonNotify();

            familyList = familyRepository.findByRegisterId(id);
            languageList = languageRepository.findByRegisterId(id);
            personNotify = personNotifyRepository.findByRegisterId(id);

            RegisterResponse registerResponse = RegisterResponse.builder()
                    .familyList(familyList)
                    .languageList(languageList)
                    .personNotify(personNotify)
                    .build();
            return registerResponse;


        } catch (Exception exception) {

            log.error("System error. JPA update status user fail ");
            return null;
        }
    }
}
