import styled from 'styled-components';

/**
 * BasicButton 컴포넌트를 위한 스타일
 * - Styled Components를 사용하여 버튼의 기본 디자인을 정의합니다.
 */
export const StyledButton = styled.button`
  background-color: #007bff;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: bold;

  /* 마우스를 올렸을 때의 스타일 */
  &:hover {
    background-color: #0056b3;
  }

  /* 클릭이 비활성화되었을 때의 스타일 */
  &:disabled {
    background-color: #cccccc;
    cursor: not-allowed;
  }
`;