import React from 'react';
import { UserItemWrapper, UserInfo } from './style';

/**
 * 사용자 한 명의 정보를 표시하는 컴포넌트
 * @param {object} props - { user }
 */
function UserItem({ user }) {
  return (
    // <li> 대신 스타일이 적용된 <UserItemWrapper>를 사용
    <UserItemWrapper>
      <UserInfo><strong>ID:</strong> {user.id}</UserInfo>
      <UserInfo><strong>이름:</strong> {user.name}</UserInfo>
      <UserInfo><strong>닉네임:</strong> {user.nickname}</UserInfo>
      <UserInfo><strong>이메일:</strong> {user.email}</UserInfo>
    </UserItemWrapper>
  );
}

export default UserItem;