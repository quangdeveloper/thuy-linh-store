package vn.free.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.free.register.constant.ResponseCode;
import vn.free.register.entity.ImageUpload;
import vn.free.register.entity.User;
import vn.free.register.repository.UploadRepository;
import vn.free.register.repository.UserRepository;
import vn.free.register.request.UserSearch;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.PageResponse;
import vn.free.register.service.UploadService;
import vn.free.register.service.UserService;
import vn.free.register.util.FileUtil;
import vn.free.register.util.SecurityUtil;

import java.util.Date;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadRepository uploadRepository;

    @Value("${image_folder.directory}")
    private String folder;

    @Override
    public ActionRes uploadTeacher(String type, MultipartFile fileUpload) {
        try {
            String imageFolder = folder + "\\teacher";
            String fileName = FileUtil.multiPartFileToFile(fileUpload, imageFolder);
            ImageUpload imageUpload = ImageUpload.builder()
                    .directoryFolder(imageFolder)
                    .originName(fileUpload.getOriginalFilename())
                    .name(fileName)
                    .typeObj(type)
                    .createdDate(new Date())
                    .build();
            uploadRepository.save(imageUpload);

            return ActionRes.builder()
                    .code("00")
                    .message(fileName)
                    .build();
        } catch (Exception exp) {

            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc())
                    .build();
        }
    }
}
