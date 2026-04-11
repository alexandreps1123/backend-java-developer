CREATE TABLE EPISODE
(
    id              VARCHAR(36) PRIMARY KEY,
    id_integration  INTEGER NOT NULL UNIQUE,
    fk_show         VARCHAR(36) NOT NULL,
    name            VARCHAR(265),
    season          INTEGER,
    number          INTEGER,
    type            VARCHAR(265),
    airdate         DATE,
    airtime         TIME,
    airstamp		TIMESTAMPTZ,
    runtime			INTEGER,
    rating			NUMERIC(5, 2),
    summary			TEXT,
    created_at		TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at		TIMESTAMPTZ NOT NULL DEFAULT NOW(),
	CONSTRAINT fk_episode_show FOREIGN KEY (fk_show) REFERENCES SHOW(id) ON DELETE CASCADE
);

CREATE INDEX idx_episode_fk_show ON EPISODE(fk_show);
CREATE INDEX idx_episode_id_integration ON EPISODE(id_integration);