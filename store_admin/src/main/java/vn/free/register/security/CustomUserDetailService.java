package vn.free.register.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.constant.ResponseCode;
import vn.free.register.entity.User;
import vn.free.register.exception.GeneralException;
import vn.free.register.repository.UserRepository;

@Slf4j
@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Lấy ra 1 userDetail thông qua tài username khi đăng nhập của người dùng
     * <p>
     * hỏi anh xem tai sao yêu cầu lấy ra user detail nhưng hàm lại trả về userPrincipal
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new GeneralException(ResponseCode.LOGIN_FAIL);
        }
        return new UserPrincipal(user);
    }

    /**
     * Tìm user thông qua id cửa user
     */

    public UserDetails loadUserById(Long id){

        User user = userRepository.findByID(id);

        if (user == null){
            throw new GeneralException(ResponseCode.LOGIN_FAIL);
        }

        return new UserPrincipal(user);
    }
}
