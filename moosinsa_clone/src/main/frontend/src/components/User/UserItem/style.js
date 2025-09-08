import styled from 'styled-components';

/**
 * UserItem 컴포넌트를 위한 스타일
 */
export const UserItemWrapper = styled.li`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  border-bottom: 1px solid #eee;

  &:last-child {
    border-bottom: none;
  }
`;

export const UserInfo = styled.div`
  font-size: 1rem;
  color: #333;
`;