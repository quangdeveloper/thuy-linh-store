package vn.free.register.service;

import vn.free.register.request.UserSearch;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;

public interface UserService {

    ResponseDTO searchUser(UserSearch search);
    ActionRes createUser(NewUserRQ newUserRQ);
    ActionRes updateUser(NewUserRQ newUserRQ);
    ActionRes updateStatusUser(NewUserRQ newUserRQ);
}
