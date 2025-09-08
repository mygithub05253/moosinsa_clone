import React, { createContext, useState, useContext, useEffect } from 'react';

// 1. Context 생성
const AuthContext = createContext(null);

// 2. AuthProvider 컴포넌트 생성
export const AuthProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(null);

  // 새로고침 시에도 로그인 상태 유지를 위해 sessionStorage에서 정보를 불러옵니다.
    useEffect(() => {
        try {
            const storedUser = sessionStorage.getItem('user');
            if (storedUser) {
                setCurrentUser(JSON.parse(storedUser));
            }
        } catch (e) {
            console.error("Failed to parse user from sessionStorage", e);
            sessionStorage.removeItem('user');
        }
    }, []);

  // 로그인 시, 사용자 정보를 state와 sessionStorage에 저장합니다.
    const login = (userData) => {
        setCurrentUser(userData);
        sessionStorage.setItem('user', JSON.stringify(userData));
    };

    // 로그아웃 시, state와 sessionStorage에서 사용자 정보를 제거합니다.
    const logout = () => {
        setCurrentUser(null);
        sessionStorage.removeItem('user');
    };

    const value = { currentUser, login, logout };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

// 다른 컴포넌트에서 Context를 쉽게 사용하기 위한 커스텀 훅
export const useAuth = () => {
    return useContext(AuthContext);
};