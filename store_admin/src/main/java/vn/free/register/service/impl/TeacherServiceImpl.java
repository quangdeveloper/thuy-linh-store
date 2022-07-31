package vn.free.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.free.register.constant.ResponseCode;
import vn.free.register.entity.Course;
import vn.free.register.entity.Teacher;
import vn.free.register.entity.User;
import vn.free.register.repository.CourseRepository;
import vn.free.register.repository.TeacherRepository;
import vn.free.register.request.CourseSearch;
import vn.free.register.request.teacher.TeacherNewRQ;
import vn.free.register.request.teacher.TeacherSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;
import vn.free.register.service.CourseService;
import vn.free.register.service.TeacherService;
import vn.free.register.util.DateUtil;
import vn.free.register.util.SecurityUtil;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public PageResponse findAll() {
        List<Teacher> list = teacherRepository.findAll();
        return PageResponse.builder()
                .data(list)
                .total(list.size())
                .build();
    }

    @Override
    public PageResponse getTop(Integer amount) {

        Pageable pageable = PageRequest.of(
                0,
                amount,
                Sort.by("id").descending());

        Page<Teacher> page = teacherRepository.getTop(pageable);
        final long total = page.getTotalElements();

        return PageResponse.builder()
                .data(page.toList())
                .total(total)
                .build();
    }

    @Override
    public PageResponse searchTeacher(TeacherSearch search) {

        Pageable pageable = PageRequest.of(
                search.getPageIndex() - 1,
                search.getPageSize(),
                Sort.by("id").descending());

        Page<Teacher> page = teacherRepository.search(
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
    public ActionRes createTeacher(TeacherNewRQ teacherNewRQ) {
        try {
            Teacher teacher = Teacher.builder()
                    .fullName(teacherNewRQ.getFullName())
                    .dateOfBirth(DateUtil.convertStringToDate(teacherNewRQ.getDateOfBirth(), "yyyy-MM-dd"))
                    .address(teacherNewRQ.getAddress())
                    .phone(teacherNewRQ.getPhone())
                    .email(teacherNewRQ.getEmail())
                    .fax(teacherNewRQ.getFax())
                    .academicDegree(teacherNewRQ.getAcademicDegree())
                    .shortDesc(teacherNewRQ.getShortDesc())
                    .gender(teacherNewRQ.getGender())
                    .imageUrl(teacherNewRQ.getImageUrl())
                    .specialize(1)
                    .status(1)
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .createdDate(new Date())
                    .build();

            teacherRepository.save(teacher);
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
    public ActionRes updateTeacher(TeacherNewRQ teacherNewRQ) {
        try {

            Teacher teacherOld = teacherRepository.findById(teacherNewRQ.getId()).orElseThrow(
                    ()-> new Exception()
            );

            Teacher teacher = Teacher.builder()
                    .id(teacherNewRQ.getId())
                    .fullName(teacherNewRQ.getFullName())
                    .dateOfBirth(DateUtil.convertStringToDate(teacherNewRQ.getDateOfBirth(), "yyyy-MM-dd"))
                    .address(teacherNewRQ.getAddress())
                    .phone(teacherNewRQ.getPhone())
                    .email(teacherNewRQ.getEmail())
                    .fax(teacherNewRQ.getFax())
                    .academicDegree(teacherNewRQ.getAcademicDegree())
                    .shortDesc(teacherNewRQ.getShortDesc())
                    .gender(teacherNewRQ.getGender())
                    .specialize(1)
                    .status(1)
                    .createdBy(teacherOld.getCreatedBy())
                    .createdDate(teacherOld.getCreatedDate())
                    .updatedBy(SecurityUtil.getCurrentUsernameId())
                    .updatedDate(new Date())
                    .build();
            if (StringUtils.isNotEmpty(teacherNewRQ.getImageUrl())){
                teacher.setImageUrl(teacherNewRQ.getImageUrl());
            }

            teacherRepository.save(teacher);
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
    public ActionRes updateStatusTeacher(TeacherNewRQ teacherNewRQ) {
        try {

            teacherRepository.updateStatus(
                    teacherNewRQ.getId(),
                    teacherNewRQ.getStatus(),
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
}
