package me.gachon.moosinsa_clone.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    /*
     * [리팩터링으로 인한 기능 비활성화] - 담당자: 동원
     * * 1. 변경 사유:
     * - 프로젝트 아키텍처를 '백엔드=API 서버' / '프론트엔드=화면 렌더링'으로 명확히 분리하기로 결정했습니다.
     * - 이에 따라 모든 페이지 라우팅(경로 제어)은 프론트엔드(React Router)가 담당하게 됩니다.
     * * 2. 기존 코드의 역할:
     * - '/' 경로로 접근 시, '/items/list'로 리다이렉트하는 서버 사이드 라우팅을 수행했습니다.
     * * 3. 조치:
     * - 위 아키텍처 원칙에 따라 이 컨트롤러의 기능은 더 이상 필요하지 않으므로, 팀원과의 논의를 위해 우선 주석 처리합니다.
     * - 향후 팀 전체의 아키텍처 동기화가 완료되면 파일 삭제를 검토할 예정입니다.
     */
    // @GetMapping("/")
    // public String home() {
    //     return "redirect:/items/list";
    // }
}