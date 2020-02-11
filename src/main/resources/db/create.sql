SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS sightingAnimals (
  id int PRIMARY KEY auto_increment,
  animalName VARCHAR,
  rangerid INTEGER
);

--CREATE TABLE IF NOT EXISTS endangeredAnimals (
--  id int PRIMARY KEY auto_increment,
--  endangeredName VARCHAR,
--  endangeredHealth VARCHAR,
--  rangerid INTEGER
--);

CREATE TABLE IF NOT EXISTS rangers(
    id int PRIMARY KEY auto_increment,
    rangerName VARCHAR,
    rangerLocation VARCHAR
);
