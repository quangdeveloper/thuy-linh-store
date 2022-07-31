package vn.free.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.free.register.constant.ResponseCode;
import vn.free.register.entity.Course;
import vn.free.register.entity.User;
import vn.free.register.repository.CourseRepository;
import vn.free.register.request.CourseSearch;
import vn.free.register.request.course.CourseNewRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;
import vn.free.register.service.CourseService;
import vn.free.register.util.DateUtil;
import vn.free.register.util.SecurityUtil;

import java.util.Date;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public PageResponse getTopCourse(int amount) {
        Pageable pageable = PageRequest.of(
                0,
                amount,
                Sort.by("id").descending());

        Page<Course> page = courseRepository.getTop(pageable);
        final long total = page.getTotalElements();

        return PageResponse.builder()
                .data(page.toList())
                .total(total)
                .build();
    }

    @Override
    public PageResponse searchCourse(CourseSearch search) {

        Pageable pageable = PageRequest.of(
                search.getPageIndex() -1,
                search.getPageSize(),
                Sort.by("id").descending());

        Page<Course> page = courseRepository.search(
                search.getStatus(),
                search.getKeyword(),
                pageable);
        final long total = page.getTotalElements();

        return PageResponse.builder()
                .data(page.toList())
                .total(total)
                .build();
    }



    @Override
    public PageResponse getAll() {
        return PageResponse.builder()
                .data(courseRepository.findAll())
                .total(1000)
                .build();
    }


    @Override
    public ActionRes createCourse(CourseNewRQ courseNewRQ) {

        try {

            Course course = Course.builder()
                    .code(courseNewRQ.getCode())
                    .name(courseNewRQ.getName())
                    .content(courseNewRQ.getContent())
                    .description(courseNewRQ.getDescription())
                    .startDate(DateUtil.convertStringToDate(courseNewRQ.getStartDate(), "yyyy-MM-dd"))
                    .endDate(DateUtil.convertStringToDate(courseNewRQ.getEndDate(), "yyyy-MM-dd"))
                    .amount(courseNewRQ.getAmount())
                    .age(courseNewRQ.getAge())
                    .price(courseNewRQ.getPrice())
                    .urlImage(courseNewRQ.getUrlImage())
                    .status(1)
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .createdDate(new Date())
                    .build();

            courseRepository.save(course);
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
    public ActionRes updateCourse(CourseNewRQ courseNewRQ) {
        try {

            Course courseOld = courseRepository.findById(courseNewRQ.getId()).orElseThrow(
                    () -> new Exception());

            Course course = Course.builder()
                    .id(courseNewRQ.getId())
                    .code(courseOld.getCode())
                    .name(courseNewRQ.getName())
                    .content(courseNewRQ.getContent())
                    .description(courseNewRQ.getDescription())
                    .startDate(DateUtil.convertStringToDate(courseNewRQ.getStartDate(), "yyyy-MM-dd"))
                    .endDate(DateUtil.convertStringToDate(courseNewRQ.getEndDate(), "yyyy-MM-dd"))
                    .age(courseNewRQ.getAge())
                    .price(courseNewRQ.getPrice())
                    .amount(courseNewRQ.getAmount())
                    .urlImage(courseNewRQ.getUrlImage())
                    .status(1)
                    .createdBy(courseOld.getCreatedBy())
                    .createdDate(courseOld.getCreatedDate())
                    .updatedBy(SecurityUtil.getCurrentUsernameId())
                    .updatedDate(new Date())
                    .build();

            courseRepository.save(course);
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
    public ActionRes updateStatusCourse(CourseNewRQ courseNewRQ) {
        try {

            courseRepository.updateStatus(
                    courseNewRQ.getId(),
                    courseNewRQ.getStatus());

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
