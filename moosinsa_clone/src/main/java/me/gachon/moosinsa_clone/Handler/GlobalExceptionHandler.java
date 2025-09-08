package me.gachon.moosinsa_clone.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

// @RestControllerAdvice: 모든 @RestController에서 발생하는 예외를 이 클래스에서 처리하도록 지정합니다.
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Valid 어노테이션을 통한 유효성 검사 실패 시 발생하는 예외를 처리합니다.
     * @param ex MethodArgumentNotValidException 객체
     * @return HTTP 400 (Bad Request) 상태 코드와 함께 유효성 검사 실패 메시지를 담은 JSON 응답
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // 예외 결과에서 필드 이름과 에러 메시지를 추출하여 Map에 담습니다.
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        // 클라이언트에게 어떤 필드가 왜 잘못되었는지 명확하게 알려줍니다.
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * 로그인 실패 시 발생하는 LoginException을 처리합니다.
     * @param ex LoginException 객체
     * @return HTTP 401 (Unauthorized) 상태 코드와 함께 에러 메시지를 담은 JSON 응답
     */
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Map<String, String>> handleLoginException(LoginException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 그 외 처리하지 못한 모든 예외를 처리합니다.
     * @param ex Exception 객체
     * @return HTTP 500 (Internal Server Error) 상태 코드와 함께 일반적인 에러 메시지를 담은 JSON 응답
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "서버 내부 오류가 발생했습니다.");
        // 서버 로그에는 실제 에러를 출력하여 원인 파악을 용이하게 합니다.
        ex.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}