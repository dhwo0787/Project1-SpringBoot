CREATE TABLE score (
    email VARCHAR(100) NOT NULL,
    scoreId INT NOT NULL,
    scale VARCHAR(10000) NOT NULL,
    dir VARCHAR(500),
    accuracy INT,
    c_time TIMESTAMP,
    CONSTRAINT score_Pk PRIMARY KEY(email,scoreId,c_time)
);