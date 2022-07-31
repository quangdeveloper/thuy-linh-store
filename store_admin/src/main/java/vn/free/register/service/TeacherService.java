package vn.free.register.service;

import vn.free.register.request.teacher.TeacherNewRQ;
import vn.free.register.request.teacher.TeacherSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;

public interface TeacherService {

    PageResponse findAll();
    PageResponse getTop(Integer amount);
    PageResponse searchTeacher(TeacherSearch search);

    ActionRes createTeacher(TeacherNewRQ teacherNewRQ);
    ActionRes updateTeacher(TeacherNewRQ teacherNewRQ);
    ActionRes updateStatusTeacher(TeacherNewRQ teacherNewRQ);
}
