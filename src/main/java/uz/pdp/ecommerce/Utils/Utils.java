package uz.pdp.ecommerce.Utils;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.repository.AuthUserRepository;

import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Utils {
//    private final JavaMailSender javaMailSender;
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

//    public boolean sendCodeToMail(String code, String mail) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("xalqaroshartnomalaruz@gmail.com");
//        message.setText(mail);
//        message.setSubject("Restaurant - APP");
//        message.setText(code);
//        javaMailSender.send(message);
//        return true;
//    }

    public boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            return phoneNumber.matches("^\\+998\\d{9}$");
        }
        return false;
    }
}
