DROP TABLE IF EXISTS pet;

CREATE TABLE pet(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20),
  tags VARCHAR(20),
  status VARCHAR(20)
);