CREATE TABLE IF NOT EXISTS film(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    director VARCHAR(100),
    duration INT,
    country VARCHAR(100),
    presale INTEGER,
    puntuation DECIMAL(10, 2),
    investment INT
    );

CREATE TABLE IF NOT EXISTS scene(
    id SERIAL PRIMARY KEY,
    description VARCHAR(100),
    budget INT,
    minutes INT,
    film_id INT NOT NULL,
    FOREIGN KEY (film_id) REFERENCES film(id)
    );

CREATE TABLE IF NOT EXISTS character(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    actor_name VARCHAR(100),
    description VARCHAR(200),
    cost DECIMAL(10,2),
    scene_id INT NOT NULL,
    FOREIGN KEY (scene_id) REFERENCES scene(id)
    );
