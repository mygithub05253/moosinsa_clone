import axios from 'axios';

// Spring Boot 백엔드 API의 기본 URL
const USER_BASE_URL = "/users";

// =======================================================
// API 호출 로직을 작성하는 곳
// 모든 함수에 주석을 추가하여 어떤 역할을 하는지 설명합니다.
// =======================================================

/**
 * 새로운 사용자를 생성(회원가입)하는 함수 (POST /signup)
 * @param {object} userData - { email, password, name, nickname } 형태의 사용자 정보 객체
 */
export const createUser = async (userData) => {
  try {
    // API 요청 URL을 지시하신 대로 '/signup'으로 정확하게 수정했습니다.
    // 이 요청은 Spring Boot의 @PostMapping("/signup") 메소드로 전달됩니다.
    const response = await axios.post('/api/signup', userData);
    return response.data;
  } catch (error) {
    console.error("회원가입 API 호출 중 오류:", error);
    throw error;
  }
};

/**
 * 로그인을 요청하는 API 함수
 * @param {object} credentials - { email, password }
 * @returns {Promise<object>} - 성공 시 사용자 정보 객체
 */
export const login = async (credentials) => {
  try {
    // 1. 백엔드 컨트롤러(@PostMapping)에 정의된 '/login' 경로로 POST 요청을 보냅니다.
    //    credentials 객체가 요청 본문(body)에 JSON 형태로 담겨 전송됩니다.
    const response = await axios.post('/api/login', credentials);
    // 2. 성공 시, 응답 데이터(사용자 정보)를 반환합니다.
    return response.data;
  } catch (error) {
    // 3. 실패 시(401 Unauthorized 등), 백엔드의 GlobalExceptionHandler가 보낸
    //    에러 메시지를 추출하여 에러를 발생시킵니다.
    throw new Error(error.response.data.error || '로그인에 실패했습니다.');
  }
};

/**
 * @설명: 모든 사용자 목록 조회 GET 요청
 * @returns {Promise} - axios를 사용한 GET 요청의 Promise 객체
 */
export const getUsers = () => {
  // `USER_BASE_URL` 엔드포인트로 GET 요청을 보냅니다.
  return axios.get(USER_BASE_URL);
};

/**
 * 모든 사용자 목록을 조회하는 함수 (GET /users)
 */
export const getAllUsers = async () => {
  try {
    const response = await axios.get('/api/users');
    return response.data;
  } catch (error) {
    console.error("사용자 목록 조회 중 오류:", error);
    return [];
  }
};

/**
 * @설명: 특정 사용자 조회 GET 요청
 * @param {string} userId - 조회할 사용자의 ID
 * @returns {Promise} - axios를 사용한 GET 요청의 Promise 객체
 */
export const getUser = (userId) => {
  // `userId`를 URL 경로에 포함시켜 특정 사용자를 조회합니다.
  return axios.get(`${USER_BASE_URL}/${userId}`);
}

/**
 * @설명: 사용자 정보 수정 PUT 요청
 * @param {string} userId - 수정할 사용자의 ID
 * @param {object} updateData - 수정할 데이터 객체
 * @returns {Promise} - axios를 사용한 PUT 요청의 Promise 객체
 */
export const updateUser = (userId, updateData) => {
  // `userId`를 URL에 포함시키고, `updateData`를 JSON 형태로 서버에 전송합니다.
  return axios.put(`${USER_BASE_URL}/${userId}`, updateData);
}

/**
 * @설명: 사용자 탈퇴 DELETE 요청
 * @param {string} userId - 삭제할 사용자의 ID
 * @returns {Promise} - axios를 사용한 DELETE 요청의 Promise 객체
 */
export const deleteUser = (userId) => {
  // `userId`를 URL에 포함시켜 특정 사용자를 삭제합니다.
  return axios.delete(`${USER_BASE_URL}/${userId}`);
}