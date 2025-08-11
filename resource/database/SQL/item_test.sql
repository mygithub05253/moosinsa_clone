use musinsaclone;

-- category 데이터 삽입
INSERT INTO category (category_id, depth, name, parent_id)
VALUES
(1, 1, '상의', NULL),
(2, 1, '신발', NULL),
(3, 1, '하의', NULL);

-- item_color 데이터 삽입
INSERT INTO item_color (item_color_id, item_color_name, item_id)
VALUES
(1, '화이트', NULL),
(2, '블랙', NULL),
(3, '네이비', NULL),
(4, '그레이', NULL),
(5, '레드', NULL);

-- item_image 데이터 삽입
INSERT INTO item_image (item_image_id, item_image, item_id)
VALUES
(1, 'tshirt.jpg', NULL),
(2, 'nike_shoes.jpg', NULL),
(3, 'adidas_pants.jpg', NULL),
(4, 'nb_hoodie.jpg', NULL),
(5, 'converse.jpg', NULL);

-- item_size 데이터 삽입
INSERT INTO item_size (item_size_id, item_size_description, item_size_name, item_id)
VALUES
(1, '95~100', 'M', NULL),
(2, '255~260', '260mm', NULL),
(3, 'L 사이즈', 'L', NULL),
(4, 'XL 사이즈', 'XL', NULL),
(5, '265~270', '270mm', NULL);

-- item 데이터 삽입
INSERT INTO item (
    item_id, item_name, item_price, item_description, item_brand,
    item_like, item_rating, item_review_amount, item_status, item_stock,
    category_id, item_color_id, item_image_id, item_size_id
) VALUES
(1, '무신사 티셔츠', 19900, '무신사 기본 티셔츠', 'Musinsa', 10, 4.5, 3, '판매중', 100, 1, 1, 1, 1),
(2, '나이키 운동화', 89000, '러닝용 나이키 운동화', 'Nike', 25, 4.8, 15, '판매중', 50, 2, 2, 2, 2),
(3, '아디다스 트레이닝 팬츠', 59000, '편안한 트레이닝 팬츠', 'Adidas', 8, 4.2, 5, '품절', 0, 3, 3, 3, 3),
(4, '뉴발란스 후드티', 69000, '가을용 후드티', 'New Balance', 5, 4.0, 2, '판매중', 30, 1, 4, 4, 4),
(5, '컨버스 척테일러', 79000, '캐주얼 하이탑 스니커즈', 'Converse', 12, 4.6, 8, '판매중', 70, 2, 5, 5, 5);
