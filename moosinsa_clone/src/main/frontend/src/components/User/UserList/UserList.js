import React from 'react';
import UserItem from '../UserItem/UserItem';
import { ListContainer } from './style';

function UserList({ users }) {
  if (!users || users.length === 0) {
    return <p>등록된 사용자가 없습니다.</p>;
  }

  return (
    <ListContainer>
      {users.map((user) => (
        <UserItem key={user.id} user={user} />
      ))}
    </ListContainer>
  );
}

export default UserList;