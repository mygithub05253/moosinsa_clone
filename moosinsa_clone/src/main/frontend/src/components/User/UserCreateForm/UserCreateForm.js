import React, { useState } from 'react';
import BasicInput from '../../input/BasicInput';
import BasicButton from '../../button/BasicButton';
import { FormContainer } from './style';

function UserCreateForm({ onSubmit }) {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    name: '',
    nickname: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault(); // 폼 제출 시 페이지가 새로고침되는 것을 방지
    // 간단한 유효성 검사 (실제로는 더 정교한 검사가 필요)
    if (!formData.email || !formData.password || !formData.name || !formData.nickname) {
      alert('모든 필드를 입력해주세요.');
      return;
    }
    onSubmit(formData);
  };

  return (
    <FormContainer onSubmit={handleSubmit}>
      <BasicInput
        type="email"
        name="email"
        placeholder="이메일"
        value={formData.email}
        onChange={handleChange}
      />
      <BasicInput
        type="password"
        name="password"
        placeholder="비밀번호"
        value={formData.password}
        onChange={handleChange}
      />
      <BasicInput
        type="text"
        name="name"
        placeholder="이름"
        value={formData.name}
        onChange={handleChange}
      />
      <BasicInput
        type="text"
        name="nickname"
        placeholder="닉네임"
        value={formData.nickname}
        onChange={handleChange}
      />
      <BasicButton type="submit">가입하기</BasicButton>
    </FormContainer>
  );
}

export default UserCreateForm;