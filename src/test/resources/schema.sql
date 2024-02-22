CREATE TABLE IF NOT EXISTS assurance (
                                         assurance_id BIGINT NOT NULL AUTO_INCREMENT,
                                         assurance_number VARCHAR(255),
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (assurance_id)
    );

-- Create Auto table
CREATE TABLE IF NOT EXISTS auto (auto_id BIGINT NOT NULL AUTO_INCREMENT, assurance_id BIGINT,
    model VARCHAR(255),
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (auto_id),
    FOREIGN KEY (assurance_id) REFERENCES assurance(assurance_id)
    );