SELECT ROUND(1234.567)
FROM employees;

--반올림
SELECT ROUND(1234.567), -- 1235
       ROUND(1234.567, 1), -- 1234.6
       ROUND(1234.567, -1) -- 1230
FROM dual;

--버리기
SELECT TRUNC(1234.567), -- 1234
       TRUNC(1234.567, 1), -- 1234.5
       TRUNC(1234.567, -1) -- 1230
FROM dual;

SELECT CEIL(1234.1), -- 1235
        FLOOR(1234.5) -- 1234
FROM dual;

SELECT employee_id, salary, commission_pct,
       salary+(salary*NVL(commission_pct,0)), 
       ROUND((salary+(salary*NVL(commission_pct,0)))/12, 1) 월급
FROM employees;

SELECT LENGTH('HELLOORACLE'), -- 11
        LENGTH('안녕하세요'), -- 5,
        INSTR('HELLOORACLE', 'L'), -- L문자열의 위치 알아내기
        INSTR('HELLOORACLE', 'L', 5), -- 5번 인덱스부터 L의 위치를 찾아라 , 10예상
        INSTR('HELLOORACLE','X') -- 해당 문자를 찾지 못하면? > 0을 반환
FROM dual;

SELECT SUBSTR('HELLOORACLE', 2, 3), -- 2인덱스부터 3개 반환 
        LPAD('HELLO', 8, '*'), -- HELLO 문자열을 8개 문자열로 만들고 남는자리는 *로 채우겠다
        'BEFIN'||LTRIM('   HELLO   ')||'END', -- LTRIM함수를 이용해서 왼쪽 공백 털어내기
        'BEFIN'||RTRIM('   HELLO   ')||'END',
        'BEFIN'||TRIM('   HELLO   ')||'END'
FROM dual;

SELECT employee_id AS 사번, first_name AS 이름
FROM employees
WHERE INSTR(first_name, 'e') = 2 OR INSTR(first_name, 'E') = 2;

SELECT employee_id AS 사번, first_name AS 이름
FROM employees
WHERE INSTR(UPPER(first_name), 'E') = 2;

SELECT MONTHS_BETWEEN(SYSDATE, '23/12/22'), -- 오늘 날짜 기준으로 23/12/22일까지  개월수 계산하기
       MONTHS_BETWEEN(SYSDATE, '23/1/1') -- 올해 1월1일부터 현재까지 개월수 계산하기
FROM dual;

SELECT ADD_MONTHS(SYSDATE, 1) "한달후 날짜",
        ADD_MONTHS(SYSDATE, -6) "6개월전의 날짜"
FROM dual;

SELECT LAST_DAY('23/02/16') "2월의 마지막일자"
FROM dual;

SELECT NEXT_DAY(SYSDATE, '일요일')
FROM dual;

SELECT SYSDATE, 
       SYSDATE - TO_DATE('23/01/01')
FROM dual;

SELECT SYSDATE
      ,TRUNC(SYSDATE - TO_DATE('23/01/01')) "일수" 
FROM dual;

SELECT *
FROM employees
WHERE department_id = 30;

SELECT *
FROM employees
WHERE department_id = '30'; -- 문자형 '30'이 숫자형 30으로 자동형변환

SELECT *
FROM employees
WHERE department_id = '030'; -- 문자형 '030'이 숫자형 30으로 자동형변환

SELECT *
FROM employees
WHERE hire_date = '03/05/18'; -- 문자형 '03/05/18'이 날짜형으로 자동형변환

SELECT TO_CHAR(SYSDATE),
        TO_CHAR(SYSDATE, 'yy/mm/dd hh24:mi:ss')
FROM dual;


SELECT employee_id, hire_date
        ,TRUNC(TO_DATE('23/12/31') - hire_date)
FROM employees;

SELECT employee_id, hire_date
        ,TRUNC(SYSDATE - hire_date) "오늘까지의 근무일수"
FROM employees;

SELECT employee_id, hire_date
FROM employees
--WHERE TO_DATE(hire_date) BETWEEN '23/08/01' AND '23/08/30';
WHERE TO_CHAR(hire_date, 'mm') = 08;

SELECT TO_CHAR(SYSDATE),
        TO_CHAR(SYSDATE, 'yy/mm/dd hh24:mi:ss day')
FROM dual;


SELECT employee_id, hire_date, TO_CHAR(hire_date, 'day')
FROM employees
--WHERE TO_DATE(hire_date) BETWEEN '23/08/01' AND '23/08/30';
WHERE TO_CHAR(hire_date, 'mm') = 08;

SELECT TO_NUMBER('1,234.5', '0,000.0'),
        TO_NUMBER('1,234,567.8', '9,999,999.9')
FROM dual;

SELECT TO_CHAR(1234.5, '9,999.9'),
       TO_CHAR('1234567.8', '9,999.9'), -- 숫자자릿수보다 작은 패턴자릿수의 경우 ### 이 뜸
       TO_CHAR('1234567.8', '9,999,999.9')
FROM dual;



SELECT TO_NUMBER('1,234,567.8', '0,000,000.0'),
       TO_CHAR(1234.5, '0,000,000.0'),
       TO_CHAR('1234567.8', '0,000,000.0')
FROM dual;

SELECT TO_CHAR(1234.5, 'L9,999,999.00') --'\1,234.50'
FROM dual;

SELECT employee_id, commission_pct, NVL2(commission_pct, '수당있음', '수당없음')
FROM employees;

SELECT employee_id, commission_pct, NVL2(commission_pct, TO_CHAR(commission_pct), '수당없음')
FROM employees;

SELECT NULLIF(10, 10),
       NULLIF('hello', 'hi')
FROM dual;

SELECT employee_id, department_id
FROM employees
WHERE department_id IS NOT NULL;

SELECT  NVL(commission_pct, 0),
        DECODE(commission_pct, null, 0, commission_pct)
FROM employees;

SELECT  NVL2(commission_pct, '수당있음', '수당없음'),
        DECODE(commission_pct, null, '수당없음', '수당있음')
FROM employees;
