package com.userservice.exception;

import com.userservice.dto.generic.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<ResponseDto> invalidRequesstException(InvalidRequestException ex) {
        log.error("InvalidRequestException Error occurred {} ", ex);
        ResponseDto response = new ResponseDto(Boolean.FALSE, ex.getLocalizedMessage(),
                HttpServletResponse.SC_BAD_REQUEST, null);

        return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResponseDto> runtimeException(RuntimeException ex) {
        log.error("RuntimeException Error occurred {} ", ex);
        ResponseDto response = new ResponseDto(Boolean.FALSE, ex.getLocalizedMessage(),
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseDto> usernameNotFoundException(UsernameNotFoundException ex) {
        log.error("UsernameNotFoundException Error occurred {} ", ex);
        ResponseDto response = new ResponseDto(Boolean.FALSE, ex.getMessage(),
                HttpServletResponse.SC_BAD_REQUEST, null);
        log.error("Error occurred {} ", ex);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> resourceNotFound(ResourceNotFoundException ex) {
        log.error("ResourceNotFoundException Error occurred {} ", ex);
        ResponseDto response = new ResponseDto(Boolean.FALSE, ex.getMessage(),
                HttpServletResponse.SC_BAD_REQUEST, null);
        log.error("Error occurred {} ", ex);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ResponseDto> resourceAlreadyExists(ResourceAlreadyExistsException ex) {
        log.error("ResourceAlreadyExists Error occurred {} ", ex);
        ResponseDto response = new ResponseDto(Boolean.FALSE, ex.getMessage(),
                HttpServletResponse.SC_BAD_REQUEST, null);
        log.error("Error occurred {} ", ex);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<String> resourceAlreadyExists(InvalidFileException ex) {
        log.error("InvalidFileException Error occurred {} ", ex);
        ResponseEntity<String> response = null;
        if (HttpStatus.PAYLOAD_TOO_LARGE.value() == ex.getErrorCode()) {
            response = new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE.getReasonPhrase(), HttpStatus.PAYLOAD_TOO_LARGE);
        } else if (HttpStatus.PRECONDITION_FAILED.value() == ex.getErrorCode()) {
            response = new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED.getReasonPhrase(), HttpStatus.PRECONDITION_FAILED);
        } else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ResponseDto> nullPointerExceptionException(NullPointerException ex) {
        log.error("NullPointerException Error occurred {} ", ex);
        ResponseDto response = new ResponseDto(Boolean.FALSE, "Something went wrong please try after sometime",
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);

        return new ResponseEntity<ResponseDto>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDto> exception(AccessDeniedException ex) {
        log.error("AccessDeniedException Error occurred {} ", ex);
        ResponseDto response = new ResponseDto(Boolean.FALSE, ex.getMessage(),
                HttpServletResponse.SC_FORBIDDEN, null);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}
