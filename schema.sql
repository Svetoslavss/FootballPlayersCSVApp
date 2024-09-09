CREATE TABLE IF NOT EXISTS teams (
    teamId SERIAL PRIMARY KEY,
    managerName VARCHAR(255) NOT NULL,
    team_group VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS players (
    id SERIAL PRIMARY KEY,
    teamNumber INT NOT NULL,
    position VARCHAR(255) NOT NULL,
    fullName VARCHAR(255) NOT NULL,
    teamId INT,
    FOREIGN KEY (teamId) REFERENCES teams(teamId)
);