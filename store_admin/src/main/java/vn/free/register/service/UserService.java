package vn.free.register.service;

import vn.free.register.request.user.UserSearch;
import vn.free.register.request.user.UserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;

public interface UserService {

    ResponseDTO searchUser(UserSearch search);
    ResponseDTO getUserById(UserRQ userRQ);
    ActionRes createUser(UserRQ userRQ);
    ActionRes updateUser(UserRQ userRQ);
    ActionRes updateStatusUser(UserRQ userRQ);
}
