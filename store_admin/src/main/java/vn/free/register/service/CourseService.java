package vn.free.register.service;

import vn.free.register.entity.Course;
import vn.free.register.request.CourseSearch;
import vn.free.register.request.course.CourseNewRQ;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;

import java.util.List;

public interface CourseService {

    PageResponse getTopCourse(int amount);

    PageResponse getAll();

    PageResponse searchCourse(CourseSearch search);

    ActionRes createCourse(CourseNewRQ courseNewRQ);
    ActionRes updateCourse(CourseNewRQ courseNewRQ);
    ActionRes updateStatusCourse(CourseNewRQ courseNewRQ);
}
