DROP TABLE Object CASCADE CONSTRAINTS PURGE;
DROP TABLE Trajectory CASCADE CONSTRAINTS PURGE;
DROP TABLE Modified CASCADE CONSTRAINTS PURGE;
DROP TABLE PointObj CASCADE CONSTRAINTS PURGE;
DROP TABLE RoadObj CASCADE CONSTRAINTS PURGE;
DROP TABLE TrajectoryPoint CASCADE CONSTRAINTS PURGE;
DROP TABLE ObjectCoordinates CASCADE CONSTRAINTS PURGE;
DROP TABLE Contributor CASCADE CONSTRAINTS PURGE;
DROP TABLE Administrator CASCADE CONSTRAINTS PURGE;
DROP TABLE Member CASCADE CONSTRAINTS PURGE;

-- I used the above delete queries every time I needed to start my table fresh. They are left here to show my development process.

CREATE TABLE Object 
(
  objectID VARCHAR2(6) NOT NULL,
  POIName VARCHAR2(50),
  RoadName VARCHAR2(50),
  RoadLength FLOAT(6),
  RegionName VARCHAR2(50),
  RegionArea FLOAT(6),
  PointFlag CHAR(1) NOT NULL CHECK (PointFlag IN ('Y', 'N')),
  RoadFlag CHAR(1) NOT NULL CHECK (RoadFlag IN ('Y', 'N')),
  RegionFlag CHAR(1) NOT NULL CHECK (RegionFlag IN ('Y', 'N')),
  PRIMARY KEY (objectID)
);

CREATE TABLE ObjectCoordinates 
(
  objectID VARCHAR2(6) NOT NULL,
  seq NUMBER(6) CHECK (seq BETWEEN 1 AND 6),
  latitude NUMBER(10,6),
  longitude NUMBER(10,6),
  PRIMARY KEY (objectID),
  FOREIGN KEY (objectID) REFERENCES Object(objectID)
);

CREATE TABLE PointObj 
(
  objectID VARCHAR2(6) NOT NULL,
  POIName VARCHAR2(50) NOT NULL,
  RestaurantFlag CHAR(1) NOT NULL CHECK (RestaurantFlag IN ('Y', 'N')),
  ShopFlag CHAR(1) NOT NULL CHECK (ShopFlag IN ('Y', 'N')),
  EducationFlag CHAR(1) NOT NULL CHECK (EducationFlag IN ('Y', 'N')),
  HealthFlag CHAR(1) NOT NULL CHECK (HealthFlag IN ('Y', 'N')),
  OthersFlag CHAR(1) NOT NULL CHECK (OthersFlag IN ('Y', 'N')),
  PRIMARY KEY (objectID),
  FOREIGN KEY (objectID) REFERENCES Object(objectID)
);

CREATE TABLE RoadObj 
(
  objectID VARCHAR2(6) NOT NULL,
  RoadName VARCHAR2(50) NOT NULL,
  RoadLength FLOAT(6) NOT NULL,
  HighwayFlag CHAR(1) NOT NULL CHECK (HighwayFlag IN ('Y', 'N')),
  PrimaryFlag CHAR(1) NOT NULL CHECK (PrimaryFlag IN ('Y', 'N')),
  ResidentialFlag CHAR(1) NOT NULL CHECK (ResidentialFlag IN ('Y', 'N')),
  FootpathFlag CHAR(1) NOT NULL CHECK (FootpathFlag IN ('Y', 'N')),
  PRIMARY KEY (objectID),
  FOREIGN KEY (objectID) REFERENCES Object(objectID)
);

CREATE TABLE Contributor
(
  contributorID VARCHAR2(6) NOT NULL,
  points NUMBER(6) CHECK (points > -1),
  PRIMARY KEY (contributorID)
);

CREATE TABLE Administrator
(
  adminID VARCHAR2(6) NOT NULL,
  email VARCHAR2(50),
  name VARCHAR2(50),
  contributorID VARCHAR2(6) NOT NULL,
  PRIMARY KEY (adminID),
  FOREIGN KEY (contributorID) REFERENCES Contributor(contributorID)
);

CREATE TABLE Member
(
  memberID VARCHAR2(6) NOT NULL,
  joinDate DATE DEFAULT SYSDATE,
  email VARCHAR2(50),
  name VARCHAR2(50),
  contributorID VARCHAR2(6) NOT NULL,
  PRIMARY KEY (memberID),
  FOREIGN KEY (contributorID) REFERENCES Contributor(contributorID)
);

CREATE TABLE Modified
(
  objectID VARCHAR2(6) NOT NULL,
  adminID VARCHAR2(6) NOT NULL,
  reason VARCHAR2(200),
  modifiedDate DATE DEFAULT SYSDATE,
  PRIMARY KEY (objectID, adminID),
  FOREIGN KEY (objectID) REFERENCES Object(objectID),
  FOREIGN KEY (adminID) REFERENCES Administrator(adminID)
);

CREATE TABLE Trajectory
(
  trajID VARCHAR2(6) NOT NULL,
  trajDateTime DATE,
  trajDuration INTERVAL DAY TO SECOND,
  trajDistance VARCHAR2(6),
  lastUpdate DATE DEFAULT SYSDATE,
  contributorID VARCHAR2(6) NOT NULL,
  PRIMARY KEY (trajID),
  FOREIGN KEY (contributorID) REFERENCES Contributor(contributorID)
);

CREATE TABLE TrajectoryPoint
(
  trajID VARCHAR2(6) NOT NULL,
  tpTimestamp DATE NOT NULL,
  latitude NUMBER(10,6),
  longitude NUMBER(10,6),
  PRIMARY KEY (trajID, tpTimestamp),
  FOREIGN KEY (trajID) REFERENCES Trajectory(trajID)
);

INSERT INTO Object (objectID, POIName, RoadName, RoadLength, RegionName, RegionArea, PointFlag, RoadFlag, RegionFlag)
VALUES ('O00001', 'Nikos Fairfield', null, null, null, null, 'Y', 'N', 'N');

INSERT INTO Object (objectID, POIName, RoadName, RoadLength, RegionName, RegionArea, PointFlag, RoadFlag, RegionFlag)
VALUES ('O00002', null, 'The Great Ocean Road', 243, null, null, 'N', 'Y', 'N');

INSERT INTO Object (objectID, POIName, RoadName, RoadLength, RegionName, RegionArea, PointFlag, RoadFlag, RegionFlag)
VALUES ('O00003', null, null, null, 'Bundoora', 17, 'N', 'N', 'Y');

INSERT INTO PointObj (objectID, POIName, RestaurantFlag, ShopFlag, EducationFlag, HealthFlag, OthersFlag)
VALUES ('O00001', 'Nikos Fairfield', 'Y', 'Y', 'N', 'N', 'N');

INSERT INTO RoadObj (objectID, RoadName, RoadLength, HighwayFlag, PrimaryFlag, ResidentialFlag, FootpathFlag)
VALUES ('O00002', 'The Great Ocean Road', 243, 'Y', 'N', 'N', 'N');

INSERT INTO ObjectCoordinates (objectID, seq, latitude, longitude)
VALUES ('O00002', 2, -38.733826, 143.687271);

INSERT INTO Contributor (contributorID, points)
VALUES ('C00001', 55);

INSERT INTO Contributor (contributorID, points)
VALUES ('C00002', 176);

INSERT INTO Administrator (adminID, email, name, contributorID)
VALUES ('A00001', 'info@andrew.com.au', 'Andrew Garfield', 'C00001');

INSERT INTO Member (memberID, email, name, contributorID) 
VALUES ('M00001', 'john_mark@retail.com', 'John Mark', 'C00002');

INSERT INTO Modified (objectID, adminID, reason)
VALUES ('O00002', 'A00001', 'The "Road" object was missing some crucial coordinates, so I updated them to ensure they reflected accuracy and currency.');

INSERT INTO Trajectory (trajID, trajDateTime, trajDuration, trajDistance, contributorID)
VALUES ('T00001', TO_DATE('12-JUN-2016 09:30', 'DD-MON-YYYY HH24:MI'),TO_CHAR(INTERVAL '01:30:00' HOUR TO SECOND, 'HH24:MI:SS'), 100, 'C00001');

INSERT INTO Trajectory (trajID, trajDateTime, trajDuration, trajDistance, contributorID)
VALUES ('T00002', TO_DATE('10-MAR-2019 12:15', 'DD-MON-YYYY HH24:MI'), TO_CHAR(INTERVAL '01:10:00' HOUR TO SECOND, 'HH24:MI:SS'), 155, 'C00001');

INSERT INTO TrajectoryPoint (trajID, tpTimestamp, latitude, longitude)
VALUES ('T00001', TO_DATE('2023-05-03', 'YYYY-MM-DD'), 35.652832, 139.839478);

INSERT INTO TrajectoryPoint (trajID, tpTimestamp, latitude, longitude)
VALUES ('T00002', TO_DATE('2023-02-15', 'YYYY-MM-DD'), 12.894675, 55.123654);

SELECT P.objectID, P.POIName
FROM PointObj P, Object O
WHERE P.POIName = O.POIName AND O.PointFlag = 'Y';

SELECT R.objectID, R.RoadName, R.RoadLength
FROM RoadObj R, Object O
WHERE R.RoadName = O.RoadName AND R.RoadLength = O.RoadLength AND O.RoadFlag = 'Y';

SELECT *
FROM Object O
ORDER BY O.objectID;

SELECT *
FROM ObjectCoordinates OC
ORDER BY OC.objectID; 

SELECT *
FROM PointObj PO
ORDER BY PO.objectID; 

SELECT *
FROM RoadObj RO
ORDER BY RO.objectID;

SELECT *
FROM Contributor
ORDER BY contributorID;

SELECT *
FROM Administrator
ORDER BY adminID;

SELECT *
FROM Member
ORDER BY memberID;

SELECT *
FROM Modified M
ORDER BY M.objectID;

SELECT *
FROM Trajectory
ORDER BY trajID;

SELECT *
FROM TrajectoryPoint
ORDER BY trajID;
