import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

/**
 * 전역 스타일(Global Style) 컴포넌트
 * - 이 컴포넌트는 App.js 최상단에 위치하여 프로젝트 전체의 스타일 기반을 정의합니다.
 * - styled-reset을 사용하여 브라우저별 기본 스타일을 초기화합니다.
 * - 전체 프로젝트의 기본 폰트, 여백 등을 설정합니다.
 */
export const GlobalStyle = createGlobalStyle`
    ${reset} // 브라우저마다 다른 기본 스타일을 모두 제거(리셋)합니다.

    * {
        box-sizing: border-box; // 모든 요소의 크기 계산을 더 쉽게 만듭니다.
    }

    body {
        font-family: 'Helvetica', 'Arial', sans-serif; // 프로젝트 기본 글꼴 설정
        line-height: 1.5;
    }
`;