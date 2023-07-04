/* Assignment 2 - CSE2DBF
Isabella Ciccone - 20972461 */
-- TASK 1

-- Q1a)
-- Tester query to see total number of objects in table
SELECT COUNT(*)
FROM Objects;
-- Actual answer
SELECT ObjectType, COUNT(*) AS COUNT
FROM Objects
GROUP BY ObjectType;

-- Q1b)
-- Needs to access multiple details to correctly obtain a matching object POIName result
SELECT DISTINCT T.trajID, T.contributorID, A.name
FROM Trajectory T, Contributor C, Administrator A, Objects O
WHERE T.contributorID = C.contributorID
  AND C.contributorID = A.contributorID
  AND O.POIName = 'McDonald'
ORDER BY trajID;

-- Q1c)
-- Is ordered first from largest number of submitted trajectories, and then in alphabetical order by admin or member ID
SELECT M.memberID AS ID, M.name, T.contributorID, COUNT(T.trajID) AS NUM_OF_TRAJECTORIES
FROM Trajectory T, Contributor C, Member M
WHERE T.contributorID = C.contributorID
  AND C.contributorID = M.contributorID
GROUP BY T.contributorID, M.memberID, M.name
UNION
SELECT A.adminID AS ID, A.name, T.contributorID, COUNT(T.trajID) AS NUM_OF_TRAJECTORIES
FROM Trajectory T, Contributor C, Administrator A
WHERE T.contributorID = C.contributorID
  AND C.contributorID = A.contributorID
GROUP BY T.contributorID, A.adminID, A.name
ORDER BY NUM_OF_TRAJECTORIES DESC, ID;

-- Q1d)
-- The equals operator didn't work, however LIKE does, meaning that some of the objects 
-- that 'Plenty Road' is associated with have it written differently/stored differently
-- The LIKE operator guarantees that anything extremely similar to the road name 
-- (eg. from removing extra punctuation) will work
SELECT COUNT(DISTINCT T.trajID) AS NUM_TRAJ
FROM Trajectory T, TrajectoryPoint TP, Objects O, Coorlist CL
WHERE O.RoadName LIKE '%Plenty Road%' 
        AND TP.latitude = CL.latitude 
        AND TP.longitude = CL.longitude
        AND O.ObjectID = CL.ObjectID
        AND T.trajID = TP.trajID 
        AND O.ObjectType = 'ROAD';

-- Q1e)
-- Uses MIN within a select statement to determine the minimum/earliest join date amongst all join dates
SELECT M.name, M.joinDate
FROM Member M
WHERE M.joinDate= (SELECT MIN(joinDate) FROM Member);
