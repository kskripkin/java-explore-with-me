CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     name VARCHAR(150) NOT NULL,
                                     email VARCHAR(150) NOT NULL UNIQUE,
                                     CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categories (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     name VARCHAR(150) NOT NULL UNIQUE,
                                     CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS locations (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     lat VARCHAR(50) NOT NULL,
                                     lon VARCHAR(50) NOT NULL,
                                     CONSTRAINT pk_locations PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS events (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     annotation VARCHAR(500),
                                     category BIGINT,
                                     confirmed_requests BIGINT,
                                     created_on TIMESTAMP,
                                     description VARCHAR(1000),
                                     event_date TIMESTAMP,
                                     initiator BIGINT,
                                     location BIGINT,
                                     paid BOOLEAN,
                                     participant_limit BIGINT,
                                     published_on TIMESTAMP,
                                     request_moderation BOOLEAN,
                                     state_action VARCHAR(50) NOT NULL,
                                     title VARCHAR(200),
                                     views BIGINT,
                                     CONSTRAINT pk_events PRIMARY KEY (id),
                                     CONSTRAINT fk_events_to_category FOREIGN KEY(category) REFERENCES categories(id),
                                     CONSTRAINT fk_events_to_location FOREIGN KEY(location) REFERENCES locations(id),
                                     CONSTRAINT fk_events_to_users FOREIGN KEY(initiator) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS compilations (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     pinned BOOLEAN NOT NULL,
                                     title VARCHAR(150) NOT NULL,
                                     CONSTRAINT pk_compilations PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS events_to_compilation (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     id_compilation BIGINT NOT NULL,
                                     id_event BIGINT NOT NULL,
                                     CONSTRAINT pk_eventsToCompilation PRIMARY KEY (id),
                                     CONSTRAINT fk_eventsToCompilation_to_idCompilation FOREIGN KEY(id_compilation) REFERENCES compilations(id),
                                     CONSTRAINT fk_eventsToCompilation_to_idEvent FOREIGN KEY(id_event) REFERENCES events(id)
);

CREATE TABLE IF NOT EXISTS requests (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     created TIMESTAMP NOT NULL,
                                     event BIGINT NOT NULL,
                                     requester BIGINT NOT NULL,
                                     status VARCHAR(50) NOT NULL,
                                     CONSTRAINT pk_requests PRIMARY KEY (id),
                                     CONSTRAINT fk_requests_to_event FOREIGN KEY(event) REFERENCES events(id),
                                     CONSTRAINT fk_requests_to_users FOREIGN KEY(requester) REFERENCES users(id)
);