select * from not_exists_in_prd_db;

--lprod테이블 초기화
delete lprod;

Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (1,'P101','컴퓨터제품');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (2,'P102','전자제품');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (3,'P201','여성캐주얼');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (4,'P202','남성캐주얼');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (5,'P301','피혁잡화');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (6,'P302','화장품');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (7,'P401','음반/CD');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (8,'P402','도서');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (9,'P403','문구류');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (22,'P212','테스트2');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (23,'P213','테스트3');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (24,'P214','테스트4');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (101,'N101','농산문');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (102,'N102','수산물');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (103,'N103','축산물');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (104,'N104','라라랄');
Insert into PC09_TEST.LPROD (LPROD_ID,LPROD_GU,LPROD_NM) values (105,'N105','감자돌이');

commit;