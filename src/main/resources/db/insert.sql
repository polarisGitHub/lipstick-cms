INSERT INTO brand (code,name,type) VALUES
('YSL','圣罗兰','lipstick'),
('DIOR','迪奥','lipstick');

INSERT INTO category (code,name,type) VALUES
('lipstick','唇膏','lipstick'),
('lip-vernis','唇釉','lipstick'),
('lip-oil','唇油','lipstick'),
('lip-kiss-brush','唇颊霜','lipstick'),
('lip-line','唇线','lipstick'),
('lip-color','唇彩','lipstick');

INSERT INTO brand_category_mapping (type,brand_code,category_code) VALUES
('lipstick','YSL','lipstick'),
('lipstick','YSL','lip-vernis'),
('lipstick','YSL','lip-oil'),
('lipstick','YSL','lip-kiss-brush'),
('lipstick','DIOR','lipstick'),
('lipstick','DIOR','lip-line'),
('lipstick','DIOR','lip-color');
