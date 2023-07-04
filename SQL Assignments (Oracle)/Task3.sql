/* Assignment 2 - CSE2DBF
Isabella Ciccone - 20972461 */
-- TASK 3

-- Q3a)
CREATE OR REPLACE TRIGGER add_contributor_point
AFTER INSERT ON TrajectoryPoint
FOR EACH ROW
BEGIN
    UPDATE Contributor
    SET points = points + 1
    WHERE contributorID = (
        SELECT contributorID
        FROM Trajectory
        WHERE trajID = :new.trajID
    );
END;
/

-- TEST CASES
-- There should be no C0052 to begin with
SELECT * 
FROM Contributor
ORDER BY contributorID DESC;

-- We now insert a contributor of ID C0052, with 4 starting points
INSERT INTO CONTRIBUTOR 
VALUES('C0052',4);

-- We now insert a trajectory and a trajectory point to the associated new contributor
INSERT INTO TRAJECTORY(TRAJID,TRAJDATETIME,TRAJDURATION,TRAJDISTANCE,LASTUPDATE,CONTRIBUTORID) 
VALUES('TRJ0052','15-May-2023', INTERVAL '00:57:49.36' HOUR TO SECOND(2),2.56,'12-Jul-2018','C0052');

INSERT INTO TrajectoryPoint (trajID, tpTimestamp, latitude, longitude)
VALUES ('TRJ0052', SYSDATE, 123.456, 789.012);

-- Contributor points for C0052 should now be at 5 points due to the trajectory point insert (incremented by 1 from 4)
SELECT * 
FROM Contributor
ORDER BY contributorID DESC;

-- We now insert a second trajectory and a trajectory point to the associated new contributor
INSERT INTO TRAJECTORY(TRAJID,TRAJDATETIME,TRAJDURATION,TRAJDISTANCE,LASTUPDATE,CONTRIBUTORID) 
VALUES('TRJ0053','15-May-2023', INTERVAL '00:57:49.36' HOUR TO SECOND(2),2.56,'12-Jul-2018','C0052');

INSERT INTO TrajectoryPoint (trajID, tpTimestamp, latitude, longitude)
VALUES ('TRJ0053', SYSDATE, 123.456, 789.012);

-- Contributor points for C0052 should now be at 6 points (incremented by 1 from 5)
SELECT * 
FROM Contributor 
ORDER BY contributorID DESC;

-- Deletion statements to remove the new data
DELETE FROM TrajectoryPoint 
WHERE trajID IN (
    SELECT trajID
    FROM Trajectory
    WHERE contributorID = 'C0052'
);

DELETE FROM Trajectory 
WHERE contributorID = 'C0052';

DELETE FROM Contributor
WHERE contributorID = 'C0052';

-- Should now be removed
SELECT * 
FROM Contributor 
ORDER BY contributorID DESC;