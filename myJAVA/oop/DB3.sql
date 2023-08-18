SELECT employee_id, first_name, department_id
FROM employees; --107명

SELECT department_id, department_name
FROM departments; --27건

--카티션곱
--사원의 사번, 이름, 부서번호, 부서명을 출력하시오(107명*27건)
SELECT employee_id, first_name, e.department_id, 
          d.department_id, department_name
FROM employees e, departments d;

--사원의 사번, 이름, 부서번호, 부서명을 출력하시오(106명)
SELECT employee_id, first_name, e.department_id, 
          d.department_id, department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id;

--표준화된 ANSI JOIN법 
--1)JOIN ON
SELECT employee_id, first_name, e.department_id, 
          d.department_id, department_name
FROM employees e JOIN departments d ON (e.department_id = d.department_id );

--사원의 사번, 이름, 부서번호, 부서명, 부서가 속한 도시명을 출력하시오
SELECT department_id FROM employees WHERE employee_id=100;  --90
SELECT department_name, location_id FROM departments WHERE department_id; --Executive, 1700
SELECT city FROM locations WHERE location_id=1700; --Seattle


SELECT  employee_id, first_name, e.department_id, 
          d.department_id, department_name,
          city
FROM employees e JOIN departments d ON (e.department_id = d.department_id )
                            JOIN locations l ON ( d.location_id = l.location_id   );

--2) NATURAL JOIN
--사원의 사번, 이름, 직무번호, 직무명을 출력하시오(106명)
SELECT employee_id, first_name
           ,j.job_id, job_title
FROM employees e JOIN jobs j ON (e.job_id = j.job_id);

SELECT employee_id, first_name
           ,job_id, job_title
FROM employees NATURAL JOIN jobs;

--3)JOIN USING
--사원의 사번, 이름, 부서번호, 부서명을 출력하시오(106명)
SELECT employee_id, first_name
          ,department_id, department_name
FROM employees NATURAL JOIN departments;(X)

SELECT employee_id, first_name
          ,department_id, department_name
FROM employees JOIN departments USING (department_id);
---------------------------------------------------------------------------
--4)OUTER JOIN
--사원의 사번, 이름, 부서번호, 부서명을 출력하시오. 단 부서없는 사원도 출력하시오(107명)
SELECT employee_id, first_name
          ,d.department_id, department_name
FROM employees e LEFT OUTER JOIN departments d ON (e.department_id = d.department_id);


--(오라클전용outer join)
SELECT employee_id, first_name
          ,d.department_id, department_name
FROM employees e,  departments d
WHERE e.department_id = d.department_id(+);


--사원의 사번, 이름, 부서번호, 부서명을 출력하시오. 단 사원없는 부서도 출력하시오(122건)
SELECT employee_id, first_name
          ,d.department_id, department_name
FROM employees e RIGHT JOIN departments d ON (e.department_id = d.department_id);


--사원의 사번, 이름, 부서번호, 부서명을 출력하시오. 단 부서없는 사원도, 사원없는 부서도 출력하시오(123건)
SELECT employee_id, first_name
          ,d.department_id, department_name
FROM employees e FULL JOIN departments d ON (e.department_id = d.department_id);
--(오라클전용 full outer join은 제공안함)

--5)SELF JOIN
--사원의 사번, 이름, 관리자번호, 관리자이름을 출력하시오
SELECT e.employee_id "사번",  e.first_name "이름"
         -- ,m.employee_id "관리자번호"
           ,e.manager_id     "관리자번호"
           ,m.first_name "관리자이름"
FROM employees e JOIN employees m ON (  e.manager_id = m.employee_id        );

--사원의 사번, 이름, 관리자번호, 관리자이름을 출력하시오, 관리자가 없는 사원도 출력하시오(107명)
SELECT e.employee_id "사번",  e.first_name "이름"
           ,e.manager_id     "관리자번호"
           ,m.first_name "관리자이름"
FROM employees e LEFT JOIN employees m ON (  e.manager_id = m.employee_id        )

---------------------------------------------------------------------------------------------------------------
집합연산자
--사원의 사번, 이전직무번호를 출력하시오(JOB_HISTORY)
--사원의 사번, 직무번호를 출력하시오(EMPLOYEES)
SELECT employee_id, job_id
FROM job_history
UNION
SELECT employee_id, job_id
FROM employees;

SELECT employee_id, job_id
FROM job_history
UNION ALL
SELECT employee_id, job_id
FROM employees
ORDER BY employee_id;


--이전직무와 다른 직무를 갖는 사원들의 사번, 현재직무를 출력하시오
SELECT employee_id, job_id
FROM employees
MINUS
SELECT employee_id, job_id
FROM job_history;

--이전직무와 같은 직무를 갖는 사원들의 사번, 현재직무를 출력하시오
SELECT employee_id, job_id
FROM employees
INTERSECT
SELECT employee_id, job_id
FROM job_history; 
--------------------------------------------------------------------------------------
--SubQuery
--위치에 따른 분류
(1) SubQuery : WHERE절에서 사용
(2) INLINE VIEW : FROM절에서 사용
(3) Scalar Query : SELECT절에서 사용
 
--SubQuery결과행수에 따른 분류
(1)Single Row SubQuery : main query와 비교시 일반비교연산자 사용가능( =, <>)
(2)Multi Row SubQuery  : main query와 비교시 특수비교연산자 사용(IN, ANY>, ANY<, ALL>, ALL<) 

--최대급여자의 사번, 이름, 급여를 출력하시오
--1) 최대급여를 계산한다
SELECT MAX(salary)
FROM employees;
--2) 1)과 같은 급여를 받는 사원의 사번, 이름, 급여를 출력하시오
SELECT employee_id, first_name, salary
FROM employees
WHERE  salary = (SELECT MAX(salary)
FROM employees);

--사원들의 평균급여보다 많은 급여를 받는 급여자의 사번, 이름, 급여를 출력하시오
1)사원들의 평균급여계산한다
SELECT AVG(salary) FROM employees
2) 1)보다 많은 급여를 받는 급여자의 사번, 이름, 급여를 출력하시오
SELECT employee_id, first_name, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);


--Multi Row SubQuery : IN는 =ANY
                                  >ANY 최소값보다 크다
                                  <ANY 최대값보다 작다
                                  >ALL  최대값보다 크다
                                  <ALL  최소값보다 작다

--부서별 최대급여를 받는 사원의 부서번호, 사번, 이름, 급여를 출력하시오
--1)부서별 최대급여를 계산한다
SELECT department_id, MAX(salary)
FROM employees
GROUP BY department_id;

--2) 1)과 같은 급여를 받는 사원의 사번, 이름, 급여를 출력하시오
SELECT department_id, employee_id, first_name, salary
FROM employees
WHERE (department_id, salary) IN (SELECT department_id, MAX(salary)
                                                 FROM employees
                                                 GROUP BY department_id
);

--부하직원이 있는 관리자의 관리자번호, 이름을 출력하시오
SELECT employee_id, first_name
FROM employees
WHERE employee_id IN ( SELECT manager_id
                                    FROM employees);

SELECT employee_id, first_name
FROM employees
WHERE employee_id IN (null, 100, 102, 103,  101,  108...);


--부하직원이 없는 모든사원의 사번, 이름을 출력하시오
SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN ( SELECT manager_id
                                    FROM employees); --0건

SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN (null, 100, 102, 103,  101,  108...);

SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN ( SELECT NVL(manager_id, -1)
                                    FROM employees); --89명

SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN ( SELECT manager_id
                                           FROM employees
                                           WHERE manager_id IS NOT NULL); --89건

----------------------------------------------------------------------------------------
(2) INLINE VIEW : FROM절에서 사용
(3) Scalar Query : SELECT절에서 사용

DDL : TABLE객체생성관련, 무결성제약조건
DML : TABLE객체에 데이터추가/수정/삭제





                         












