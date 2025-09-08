import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import BasicButton from '../../components/button/BasicButton';
import {
  HeaderContainer,
  Logo,
  Nav,
  NavLink,
  UserInfoContainer,
  WelcomeMessage,
} from './style';

export function Header() {
  const { currentUser, logout } = useAuth(); // AuthContext에서 현재 유저 정보와 로그아웃 함수를 가져옵니다.
  const navigate = useNavigate();

  const handleLogout = () => {
    logout(); // 로그아웃 함수 호출
    alert('로그아웃 되었습니다.');
    navigate('/'); // 메인 페이지로 이동
  };

  return (
    <HeaderContainer>
      <Logo>
        <NavLink to="/">MOOSINSA</NavLink>
      </Logo>
      <Nav>
        {currentUser ? (
          // 로그인된 경우
          <UserInfoContainer>
            <WelcomeMessage>{currentUser.nickname}님, 환영합니다!</WelcomeMessage>
            <BasicButton onClick={handleLogout} size="small">로그아웃</BasicButton>
          </UserInfoContainer>
        ) : (
          // 로그아웃된 경우
          <>
            <NavLink to="/login">로그인</NavLink>
            <NavLink to="/signup">회원가입</NavLink>
          </>
        )}
        <NavLink to="/users">사용자 목록</NavLink>
      </Nav>
    </HeaderContainer>
  );
}