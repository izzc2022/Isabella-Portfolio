/* Assignment 2 - CSE2DBF
Isabella Ciccone - 20972461 */
-- TASK 2

-- Q2a)
-- Uses ROUND to portray 4 decimal places answer
CREATE OR REPLACE FUNCTION CalculateDistance(
    i_latitude NUMBER,
    i_longitude NUMBER,
    j_latitude NUMBER,
    j_longitude NUMBER
) RETURN NUMBER IS
    v_distance NUMBER;
BEGIN
    v_distance := SQRT(POWER(i_latitude - j_latitude, 2) + POWER(i_longitude - j_longitude, 2)) * 111;
    RETURN ROUND(v_distance, 4);
END;
/

-- Q2b)
-- LPAD and RPAD used for better positioning
DECLARE
    v_user_latitude NUMBER := -37.7206;
    v_user_longitude NUMBER := 145.0500;
    v_distance NUMBER;
    v_object VARCHAR2(100);
    CURSOR c_objects IS
        SELECT DISTINCT O.objectID, O.POIName, CL.latitude, CL.longitude,
               CalculateDistance(v_user_latitude, v_user_longitude, CL.latitude, CL.longitude) AS distance
        FROM Objects O, Coorlist CL, TrajectoryPoint TP
        WHERE O.POINAME IS NOT NULL
        AND O.ObjectID = CL.ObjectID
        ORDER BY distance;  

BEGIN
    DBMS_OUTPUT.PUT_LINE(LPAD('ObjectID', 10) || LPAD('POINAME', 13) || LPAD('DIST', 16));
    DBMS_OUTPUT.PUT_LINE('------------------------------------------------');
    
    FOR obj IN c_objects LOOP
        IF obj.latitude IS NOT NULL AND obj.longitude IS NOT NULL THEN
            v_distance := obj.distance;
            DBMS_OUTPUT.PUT_LINE(LPAD(obj.objectID, 10) || RPAD(obj.POIName, 30) || LPAD(v_distance, 6));
        END IF;
    END LOOP;
END;
/

-- Q2c)
-- Time function creation
CREATE OR REPLACE FUNCTION CheckTime(
    time_stamp TIMESTAMP
) RETURN VARCHAR2 IS
    time_only VARCHAR2(8);
    time_of_day VARCHAR2(30);
BEGIN
    time_only := TO_CHAR(time_stamp, 'HH24:MI:SS');

    IF time_only > '04:00' AND time_only < '12:00' THEN
        time_of_day := 'Morning';
    ELSIF time_only < '17:00' THEN
        time_of_day := 'Afternoon';
    ELSIF time_only < '22:00' THEN
        time_of_day := 'Evening';
    ELSE
        time_of_day := 'Night';
    END IF;

    RETURN time_of_day;
END;
/

-- Tester to show results
DECLARE
    t_time VARCHAR2(30);
BEGIN
    FOR TP IN (SELECT trajID, TO_CHAR(MIN(tptimestamp), 'HH24') AS first
               FROM TrajectoryPoint
               GROUP BY trajID
               ORDER BY trajID
              ) LOOP
        IF TP.trajID IS NOT NULL AND TP.first IS NOT NULL THEN
            t_time := CheckTime(TO_TIMESTAMP(TP.first, 'HH24'));
            DBMS_OUTPUT.PUT_LINE(TP.trajID || CHR(9) || TP.first || CHR(9) || t_time);
        END IF;
    END LOOP;
END;
/

-- Q2d)
CREATE OR REPLACE PROCEDURE total_dist(p_objectID IN Objects.objectID%TYPE) AS
    r_length NUMBER := 0;
    prev_latitude Coorlist.latitude%TYPE;
    prev_longitude Coorlist.longitude%TYPE;

    CURSOR c_objectDetails IS
        SELECT CL.seq, CL.latitude, CL.longitude
        FROM Coorlist CL, Objects O
        WHERE O.objectID = p_objectID
              AND CL.objectID = O.objectID;
BEGIN
    DBMS_OUTPUT.PUT_LINE('SQL> exec total_dist('|| p_objectID || ')');
    FOR ptr IN c_objectDetails LOOP
        IF ptr.seq = 1 THEN
            r_length := 0;
            prev_latitude := ptr.latitude;
            prev_longitude := ptr.longitude;
            DBMS_OUTPUT.PUT_LINE('Seq: ' || ptr.seq || ' (' || ptr.latitude || ',' || ptr.longitude || ') Length = ' || r_length || ' km');
        ELSE
            r_length := r_length + CalculateDistance(prev_latitude, prev_longitude, ptr.latitude, ptr.longitude);
            prev_latitude := ptr.latitude;
            prev_longitude := ptr.longitude;
            DBMS_OUTPUT.PUT_LINE('Seq: ' || ptr.seq || ' (' || ptr.latitude || ',' || ptr.longitude || ') Length = ' || r_length || ' km');
        END IF;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Total Length: ' || r_length || ' km');
END total_dist;
/

-- Run Procedure
begin
total_dist('OB0021');
end;
/
