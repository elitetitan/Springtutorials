DROP TABLE IF EXISTS EMP;
DROP TABLE IF EXISTS DEPT;

create table DEPT(
                   deptno     number,
                   dname      varchar(14),
                   loc        varchar(13),
                   constraint pk_dept primary key (deptno)
);

create table EMP(
                  empno    number,
                  ename    varchar(10),
                  job      varchar(9),
                  mgr      number,
                  hiredate date,
                  sal      number,
                  comm     number,
                  deptno   number,
                  constraint pk_emp primary key (empno),
                  constraint fk_deptno foreign key (deptno) references dept (deptno)
);