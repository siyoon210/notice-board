package me.siyoon.noticeboard.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleConstraintViolationException(DataIntegrityViolationException e,Model model) {
        String duplicationStr = getDuplicationStr(e);
        model.addAttribute("duplicationStr", duplicationStr);
        return "signUp";
    }

    private String getDuplicationStr(DataIntegrityViolationException e) {
        String message = e.getCause().getCause().getMessage();
        return message.substring(29, message.indexOf("' for key '"));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleSQLException(SQLException e) {
        log.warn(e.getClass().getName() + " : " + e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"message\":" + e.getMessage() + "}");
    }

    @ExceptionHandler(Error.class)
    public String handleError(Error error, Model model) {
        log.warn(error.getMessage());
        model.addAttribute(error.getClass().getName(), error.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAllExceptions(Exception e) {
        log.warn("예상치 못한 예외 발생 : " + e.getMessage() + "-" + e.getClass().getName());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.warn(stackTraceElement.toString());
        }
        return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"message\":" + e.getMessage() + "}");
    }
}