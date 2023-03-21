

CREATE TABLE IF NOT EXISTS messages (
                                      id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                      user VARCHAR(50) NOT NULL,
                                      admin VARCHAR(50) NOT NULL,
                                      text VARCHAR(1500) NOT NULL,
                                      CONSTRAINT pk_messages PRIMARY KEY (id)

);