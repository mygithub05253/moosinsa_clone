import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createUser } from '../../../api/userApi';
import { useInput } from '../../../hooks/useInput';
import BasicInput from '../../../components/input/BasicInput';
import BasicButton from '../../../components/button/BasicButton';
import { SignupPageContainer, Title, Form, ErrorMessage } from './style';

function SignupPage() {
  const navigate = useNavigate();

  // 모든 입력 필드를 useInput 훅으로 관리
  const [email, onChangeEmail] = useInput('');
  const [userId, onChangeUserId] = useInput(''); // [추가] 아이디 state
  const [password, onChangePassword] = useInput('');
  const [name, onChangeName] = useInput('');
  const [nickname, onChangeNickname] = useInput('');
  const [phoneNumber, onChangePhoneNumber] = useInput('');
  const [address, onChangeAddress] = useInput('');
  
  const [error ] = useState('');

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      // API로 보낼 데이터 객체에 백엔드 DTO와 동일한 필드를 모두 포함
      const userData = { 
        email, userId, password, name, nickname, phoneNumber, address 
      };
      await createUser(userData);
      alert('회원가입이 성공적으로 완료되었습니다!');
      navigate('/login');
    } catch (err) { /* ... */ }
  };

  return (
    <SignupPageContainer>
      <Title>회원가입</Title>
      <Form onSubmit={handleSignup}>
        <BasicInput type="email" placeholder="이메일" value={email} onChange={onChangeEmail} />
        {/* [추가] 아이디 입력창 */}
        <BasicInput type="text" placeholder="아이디" value={userId} onChange={onChangeUserId} />
        <BasicInput type="password" placeholder="비밀번호" value={password} onChange={onChangePassword} />
        <BasicInput type="text" placeholder="이름" value={name} onChange={onChangeName} />
        <BasicInput type="text" placeholder="닉네임" value={nickname} onChange={onChangeNickname} />
        <BasicInput type="tel" placeholder="전화번호" value={phoneNumber} onChange={onChangePhoneNumber} />
        <BasicInput type="text" placeholder="주소" value={address} onChange={onChangeAddress} />
        
        {error && <ErrorMessage>{error}</ErrorMessage>}
        <BasicButton type="submit">가입하기</BasicButton>
      </Form>
    </SignupPageContainer>
  );
}

export default SignupPage;