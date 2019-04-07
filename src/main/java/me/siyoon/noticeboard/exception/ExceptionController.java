package me.siyoon.noticeboard.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(DuplicateUserInfo.class)
    public String handleSignUpDuplicateException(DuplicateUserInfo e, Model model) {
        model.addAttribute("user", e.getUser());
        model.addAttribute("duplicationStr", e.getDuplicationStr());
        return "signUp";
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public String handleAuthenticationException(AuthenticationException e) {
        String noticeId = getNoticeIdFromErrMessage(e);
        return "redirect:/notices/" + noticeId + "?auth-error=true";
    }

    private String getNoticeIdFromErrMessage(AuthenticationException e) {
        String errMessage = e.getMessage();
        return errMessage.substring(errMessage.lastIndexOf(":") + 1);
    }

    @ExceptionHandler({SQLException.class, TemplateInputException.class})
    public void handleExpectedException(RuntimeException e) {
        log.warn(e.getClass().getName() + " : " + e.getMessage());
    }

    @ExceptionHandler(Error.class)
    public String handleError(Error error, Model model) {
        log.warn(error.getMessage());
        model.addAttribute("errMessage", error.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleUnexpectedExceptions(Exception e, Model model) {
        log.warn("예상치 못한 예외 발생 : " + e.getMessage() + "-" + e.getClass().getName());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.warn(stackTraceElement.toString());
        }
        model.addAttribute("errMessage", e.getMessage());
        return "error";
    }
}