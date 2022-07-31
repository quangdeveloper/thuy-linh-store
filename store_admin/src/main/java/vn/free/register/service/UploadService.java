package vn.free.register.service;

import org.springframework.web.multipart.MultipartFile;
import vn.free.register.request.UserSearch;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;

public interface UploadService {

    ActionRes uploadTeacher(String type, MultipartFile fileUpload);
}
