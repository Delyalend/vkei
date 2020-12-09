package com.vkei.exception;

import com.vkei.dto.UserRegistrationDto;
import com.vkei.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<AwesomeException> handleUserAlreadyException(Exception ex) {
        UserRegistrationDto dto = ((UserAlreadyExistsException) ex).getDto();
        User user = ((UserAlreadyExistsException) ex).getUser();

        Map<String, String> titleValueFields = new HashMap<>();
        if (dto.getLogin().equals(user.getLogin())) {
            titleValueFields.put("login", dto.getLogin());
        }

        if (dto.getMail().equals(user.getMail())) {
            titleValueFields.put("mail", dto.getMail());
        }

        String msgEx = "User with " + titleValueFields + " is already exists";
        return new ResponseEntity<AwesomeException>(
                new AwesomeException(msgEx),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchUserException.class})
    public ResponseEntity<AwesomeException> handleNoSuchUserException(Exception ex) {
        Long userId = ((NoSuchUserException) ex).getUserId();

        String msgEx = "User with id = " + userId + " not found";

        return new ResponseEntity<AwesomeException>(
                new AwesomeException(msgEx),
                HttpStatus.BAD_REQUEST
        );

    }

    @ExceptionHandler({SubjectAlreadyExistsException.class})
    public ResponseEntity<AwesomeException> handleSubjectAlreadyExistsException(Exception ex) {

        String titleSubject = ((SubjectAlreadyExistsException) ex).getTitleSubject();

        String msgEx = "Subject with title = " + titleSubject + " is already exists";

        return new ResponseEntity<AwesomeException>(
                new AwesomeException(msgEx),
                HttpStatus.BAD_REQUEST
        );

    }

    @ExceptionHandler({SubjectIsAlreadyExistsInCollectionException.class})
    public ResponseEntity<AwesomeException> handleSubjectIsAlreadyExistsInCollectionException(Exception ex) {
        String titleSubject = ((SubjectIsAlreadyExistsInCollectionException) ex).getTitleSubject();
        String msgEx = "Subject with title = " + titleSubject + " is already exists in collection";

        return new ResponseEntity<AwesomeException>(
                new AwesomeException(msgEx),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({SubjectExistsInOppositeCategoryException.class})
    public ResponseEntity<AwesomeException> handleSubjectExistsInOppositeCategoryException(Exception ex) {
        String titleSubject = ((SubjectExistsInOppositeCategoryException) ex).getTitleSubject();
        String msgEx = "Subject with title = " + titleSubject + " is already exists in opposite collection";

        return new ResponseEntity<AwesomeException>(
                new AwesomeException(msgEx),
                HttpStatus.BAD_REQUEST
        );
    }

    @Data
    @AllArgsConstructor
    private static class AwesomeException {
        private String message;
    }

}
