import { Link } from 'react-router-dom';
import styled from 'styled-components';

// Header.jsx에서 사용될 스타일 컴포넌트들
export const HeaderContainer = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  border-bottom: 1px solid #eee;
  background-color: white;
`;

export const Logo = styled.div`
  font-size: 1.5rem;
  font-weight: bold;
`;

export const Nav = styled.nav`
  display: flex;
  align-items: center;
  gap: 1.5rem; /* 링크 사이의 간격 */
`;

// react-router-dom의 Link 컴포넌트에 스타일을 적용합니다.
export const NavLink = styled(Link)`
  color: #333;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.2s ease-in-out;

  &:hover {
    color: #007bff;
  }
`;

export const UserInfoContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 1rem;
`;

export const WelcomeMessage = styled.span`
  font-weight: bold;
  color: #555;
`;

// Footer.jsx에서 사용될 스타일 컴포넌트
export const FooterContainer = styled.footer`
  text-align: center;
  padding: 2rem;
  background-color: #f8f9fa;
  color: #6c757d;
  border-top: 1px solid #eee;
`;