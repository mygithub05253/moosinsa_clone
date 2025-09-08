import React, { useState, useEffect } from 'react';
import { getAllUsers } from '../../../api/userApi';
import UserList from '../../../components/User/UserList';
import { UserListPageContainer, Title } from './style';

function UserListPage() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    // 페이지가 처음 렌더링될 때 사용자 목록을 가져옵니다.
    const fetchUsers = async () => {
      const userList = await getAllUsers();
      setUsers(userList);
    };

    fetchUsers();
  }, []); // []를 두 번째 인자로 전달하여 컴포넌트 마운트 시 한 번만 실행되도록 합니다.

  return (
    <UserListPageContainer>
      <Title>전체 사용자 목록</Title>
      <UserList users={users} />
    </UserListPageContainer>
  );
}

export default UserListPage;