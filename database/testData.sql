USE idu0020;
DROP TABLE IF EXISTS LearningObject;

CREATE TABLE LearningObject
(
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  type        VARCHAR(10),
  title       VARCHAR(30),
  description TEXT
);

INSERT INTO LearningObject (type, title, description) VALUES ('textbook', 'Textbook for 1st Grade', 'Yet another book');
INSERT INTO LearningObject (type, title, description) VALUES ('book', 'Generation Kill', 'Guys in Iraq');
INSERT INTO LearningObject (type, title, description) VALUES ('workbook', 'Töövihik viiendale klassile', 'Pay to fill');