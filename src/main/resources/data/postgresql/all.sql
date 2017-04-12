CREATE TABLE IF NOT EXISTS files
(
  file_name VARCHAR(256) NOT NULL,
  data      TEXT         NOT NULL
);
CREATE TABLE IF NOT EXISTS user_roles
(
  user_id INTEGER PRIMARY KEY NOT NULL,
  role    VARCHAR(45)         NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
  user_id  SERIAL PRIMARY KEY NOT NULL,
  username VARCHAR(20)        NOT NULL,
  password VARCHAR(60)        NOT NULL,
  enabled  SMALLINT DEFAULT 1 NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS files_file_name_uindex
  ON files (file_name);
ALTER TABLE user_roles
  DROP CONSTRAINT IF EXISTS user_roles_users_user_id_fk;
ALTER TABLE user_roles
  ADD CONSTRAINT user_roles_users_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id);
CREATE UNIQUE INDEX IF NOT EXISTS users_username_uindex
  ON users (username);