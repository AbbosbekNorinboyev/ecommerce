package uz.pdp.ecommerce.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.repository.AuthUserRepository;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class Utils {
    private final AuthUserRepository authUserRepository;

    public boolean checkEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }


    public String getCode() {
        Random random = new Random();
        int randomCode = 100000 + random.nextInt(90000);
        return String.valueOf(randomCode);
    }

//    public boolean checkCode(String email, String code) {
//        Optional<AuthUser> optionalAuthUser = authUserRepository.findByEmail(email);
//        if (optionalAuthUser.isPresent()) {
//            AuthUser authUser = optionalAuthUser.get();
//            return code != null && code.trim().isEmpty() && authUser.getCode().equals(code);
//        }
//        return false;
//    }

    public boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            return phoneNumber.matches("^\\+998\\d{9}$");
        }
        return false;
    }
}
