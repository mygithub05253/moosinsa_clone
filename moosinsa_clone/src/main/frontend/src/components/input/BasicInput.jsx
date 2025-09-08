import React from 'react';
import { StyledInput } from './style';

/**
 * 프로젝트 전반에서 사용될 기본 입력창 컴포넌트
 * @param {object} props - { type, placeholder, value, onChange, ... }
 */
function BasicInput(props) {
  // <input> 대신 스타일이 적용된 <StyledInput>을 사용
  return <StyledInput {...props} />;
}

export default BasicInput;