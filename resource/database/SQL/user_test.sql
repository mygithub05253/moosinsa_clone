-- 스키마 선택(필요 시)
USE musinsaclone;

-- 1) 막고 있는 외래키 먼저 제거
ALTER TABLE `user`            DROP FOREIGN KEY `FKr5oabx7pr87o7drjpm4rer5kt`;
ALTER TABLE `like`            DROP FOREIGN KEY `FKbkuifxltr3tlpihfqxsnok0bh`;
ALTER TABLE `order`           DROP FOREIGN KEY `FKcpl0mjoeqhxvgeeeq5piwpd3i`;
ALTER TABLE `cart`            DROP FOREIGN KEY `FKl70asp4l4w0jmbm1tqyofho4o`;
ALTER TABLE `shipping_address`DROP FOREIGN KEY `FKqijab83dlbj00gytfswvh7ri9`;

-- 2) 컬럼 타입 변경
-- 등급: 정수 → 문자열
ALTER TABLE `grade`
  MODIFY `user_grade` VARCHAR(20) NOT NULL;  -- PK 유지됨

-- user 테이블: FK 컬럼과 아이디 컬럼 문자열로
ALTER TABLE `user`
  MODIFY `user_id`    VARCHAR(50) NOT NULL,
  MODIFY `user_name`  VARCHAR(50) NOT NULL,
  MODIFY `phone_number` VARCHAR(30),
  MODIFY `user_grade` VARCHAR(20) NOT NULL;

-- user_id 참조하는 테이블들 타입 통일
ALTER TABLE `like`             MODIFY `user_id` VARCHAR(50) NOT NULL;
ALTER TABLE `order`            MODIFY `user_id` VARCHAR(50) NOT NULL;
ALTER TABLE `cart`             MODIFY `user_id` VARCHAR(50) NOT NULL;
ALTER TABLE `shipping_address` MODIFY `user_id` VARCHAR(50) NOT NULL;

-- 3) 외래키 재생성 (이름은 읽기 쉬운 이름으로 재지정)
ALTER TABLE `user`
  ADD CONSTRAINT `FK_user__grade`
  FOREIGN KEY (`user_grade`) REFERENCES `grade`(`user_grade`);

ALTER TABLE `like`
  ADD CONSTRAINT `FK_like__user`
  FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `order`
  ADD CONSTRAINT `FK_order__user`
  FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `cart`
  ADD CONSTRAINT `FK_cart__user`
  FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `shipping_address`
  ADD CONSTRAINT `FK_shipping_address__user`
  FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

-- 1) 등급 데이터 삽입
INSERT INTO grade (user_grade, grade_name, grade_discount, grade_benefit)
VALUES
  ('BRONZE', '브론즈', 0,  '신규 가입 기본 등급'),
  ('SILVER', '실버',   3,  '3% 상시 할인'),
  ('GOLD',   '골드',   5,  '5% 상시 할인 + 생일 쿠폰'),
  ('VIP',    'VIP',    10, '10% 상시 할인 + 무료배송');

-- 2) 사용자 데이터 삽입
INSERT INTO `user` (user_id, user_pwd, user_name, phone_number, address, user_grade)
VALUES
  ('user001', 'pass001', '홍길동',  '010-1111-1111', '서울특별시 강남구 테헤란로 123', 'BRONZE'),
  ('user002', 'pass002', '김철수',  '010-2222-2222', '부산광역시 해운대구 센텀중앙로 45', 'SILVER'),
  ('user003', 'pass003', '이영희',  '010-3333-3333', '대구광역시 수성구 달구벌대로 999', 'GOLD'),
  ('user004', 'pass004', '박영수',  '010-4444-4444', '인천광역시 연수구 송도동 77',       'VIP'),
  ('user005', 'pass005', '최민수',  '010-5555-5555', '경기도 성남시 분당구 판교로 256',    'SILVER');
