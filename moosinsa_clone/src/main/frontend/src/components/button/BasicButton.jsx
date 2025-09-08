import React from 'react';
import { StyledButton } from './style';

/**
 * 프로젝트 전반에서 사용될 기본 버튼 컴포넌트
 * @param {object} props - { children, onClick, type, ... }
 */
function BasicButton(props) {
  // 기존의 <button> 태그 대신, 스타일이 적용된 <StyledButton>을 사용
  // 넘겨받은 모든 props (onClick, type 등)는 그대로 적용
  return <StyledButton {...props}>{props.children}</StyledButton>;
}

export default BasicButton;