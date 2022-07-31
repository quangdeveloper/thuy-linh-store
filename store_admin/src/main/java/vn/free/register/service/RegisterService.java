package vn.free.register.service;

import vn.free.register.request.RegisterRQ;
import vn.free.register.request.UserSearch;
import vn.free.register.request.register.RegisterRequest;
import vn.free.register.request.register.RegisterSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;

public interface RegisterService {

    PageResponse searchRegister(RegisterSearch search);
    ActionRes changeStatus(RegisterRequest registerRequest);
    Object getByID(Long id);

}
