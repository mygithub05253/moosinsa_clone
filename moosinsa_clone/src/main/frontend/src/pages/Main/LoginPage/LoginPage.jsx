import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../../context/AuthContext';
import { login } from '../../../api/userApi';
import { useInput } from '../../../hooks/useInput';
import BasicInput from '../../../components/input/BasicInput';
import BasicButton from '../../../components/button/BasicButton';
import { LoginPageContainer, Title, Form, ErrorMessage } from './style';

function LoginPage() {
  const navigate = useNavigate();
  // AuthContext에서 전역 로그인 함수를 가져옵니다.
  const { login: authLogin } = useAuth();
  
  // useInput 훅으로 이메일과 비밀번호 상태를 관리합니다.
  const [userId, onChangeUserId] = useInput('');
  const [password, onChangePassword] = useInput('');
  
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      // 2. [오류 수정] API로 보낼 객체의 키를 'password'에서 'userPwd'로 변경합니다.
      //    이제 백엔드 UserLoginRequest DTO의 필드명과 정확히 일치하게 됩니다.
      const userData = await login({ userId, userPwd: password });
      
      authLogin(userData.user); // 응답 형식이 { user: ..., token: ... } 이므로 .user를 사용
      
      alert(`환영합니다, ${userData.user.userName}님!`);
      navigate('/');
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <LoginPageContainer>
      <Title>로그인</Title>
      <Form onSubmit={handleSubmit}>
        <BasicInput
          type="text" // type도 text로 변경
          placeholder="아이디" // placeholder도 아이디로 변경
          value={userId}
          onChange={onChangeUserId}
        />
        <BasicInput
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={onChangePassword}
        />
        {error && <ErrorMessage>{error}</ErrorMessage>}
        <BasicButton type="submit">로그인</BasicButton>
      </Form>
    </LoginPageContainer>
  );
}

export default LoginPage;