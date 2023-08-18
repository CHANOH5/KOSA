SELECT ROUND(1234.567)
FROM employees;

--�ݿø�
SELECT ROUND(1234.567), -- 1235
       ROUND(1234.567, 1), -- 1234.6
       ROUND(1234.567, -1) -- 1230
FROM dual;

--������
SELECT TRUNC(1234.567), -- 1234
       TRUNC(1234.567, 1), -- 1234.5
       TRUNC(1234.567, -1) -- 1230
FROM dual;

SELECT CEIL(1234.1), -- 1235
        FLOOR(1234.5) -- 1234
FROM dual;

SELECT employee_id, salary, commission_pct,
       salary+(salary*NVL(commission_pct,0)), 
       ROUND((salary+(salary*NVL(commission_pct,0)))/12, 1) ����
FROM employees;

SELECT LENGTH('HELLOORACLE'), -- 11
        LENGTH('�ȳ��ϼ���'), -- 5,
        INSTR('HELLOORACLE', 'L'), -- L���ڿ��� ��ġ �˾Ƴ���
        INSTR('HELLOORACLE', 'L', 5), -- 5�� �ε������� L�� ��ġ�� ã�ƶ� , 10����
        INSTR('HELLOORACLE','X') -- �ش� ���ڸ� ã�� ���ϸ�? > 0�� ��ȯ
FROM dual;

SELECT SUBSTR('HELLOORACLE', 2, 3), -- 2�ε������� 3�� ��ȯ 
        LPAD('HELLO', 8, '*'), -- HELLO ���ڿ��� 8�� ���ڿ��� ����� �����ڸ��� *�� ä��ڴ�
        'BEFIN'||LTRIM('   HELLO   ')||'END', -- LTRIM�Լ��� �̿��ؼ� ���� ���� �о��
        'BEFIN'||RTRIM('   HELLO   ')||'END',
        'BEFIN'||TRIM('   HELLO   ')||'END'
FROM dual;

SELECT employee_id AS ���, first_name AS �̸�
FROM employees
WHERE INSTR(first_name, 'e') = 2 OR INSTR(first_name, 'E') = 2;

SELECT employee_id AS ���, first_name AS �̸�
FROM employees
WHERE INSTR(UPPER(first_name), 'E') = 2;

SELECT MONTHS_BETWEEN(SYSDATE, '23/12/22'), -- ���� ��¥ �������� 23/12/22�ϱ���  ������ ����ϱ�
       MONTHS_BETWEEN(SYSDATE, '23/1/1') -- ���� 1��1�Ϻ��� ������� ������ ����ϱ�
FROM dual;

SELECT ADD_MONTHS(SYSDATE, 1) "�Ѵ��� ��¥",
        ADD_MONTHS(SYSDATE, -6) "6�������� ��¥"
FROM dual;

SELECT LAST_DAY('23/02/16') "2���� ����������"
FROM dual;

SELECT NEXT_DAY(SYSDATE, '�Ͽ���')
FROM dual;

SELECT SYSDATE, 
       SYSDATE - TO_DATE('23/01/01')
FROM dual;

SELECT SYSDATE
      ,TRUNC(SYSDATE - TO_DATE('23/01/01')) "�ϼ�" 
FROM dual;

SELECT *
FROM employees
WHERE department_id = 30;

SELECT *
FROM employees
WHERE department_id = '30'; -- ������ '30'�� ������ 30���� �ڵ�����ȯ

SELECT *
FROM employees
WHERE department_id = '030'; -- ������ '030'�� ������ 30���� �ڵ�����ȯ

SELECT *
FROM employees
WHERE hire_date = '03/05/18'; -- ������ '03/05/18'�� ��¥������ �ڵ�����ȯ

SELECT TO_CHAR(SYSDATE),
        TO_CHAR(SYSDATE, 'yy/mm/dd hh24:mi:ss')
FROM dual;


SELECT employee_id, hire_date
        ,TRUNC(TO_DATE('23/12/31') - hire_date)
FROM employees;

SELECT employee_id, hire_date
        ,TRUNC(SYSDATE - hire_date) "���ñ����� �ٹ��ϼ�"
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
       TO_CHAR('1234567.8', '9,999.9'), -- �����ڸ������� ���� �����ڸ����� ��� ### �� ��
       TO_CHAR('1234567.8', '9,999,999.9')
FROM dual;



SELECT TO_NUMBER('1,234,567.8', '0,000,000.0'),
       TO_CHAR(1234.5, '0,000,000.0'),
       TO_CHAR('1234567.8', '0,000,000.0')
FROM dual;

SELECT TO_CHAR(1234.5, 'L9,999,999.00') --'\1,234.50'
FROM dual;

SELECT employee_id, commission_pct, NVL2(commission_pct, '��������', '�������')
FROM employees;

SELECT employee_id, commission_pct, NVL2(commission_pct, TO_CHAR(commission_pct), '�������')
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

SELECT  NVL2(commission_pct, '��������', '�������'),
        DECODE(commission_pct, null, '�������', '��������')
FROM employees;
