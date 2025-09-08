import React from 'react';
import { Outlet } from 'react-router-dom';
import { Header } from "./Header";
import { Footer } from "./Footer";
import styled from 'styled-components';

const MainWrapper = styled.div`
  padding: 20px;
  min-height: calc(100vh - 120px); // 푸터가 화면 하단에 고정되도록 설정
`;

/**
 * @설명: 모든 페이지에서 공통적으로 보이는 헤더와 푸터를 포함하는 레이아웃 컴포넌트입니다.
 * `Outlet`은 `react-router-dom`에서 현재 URL에 해당하는 자식 라우트 컴포넌트를 렌더링하는 역할을 합니다.
 */
function Layout() {
  return (
    <>
      <Header />
      <MainWrapper>
        <Outlet />
      </MainWrapper>
      <Footer />
    </>
  );
}

export default Layout;