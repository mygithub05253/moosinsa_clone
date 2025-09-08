package me.gachon.moosinsa_clone.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    /**
     * API 및 정적 리소스를 제외한 모든 GET 요청을 React의 index.html로 포워딩합니다.
     * 이를 통해 React Router가 클라이언트 사이드 라우팅을 처리할 수 있게 됩니다.
     * * @GetMapping(value = {...})
     * - `value` 경로 패턴 설명:
     * - `/{path:[^\\.]*}`: URL 경로에 '.'(점)이 없는 경우에만 해당됩니다. (예: /login, /signup)
     * 정적 파일(main.js, main.css) 요청을 무시하기 위한 조건입니다.
     **/
    @GetMapping(value = {"/", "/{path:[^\\.]*}"})
    public String forward() {
        // "forward:" 접두사는 서버 내부적으로 요청을 전달하므로, 브라우저의 URL은 변경되지 않습니다.
        return "forward:/index.html";
    }
}