CREATE TABLE MEMBER_TABLE(
        MEMBER_ID             VARCHAR2(15),
        MEMBER_NAME           VARCHAR2(30)            NOT NULL,
        MEMBER_EMAIL          VARCHAR2(50)            NOT NULL,
        MEMBER_PASSWORD       CHAR(60)               NOT NULL,        -- ��й�ȣ ��ȣȭ ó��.
        MEMBER_ZIPCODE        CHAR(5)                 NOT NULL, -- �����ȣ
        MEMBER_ADDR           VARCHAR2(100)            NOT NULL,
        MEMBER_DEADDR         VARCHAR2(100)            NOT NULL,
        MEMBER_PHONE          VARCHAR2(15)            NOT NULL,
        MEMBER_POINT          NUMBER DEFAULT 0        NOT NULL,
        MEMBER_LASTLOGIN      DATE DEFAULT SYSDATE    NOT NULL,
        MEMBER_DATESUB        DATE DEFAULT SYSDATE    NOT NULL,
        MEMBER_UPDATEDATE     DATE DEFAULT SYSDATE    NOT NULL
);

--���� ������Ű ����
ALTER TABLE MEMBER_TABLE
ADD CONSTRAINT PK_MEMBER_ID PRIMARY KEY (MEMBER_ID);

-- ���̵� üũ
SELECT MEMBER_ID FROM MEMBER_TABLE  WHERE MEMBER_ID = 'USER01';

-- ȸ������
INSERT INTO MEMBER_TABLE (MEMBER_ID, MEMBER_NAME, MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_ZIPCODE, MEMBER_ADDR, MEMBER_DEADDR, MEMBER_PHONE) 
VALUES (#{MEMBER_id}, #{MEMBER_name}, #{MEMBER_email}, 
#{MEMBER_password}, #{MEMBER_zipcode}, #{MEMBER_addr}, #{MEMBER_deaddr}, #{MEMBER_phone});

-- �α���
SELECT MEMBER_ID, MEMBER_NAME, MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_ZIPCODE, MEMBER_ADDR, MEMBER_DEADDR, MEMBER_PHONE, MEMBER_POINT, MEMBER_LASTLOGIN, MEMBER_DATESUB, MEMBER_UPDATEDATE
FROM MEMBER_TABLE WHERE MEMBER_ID = #{MEMBER_ID}

-- ȸ��������
UPDATE MEMBER_TABLE
SET 
    MEMBER_EMAIL = #{MEMBER_email},MEMBER_ZIPCODE = #{MEMBER_zipcode},MEMBER_ADDR = #{MEMBER_addr},MEMBER_DEADDR = #{MEMBER_deaddr},
    MEMBER_PHONE = #{MEMBER_phone},MEMBER_UPDATEDATE = sysdate
WHERE MEMBER_ID = #{MEMBER_id};

-- �α��� ���� �ð�
UPDATE MEMBER_TABLE
SET	MEMBER_LASTLOGIN = sysdate
WHERE MEMBER_ID = #{MEMBER_id};
-- ȸ��Ż��
DELETE MEMBER_TABLE
WHERE MEMBER_ID = #{MEMBER_id};


--���̵�׺�й�ȣã��
--1)���̵� ã��
SELECT MEMBER_ID FROM MEMBER_TABLE WHERE MEMBER_NAME = #{MEMBER_name} AND MEMBER_EMAIL = #{MEMBER_email}
--2)��й�ȣ ã��(���� ����)
SELECT MEMBER_EMAIL FROM MEMBER_TABLE WHERE MEMBER_ID = #{MEMBER_id} AND MEMBER_EMAIL = #{MEMBER_email}

-- ������ ���̺�
CREATE TABLE ADMIN_TABLE (
    ADMIN_ID    VARCHAR2(15)    PRIMARY KEY,
    ADMIN_PW    CHAR(60)    NOT NULL,
    ADMIN_VISIT_DATE    DATE   -- ������ �湮�ð�
);

-- ������ ��й�ȣ 1234
INSERT INTO ADMIN_TABLE VALUES('admin', '$2a$10$dQFCMr0udCI865eG6SoIcOaNr3Y/dgBX.R4qf6rX5KA3jciSnnNjG',sysdate);
-- ������ �α���
SELECT ADMIN_ID,ADMIN_PW, ADMIN_VISIT_DATE FROM ADMIN_TABLE WHERE ADMIN_ID = #{admin_id};
-- ������ �α���
UPDATE
			ADMIN_TABLE
		SET
			ADMIN_VISIT_DATE = sysdate
		WHERE
			ADMIN_ID = 'admin';]
-- ī�װ� ���̺�
CREATE TABLE CATEGORY_TBL(
        CG_CODE            NUMBER    PRIMARY KEY,    -- ī�װ� �ڵ�
        CG_PARENT_CODE     NUMBER    NULL,           -- ����ī�װ� �ڵ�
        CG_NAME            VARCHAR2(50)    NOT NULL,
        FOREIGN KEY(CG_PARENT_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
);

-- 1�� ī�װ� : TOP(1) PANTS(2) SHIRTS(3) OUTER(4) SHOES(5) BAG(6) ACC(7)
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (1,NULL,'TOP');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (2,NULL,'PANTS');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (3,NULL,'SHIRTS');    
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (4,NULL,'OUTER');        
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (5,NULL,'SHOES');    
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (6,NULL,'BAG');    
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (7,NULL,'ACC');  

-- 1��ī�װ� TOP : 1
-- 2�� ī�װ� : ����Ƽ ��Ʈ ������/�ĵ�Ƽ ������Ƽ ���� ����Ƽ/7��Ƽ
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (8,1,'����Ƽ');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
VALUES (9,1,'��Ʈ');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
VALUES (10,1,'������&#38;�ĵ�Ƽ');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
VALUES (11,1,'������Ƽ');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
VALUES (12,1,'����');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
VALUES (13,1,'����Ƽ&#38;7��Ƽ');

-- 1��ī�װ� PANTS : 2
-- 2��ī�װ� : ������� û���� ������ ����� �ݹ���
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (14,2,'�������');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (15,2,'û����');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (16,2,'������');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (17,2,'�����');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (18,2,'�ݹ���');
    
-- 1��ī�װ� SHIRTS : 3
-- 2��ī�װ� : ���/���̳� ������ üũ/���� û���� ��Ʈ������ 

INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (19,3,'���&#38;���̳�');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (20,3,'������');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (21,3,'üũ&#38;����');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (22,3,'û����');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (23,3,'��Ʈ������'); 
    
    
-- 1��ī�װ� OUTER : 4
-- 2��ī�װ� : �е� ��Ʈ ��Ʈ/������ ���� �����/MA-1 �����/���� �ĵ�/����

INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (24,4,'�е�');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (25,4,'��Ʈ');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (26,4,'��Ʈ&#38;������');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (27,4,'����');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (28,4,'�����&#38;MA-1');     
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (29,4,'�����&#38;����');     
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (30,4,'�ĵ�&#38;����');  
    
-- 1��ī�װ� SHOES : 5
-- 2��ī�װ� : ����Ŀ�� ����/���� Ű���̽Ź�/��â ������/�ɸ�/����
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (31,5,'����Ŀ��');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (32,5,'����&#38;����');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (33,5,'Ű���̽Ź�&#38;��â');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (34,5,'������&#38;�ɸ�/����');
   
-- 1��ī�װ� BAG : 6
-- 2��ī�װ� : ���� ��Ʈ/����� ũ�ν��� Ŭ��ġ
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (35,6,'����'); 
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (36,6,'��Ʈ/�����');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (37,6,'ũ�ν���');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (38,6,'Ŭ��ġ');    
-- 1��ī�װ� ACC : 7
-- 2��ī�װ� : �縻/��Ÿ�� ���� ���÷�/�尩 ���̿��� ��Ʈ/�ð� ��Ÿ
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (39,7,'�縻/��Ÿ��');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (40,7,'����');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (41,7,'���÷�&#38;�尩');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (42,7,'���̿���');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (43,7,'��Ʈ&#38;�ð�');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (44,7,'��Ÿ');

-- ��ü ī�װ� ���
SELECT * FROM CATEGORY_TBL;

-- 1�� ī�װ� ���� ���
SELECT CG_CODE, CG_PARENT_CODE, CG_NAME FROM CATEGORY_TBL
WHERE CG_PARENT_CODE IS NULL;

-- 1�� ī�װ� TOP�� ������ 2�� ī�װ� ���
SELECT * FROM CATEGORY_TBL
WHERE CG_PARENT_CODE = 1;

-- 2�� ī�װ� ���� ���
SELECT * FROM CATEGORY_TBL
WHERE CG_PARENT_CODE IS NOT NULL;
drop table PRODUCT_TBL;
drop SEQUENCE SEQ_PRODUCT_TBL;
-- ��ǰ ���� ���̺�
CREATE TABLE PRODUCT_TBL(
        PRO_NUM             NUMBER  CONSTRAINT  PK_PRO_NUM         PRIMARY KEY,
        CG_CODE             NUMBER           NULL,
        PRO_NAME            VARCHAR2(50)            NOT NULL,
        PRO_PRICE           NUMBER                  NOT NULL,
        PRO_DISCOUNT        NUMBER                  NOT NULL,
        PRO_PUBLISHER       VARCHAR2(50)            NOT NULL,
        PRO_CONTENT         VARCHAR2(4000)  /* CLOB */                  NOT NULL,       -- ������ 4000BYTE �ʰ������Ǵ�?
        PRO_UP_FOLDER       VARCHAR2(50)             NOT NULL,
        PRO_IMG             VARCHAR2(100)             NOT NULL,  -- ��¥������ΰ� �����Ͽ� �����̸�����
        PRO_AMOUNT          NUMBER                  NOT NULL,
        PRO_BUY             CHAR(1)             NOT NULL,
        PRO_DATE            DATE DEFAULT SYSDATE    NOT NULL,
        PRO_UPDATEDATE      DATE DEFAULT SYSDATE    NOT NULL,
        FOREIGN KEY(CG_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
);    

-- ��ǰ���̺��� ��ǰ�ڵ� �÷��� ������
CREATE SEQUENCE SEQ_PRODUCT_TBL;

-- ��ǰ���̺� ������ ����ֱ�
INSERT INTO 
    PRODUCT_TBL 
        PRO_NUM, CG_CODE, PRO_NAME, PRO_PRICE, PRO_DISCOUNT, 
        PRO_PUBLISHER, PRO_CONTENT, PRO_UP_FOLDER, PRO_IMG, PRO_AMOUNT, PRO_BUY
    VALUES
        (SEQ_PRODUCT_TBL.NEXTVAL,#{cg_code},#{pro_name},#{pro_price},#{pro_discount},
        #{pro_publisher},#{pro_content},#{pro_up_folder},#{pro_img},#{pro_amount},#{pro_buy});

SELECT * FROM PRODUCT_TBL;        

<!-- ����� SQL�����۾� : �˻����� -->
   <sql id="criteria">
   		<trim prefix="(" suffix=") AND" prefixOverrides="OR">
   			<foreach collection="typeArr" item="type">
   				<trim prefix="OR">
   					<choose>
   						<when test="type == 'N'.toString()">
							PRO_NAME like '%' || #{keyword} || '%'
   						</when>
   						<when test="type == 'C'.toString()">
							PRO_NUM like '%' || #{keyword} || '%'
   						</when>
   						<when test="type == 'P'.toString()">
							PRO_PUBLISHER like '%' || #{keyword} || '%'
   						</when>
   					</choose>
   				</trim>
   			</foreach>
   		</trim>	
   </sql>
   
   <!-- �Խ���  -->
   <select id="getListWithPaging" resultType="com.demo.domain.BoardVO" parameterType="com.demo.domain.Criteria">
   		<![CDATA[
   		SELECT PRO_NUM, CG_CODE, PRO_NAME, PRO_PRICE, PRO_DISCOUNT, PRO_PUBLISHER, PRO_CONTENT, PRO_UP_FOLDER, PRO_IMG, PRO_AMOUNT, PRO_BUY, PRO_DATE, PRO_UPDATEDATE
		FROM (
    		SELECT /*+ INDEX_DESC(PRODUCT_TBL pk_pro_num) */
    			ROWNUM RN, PRO_NUM, CG_CODE, PRO_NAME, PRO_PRICE, PRO_DISCOUNT, PRO_PUBLISHER, PRO_CONTENT, PRO_UP_FOLDER, PRO_IMG, PRO_AMOUNT, PRO_BUY, PRO_DATE, PRO_UPDATEDATE
    		FROM 
                PRODUCT_TBL 
    		WHERE
			]]>
			
			<include refid="criteria"></include>
			
			<![CDATA[     		
    		 ROWNUM <= #{pageNum} * #{amount}
    		)
		WHERE RN > (#{pageNum} -1) * #{amount}
		]]> 
   </select>
   
   -- ��ü �����ͼ� -->
SELECT COUNT(*) 
FROM PRODUCT_TBL 
WHERE <include refid="criteria"></include> PRO_NUM > 0

   
-- üũ��ǰ �����۾�
UPDATE PRODUCT_TBL SET PRO_PRICE = #{pro_price}, PRO_BUY = #{pro_buy} WHERE PRO_NUM = #{pro_num}; 

-- ��ǰ���� �� ������
SELECT 
    PRO_NUM, CG_CODE, PRO_NAME, PRO_PRICE, PRO_DISCOUNT, PRO_PUBLISHER, PRO_CONTENT, 
    PRO_UP_FOLDER, PRO_IMG, PRO_AMOUNT, PRO_BUY, PRO_DATE, PRO_UPDATEDATE
FROM PRODUCT_TBL WHERE PRO_NUM = #{pro_num};

--2��ī�װ� 9�� �θ� ī�װ� ����
select CG_CODE, CG_PARENT_CODE, CG_NAME from CATEGORY_TBL where CG_CODE = #{CG_CODE};

--1��ī�װ��� �ڽ� ī�װ� ����
select CG_CODE, CG_PARENT_CODE, CG_NAME from CATEGORY_TBL where CG_PARENT_CODE = #{CG_PARENT_CODE};
-- ��ǰ ����
UPDATE PRODUCT_TBL 
SET CG_CODE = , PRO_NAME = , PRO_PRICE = , PRO_DISCOUNT = , PRO_PUBLISHER = , 
PRO_CONTENT = , PRO_UP_FOLDER = , PRO_IMG = , PRO_AMOUNT = , PRO_BUY = , PRO_UPDATEDATE = SYSDATE
WHERE PRO_NUM = ;

-- ��ǰ ����
DELETE FROM PRODUCT_TBL WHERE PRO_NUM = #{pro_num};

-- ��ٱ���
CREATE TABLE CART_TBL(
        CART_CODE        NUMBER,
        PRO_NUM         NUMBER          NOT NULL,
        MEMBER_ID         VARCHAR2(15)    NOT NULL,
        CART_AMOUNT      NUMBER          NOT NULL,
        FOREIGN KEY(PRO_NUM) REFERENCES PRODUCT_TBL(PRO_NUM),
        FOREIGN KEY(MEMBER_ID) REFERENCES MEMBER_TABLE(MEMBER_ID),
        CONSTRAINT PK_CART_CODE primary key(CART_CODE) 
);

create sequence seq_cart_code;

-- ��ٱ��Ͽ� �α��� ����ڰ� ��ǰ�� �߰���, ���� �Ұ��� ��������, ���� �����ʴ� ��� ��ٱ��� �߰�(���)
merge into cart_tbl
using dual
on (MEMBER_ID = 'id��' and PRO_NUM = '��ǰ�ڵ�')
when matched then
    update
        set CART_AMOUNT = CART_AMOUNT + ����
when not matched then
    insert(cart_code, pro_num, MEMBER_id, cart_amount)
    values(seq_cart_code.nextval,#{pro_num},#{MEMBER_id},#{cart_amount})

-- ��ٱ��� ���
SELECT C.CART_CODE, C.PRO_NUM, C.CART_AMOUNT ,P.PRO_NAME, P.PRO_PRICE, P.PRO_IMG, P.PRO_DISCOUNT, P.PRO_UP_FOLDER
FROM PRODUCT_TBL P INNER JOIN CART_TBL C ON P.PRO_NUM = C.PRO_NUM
WHERE C.MEMBER_ID = 'user01';

-- ��ٱ��� ��������
UPDATE CART_TBL
SET CART_AMOUNT = #{cart_amount}
WHERE CART_CODE = #{cart_code}

-- ��ٱ��� ��������
DELETE FROM CART_TBL
WHERE CART_CODE = #{cart_code}


drop table PAYMENT;
--5.�ֹ����� ���̺�. �������� ����
CREATE TABLE ORDER_TBL(
        ORD_CODE            NUMBER                  ,--PRIMARY KEY, -- �ֹ� �ڵ�
        MEMBER_ID             VARCHAR2(15)            NOT NULL, -- ������ ID
        ORD_NAME            VARCHAR2(30)            NOT NULL, -- ������ ����
        ORD_ZIPCODE         CHAR(5)                 NOT NULL, -- �����ּ�
        ORD_ADDR_BASIC      VARCHAR2(50)            NOT NULL, -- �ּ�
        ORD_ADDR_DETAIL     VARCHAR2(50)            NOT NULL, -- ���ּ�
        ORD_TEL             VARCHAR2(20)            NOT NULL, -- ��ȭ��ȣ
        ORD_PRICE           NUMBER                  NOT NULL, -- ���ֹ��ݾ�. ����
        ORD_REGDATE         DATE DEFAULT SYSDATE    NOT NULL, -- �ֹ�����
        ORD_STATUS          VARCHAR2(20)            NOT NULL, -- �ֹ� ����
        PAYMENT_STATUS      VARCHAR2(20)            NOT NULL -- ���� ����
        --FOREIGN KEY(MBSP_ID)    REFERENCES MBSP_TBL(MBSP_ID)
);

ALTER TABLE ORDER_TBL
ADD CONSTRAINT PK_ORDER_TBL PRIMARY KEY(ORD_CODE);

--6.�ֹ��� ���̺�  (������ ��ǰ�� ����)
CREATE TABLE ORDETAIL_TBL(
        ORD_CODE        NUMBER      NOT NULL REFERENCES ORDER_TBL(ORD_CODE),
        PRO_NUM         NUMBER      NOT NULL REFERENCES PRODUCT_TBL(PRO_NUM),
        DT_AMOUNT       NUMBER      NOT NULL,
        DT_PRICE        NUMBER      NOT NULL,  -- ������ȭ
        PRIMARY KEY (ORD_CODE ,PRO_NUM) 
);



drop table PAYMENT;

-- �ֹ���ȣ : ����������
CREATE SEQUENCE SEQ_ORD_CODE;

drop SEQUENCE SEQ_ORD_CODE;
-- �������̺� 
CREATE TABLE PAYMENT (
        PAY_CODE            NUMBER          NOT NULL, -- �Ϸù�ȣ
        ODR_CODE            NUMBER          NOT NULL, -- �ֹ���ȣ
        MEMBER_ID             VARCHAR2(50)    NOT NULL, -- ȸ��ID
        PAY_METHOD          VARCHAR2(50)    NOT NULL, -- �������
        PAY_DATE            DATE            NULL,     -- ������
        PAY_TOT_PRICE       NUMBER          NOT NULL, -- �����ݾ�
        PAY_NOBANK_PRICE    NUMBER          NULL, -- �������Աݱݾ�
        PAY_NOBANK_USER     VARCHAR2(50)    NULL,     -- �������Ա�����
        PAY_NOBANK          VARCHAR2(50)    NULL,     -- �Ա�����
        PAY_BANK_ACCOUNT    VARCHAR2(20)    NULL,     -- ���¹�ȣ         
        PAY_MEMO            VARCHAR2(100)   NULL      -- �޸�        
);

drop sEQUENCE SEQ_PAYMENT_CODE;

commit;

CREATE SEQUENCE SEQ_PAYMENT_CODE;

-- �ֹ� ���̺�
INSERT INTO 
    ORDER_TBL(
    ord_code, mbsp_id, ord_name, ord_zipcode, ord_addr_basic, ord_addr_detail, ord_tel, ord_price, ord_regdate, 
    ord_status, payment_status
    )
VALUES
	(#{ord_code}, #{mbsp_id}, #{ord_name}, #{ord_zipcode}, #{ord_addr_basic}, #{ord_addr_detail}, 
    #{ord_tel}, #{ord_regdate}, sysdate, #{ord_status}, #{payment_status})
			
-- �ֹ��� ���̺� ���� (��ٱ��� ���̺� ����)
INSERT ORDETAIL_TBL (ord_code, pro_num, dt_amount, dt_price)
SELECT #{odr_code},c.PRO_NUM, c.CART_AMOUNT  , p.pro_price
FROM CART_TBL c inner join PRODUCT_TBL p on c.pro_num = p.pro_num
WHERE MBSP_ID = #{mbsp_id}

-- ��ٱ��� ���̺� ����
DELETE FROM CART_TBL WHERE MBSP_ID = #{mbsp_id}

-- ���� ���̺� ����
INSERT INTO 
    PAYMENT (PAY_CODE, ODR_CODE, MBSP_ID, PAY_METHOD, PAY_DATE, PAY_TOT_RICE, PAY_NOBANK_PRICE, PAY_NOBANK_USER, PAY_NOBANK, PAY_MEMO ,PAY_BANK_ACCOUNT)
VALUES
    (SEQ_PAYMENT_CODE.NEXTVAL,#{odr_code},#{mbsp_id},#{pay_method},sysdate,#{pay_tot_price},#{pay_nobank_price},#{pay_nobank_user},#{pay_nobank},#{pay_memo},#{pay_bank_account})
    
--7.���� ���̺�
CREATE TABLE REVIEW_TBL(
        REW_NUM         NUMBER,
        MEMBER_ID         VARCHAR2(15)                NOT NULL,
        PRO_NUM         NUMBER                      NOT NULL,
        REW_CONTENT     VARCHAR2(200)               NOT NULL,
        REW_SCORE       NUMBER                      NOT NULL,
        REW_REGDATE     DATE DEFAULT SYSDATE        NOT NULL,
        FOREIGN KEY(MEMBER_ID) REFERENCES MEMBER_TABLE(MEMBER_ID),
        FOREIGN KEY(PRO_NUM) REFERENCES PRODUCT_TBL(PRO_NUM)
);

drop table REVIEW_TBL;

ALTER TABLE REVIEW_TBL
ADD CONSTRAINT PK_REVIEW_TBL PRIMARY KEY(REW_NUM);



create sequence SEQ_REVIEW_TBL;

-- ���� �ۼ�
INSERT INTO 
    REVIEW_TBL (REW_NUM, MBSP_ID, PRO_NUM, REW_CONTENT, REW_SCORE, REW_REGDATE)
VALUES
    (SEQ_REVIEW_TBL.NEXTVAL,);

-- ���� ��ǰ �ı�
<![CDATA[
		select 
    		REW_NUM, MBSP_ID, PRO_NUM, REW_CONTENT, REW_SCORE, REW_REGDATE
		from 
		    (
		    select /*+INDEX_DESC(REVIEW_TBL PK_REVIEW_TBL) */
		        rownum rn, REW_NUM, MBSP_ID, PRO_NUM, REW_CONTENT, REW_SCORE, REW_REGDATE
		    from 
		        REVIEW_TBL
		    where 
		    	PRO_NUM = #{pro_num}
		    	and    
		        rownum <= #{cri.pageNum} * #{cri.amount}
		    )
		where 
		    rn > (#{cri.pageNum} -1) * #{cri.amount}
		]]>

-- ���� ����¡ 


  
   -- �ֹ� �� ������ ������ ������. (�ֹ������̺�, ��ǰ���̺� ����)
   -- JOIN : 1. ����Ŭ ���� 2. ANSI-SQL ǥ������

   -- ����Ŭ
   SELECT
       ot.ORD_CODE, ot.PRO_NUM, ot.DT_AMOUNT,
       pt.CG_CODE, pt.PRO_NAME, pt.PRO_PRICE, pt.PRO_UP_FOLDER, pt.PRO_IMG, pt.PRO_AMOUNT, pt.PRO_BUY
   FROM
       ORDETAIL_TBL ot, PRODUCT_TBL pt
   WHERE
       ot.PRO_NUM = pt.PRO_NUM
   AND
       ot.ORD_CODE = #{}
    
INSERT INTO PAYMENT (
    PAY_CODE, ODR_CODE, MEMBER_ID, PAY_METHOD, PAY_DATE, PAY_TOT_PRICE, PAY_MEMO ,PAY_NOBANK_PRICE, PAY_NOBANK_USER, PAY_NOBANK, PAY_BANK_ACCOUNT ) 
VALUES 
    (SEQ_PAYMENT_CODE.NEXTVAL),2,'user01','�������Ա�',sysdate,71725,'' ,71725,'ȫ�浿','��������','456-456-4567')

-- ���� ���������� �ֱ� �ֹ����5�� ROW_NUMBER�� ������ �׳� rounum�� �����̵ǰ� �����͸��̾ƿ������ؼ� �ֱٵ����͸� �̾ƿ�������
select
			ord_code, member_id, ord_name, ord_tel, pay_method, 
            TO_CHAR(ord_price, '999,999,999') AS ord_price, ord_regdate,rnum
		from
			(
			select
				o.ord_code, o.member_id, o.ord_name, o.ord_tel, p.pay_method, o.ord_price, o.ord_regdate,
				ROW_NUMBER() OVER (ORDER BY o.ord_code DESC) AS rnum
			from
				ORDER_TBL o
				INNER JOIN PAYMENT p ON o.ord_code = p.odr_code
			)
		WHERE 
			rnum <= 5;
-- ���� ���������� �ֱ�ȸ������    
select
    MEMBER_NAME,MEMBER_ID,MEMBER_PHONE,MEMBER_EMAIL,MEMBER_DATESUB
from
    MEMBER_TABLE
order by
    MEMBER_DATESUB desc;
commit;

SELECT
    COUNT(O.ORD_CODE),SUM(P.PAY_TOT_PRICE),
FROM   
    ORDER_TBL O INNER JOIN PAYMENT P
    ON o.ord_code = p.odr_code

SELECT
    -- �� �ֹ��Ǽ� �� �� �ֹ��ݾ�
    COUNT(O.ORD_CODE) AS total_orders,
    TO_CHAR(SUM(P.PAY_TOT_PRICE),'999,999,999') AS total_order_amount,

    -- �̹� �� �ֹ��Ǽ� �� �ֹ��ݾ�
    COUNT(CASE WHEN EXTRACT(YEAR FROM O.ORD_REGDATE) = EXTRACT(YEAR FROM SYSDATE) AND EXTRACT(MONTH FROM O.ORD_REGDATE) = EXTRACT(MONTH FROM SYSDATE) THEN O.ORD_CODE END) AS this_month_orders,
    TO_CHAR(SUM(CASE WHEN EXTRACT(YEAR FROM O.ORD_REGDATE) = EXTRACT(YEAR FROM SYSDATE) AND EXTRACT(MONTH FROM O.ORD_REGDATE) = EXTRACT(MONTH FROM SYSDATE) THEN P.PAY_TOT_PRICE END),'999,999,999') AS this_month_order_amount,

    -- ���� �� �ֹ��Ǽ� �� �ֹ��ݾ�
    COUNT(CASE WHEN EXTRACT(YEAR FROM O.ORD_REGDATE) = EXTRACT(YEAR FROM ADD_MONTHS(SYSDATE, -1)) AND EXTRACT(MONTH FROM O.ORD_REGDATE) = EXTRACT(MONTH FROM ADD_MONTHS(SYSDATE, -1)) THEN O.ORD_CODE END) AS last_month_orders,
    TO_CHAR(SUM(CASE WHEN EXTRACT(YEAR FROM O.ORD_REGDATE) = EXTRACT(YEAR FROM ADD_MONTHS(SYSDATE, -1)) AND EXTRACT(MONTH FROM O.ORD_REGDATE) = EXTRACT(MONTH FROM ADD_MONTHS(SYSDATE, -1)) THEN P.PAY_TOT_PRICE END),'999,999,999') AS last_month_order_amount
FROM   
    ORDER_TBL O
INNER JOIN 
    PAYMENT P ON O.ORD_CODE = P.ODR_CODE;

