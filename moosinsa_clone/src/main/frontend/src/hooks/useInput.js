import { useState, useCallback } from 'react';

/**
 * input 상태 관리를 위한 커스텀 훅(Custom Hook)
 * @param {any} initialValue - input 태그의 초기값
 * @returns {[any, Function, Function]} - [현재 값, 이벤트 핸들러, 값 직접 설정 함수]
 * @description
 * - 반복적인 input 상태 관리 로직을 하나로 통합합니다.
 * - 이를 통해 LoginPage, SignupPage 등 폼(form)이 있는 컴포넌트의 코드를 훨씬 간결하게 만들 수 있습니다.
 */
export const useInput = (initialValue) => {
  // input의 현재 값을 저장하기 위한 state
  const [value, setValue] = useState(initialValue);

  // input의 onChange 이벤트가 발생했을 때, state 값을 업데이트하는 함수
  // useCallback을 사용: 컴포넌트가 불필요하게 리렌더링될 때마다 이 함수가 재생성되는 것을 방지하여 성능을 최적화합니다.
  const handler = useCallback((e) => {
    setValue(e.target.value);
  }, []);

  // 외부에서 값을 강제로 변경해야 할 경우를 위한 setter 함수
  const setter = useCallback((newValue) => {
    setValue(newValue);
  }, []);

  return [value, handler, setter];
};