package vn.free.register.service;

import vn.free.register.request.CourseSearch;
import vn.free.register.request.RegisterRQ;
import vn.free.register.request.UserSearch;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;

public interface UserService {

    PageResponse searchUser(UserSearch search);

    ActionRes createUser(NewUserRQ newUserRQ);
    ActionRes updateUser(NewUserRQ newUserRQ);
    ActionRes updateStatusUser(NewUserRQ newUserRQ);
    Object getByID(Long userId);
}
