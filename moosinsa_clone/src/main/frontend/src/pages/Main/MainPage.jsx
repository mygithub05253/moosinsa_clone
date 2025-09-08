import React from 'react';
import styled from 'styled-components';

const MainWrapper = styled.div`
  text-align: center;
  padding: 50px 20px;
`;

/**
 * @설명: 앱의 첫 화면에 해당하는 컴포넌트입니다.
 * 메인 타이틀과 간단한 설명을 표시합니다.
 */
function MainPage() {
  return (
    <MainWrapper>
      <h2>환영합니다!</h2>
      <p>무신사 클론 프로젝트에 오신 것을 환영합니다. 상단의 메뉴를 통해 로그인, 회원가입, 사용자 목록을 확인하세요.</p>
    </MainWrapper>
  );
}

export default MainPage;