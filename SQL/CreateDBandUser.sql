/*MySQL*/ 
CREATE DATABASE DBEvaluator;
USE DBEvaluator;
CREATE USER 'dbUser'@'localhost' IDENTIFIED BY 'megaHeslo';
GRANT ALL PRIVILEGES ON DBEvaluator.* TO 'dbUser'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE Kategorie (
	id int primary key,
	kategoria1 varchar(50),
    kategoria2 varchar(50),
    kategoria3 varchar(50),
    kategoria4 varchar(50)
)

INSERT INTO KATEGORIE VALUES 
(1, 'abf', 'adf', 'fas', 'ttt'),
(2, 'abf', 'adf', 'fas', 'ttt'),
(3, 'abf', 'xxx', 'fas', 'ooo'),
(4, 'fsd', 'red', 'fas', 'ttt');

SELECT * FROM Kategorie