
CREATE TABLE customer (
    ID VARCHAR2(5),
    PWD VARCHAR2(5)NOT NULL,
    NAME VARCHAR2(5),
    CONSTRAINT id_pk PRIMARY KEY(id)
);

CREATE TABLE PRODUCT (
    prod_no VARCHAR2(5),
    prod_name VARCHAR2(50) NOT NULL,
    prod_price NUMBER(6),
    prod_mf_dt DATE,
    prod_detail VARCHAR2(4000),
    CONSTRAINT prod_no_pk PRIMARY KEY(prod_no),
    CONSTRAINT pord_price_ck CHECK(prod_price >= 0 )
);


CREATE TABLE ORDER_INFO (
    order_no NUMBER,
    order_id VARCHAR2(5),
    order_dt DATE DEFAULT SYSDATE,  
    CONSTRAINT order_no PRIMARY KEY(order_no),
    CONSTRAINT order_id_fk FOREIGN KEY(order_id) REFERENCES customer(id)
);

CREATE TABLE ORDER_LINE (
    order_line_no NUMBER,
    order_prod_no VARCHAR2(5),
    order_quantity NUMBER(3),
    CONSTRAINT order_line_pk PRIMARY KEY(order_line_no, order_prod_no),
    CONSTRAINT order_line_no_fk FOREIGN KEY(order_line_no) REFERENCES order_info(order_no),
    CONSTRAINT order_prod_no_fk FOREIGN KEY(order_prod_no) REFERENCES product(prod_no)
);


INSERT INTO customer(id, pwd, name) VALUES('A', '', 'A');
INSERT INTO customer(id, pwd, name) VALUES('B', 'b', 'B');
INSERT INTO customer(id, pwd, name) VALUES('B', 'b11', 'B11');
INSERT INTO customer(id, pwd, name) VALUES('A', 'a', 'A');
INSERT INTO customer(id, pwd, name) VALUES('C', 'c', 'C');

INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0001', '�Ƹ޸�ī��', 1000);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0002', '���̽� �Ƹ޸�ī��', 1000);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0003', '��', 1500);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0004', '���̽���', 1500);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0005', 'īǪġ��', 1500);

INSERT INTO order_info(order_no, order_id) VALUES (1, 'A');
INSERT INTO order_info(order_no, order_id) VALUES (2, 'B');
INSERT INTO order_info(order_no, order_id) VALUES (3, 'C');

SELECT *
FROM order_info;

INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(1, 'C0001', 2);
 -- INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(0, 'C0001', 9);
 -- INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(1, NULL, 9);
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, 'C0001', 4);
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, 'C0002', 1);
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, 'C0005', 1);
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(3, 'C0002', 1);

SELECT *
FROM order_line;


-- �����ȵ� ��ǰ�� �����Ѵ�
UPDATE product SET prod_no='F0001', prod_name='�ٳ���' WHERE prod_no='C0003';

SELECT *
FROM product;

-- ������ ��ǰ�� �����Ѵ�
UPDATE product SET prod_no='X' WHERE prod_no='C0001'; -- ERROR
UPDATE product SET prod_price=prod_price+100 WHERE prod_no='C0001'; -- OK
-- �θ��� pk������ ����ϴ� �÷��� ����x 

-- check �������� �߰�
UPDATE product SET prod_price = -1 WHERE prod_no='F0001';

-- �����ȵ� ��ǰ ����
DELETE product WHERE prod_no='C0004';

-- ������ ��ǰ ����
DELETE product WHERE prod_no='C0001';

-- ������������ ��ųʸ��� �䰴ü : USER_CONSTRAINTS
SELECT *
FROm user_constraints
WHERE table_name='CUSTOMER';