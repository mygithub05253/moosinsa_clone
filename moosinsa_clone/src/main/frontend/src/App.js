import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
// './global/style' 파일에서 GlobalStyle을 named export 방식으로 내보냈으므로,
// import 할 때 중괄호 {}를 사용하여 정확하게 'GlobalStyle'이라는 이름으로 가져옵니다.
import { GlobalStyle } from "./global/GlobalStyles";
import Layout from './pages/Layout/Layout';
import MainPage from './pages/Main/MainPage';
import SignupPage from './pages/Main/SignupPage';
import UserListPage from './pages/Main/UserListPage';
import LoginPage from './pages/Main/LoginPage';
import Items from './Items';
import ItemDetail from './components/ItemDetail';
import ItemCreate from './components/ItemCreate';
import ItemSizeCreate from "./components/ItemSizeCreate";
function App() {
  return (
    <BrowserRouter>
      {/* GlobalStyle 컴포넌트를 여기에 두면 애플리케이션 전체에 전역 스타일이 적용됩니다. */}
      <GlobalStyle />
      <Routes>
        {/* Layout 컴포넌트는 Header와 Footer를 포함하며, 그 안의 내용만 페이지에 따라 변경됩니다. */}
        <Route element={<Layout />}>
          {/* '/' 경로로 접속하면 MainPage 컴포넌트를 보여줍니다. */}
          <Route path="/" element={<MainPage />} />
          {/* '/signup' 경로로 접속하면 SignupPage 컴포넌트를 보여줍니다. */}
          <Route path="/signup" element={<SignupPage />} />
          {/* '/users' 경로로 접속하면 UserListPage 컴포넌트를 보여줍니다. */}
          <Route path="/users" element={<UserListPage />} />
          {/* '/login' 경로에 LoginPage 컴포넌트를 연결하는 Route를 추가합니다. */}
          <Route path="/login" element={<LoginPage />} />
          {/* 리스트 */}
          <Route path="/items" element={<Items />} />
          {/* 상세 */}
          <Route path="/items/:itemId" element={<ItemDetail />} />
          {/* 루트로 오면 /items로 */}
          {/* <Route path="/" element={<Navigate to="/items" replace />} /> */}
          <Route path="/items/create" element={<ItemCreate />} />
          <Route path="/items/create-sizes/:itemId" element={<ItemSizeCreate />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;