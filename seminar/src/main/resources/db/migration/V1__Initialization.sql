BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Users" (
	"id"	INTEGER,
	"username"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL,
	"email"	TEXT NOT NULL UNIQUE,
	"user_type"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Trips" (
	"id"	INTEGER,
	"trip_name"	TEXT NOT NULL,
	"date"	DATE NOT NULL,
	"location"	TEXT NOT NULL,
	"capacity"	INTEGER NOT NULL,
	"guide_id"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("guide_id") REFERENCES "Users"("id")
);
CREATE TABLE IF NOT EXISTS "Reservations" (
	"id"	INTEGER,
	"user_id"	INTEGER NOT NULL,
	"trip_id"	INTEGER NOT NULL,
	"number_of_people"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("user_id") REFERENCES "Users"("id"),
	FOREIGN KEY("trip_id") REFERENCES "Trips"("id")
);
CREATE TABLE IF NOT EXISTS "Preferences" (
	"id"	INTEGER,
	"reservation_id"	INTEGER NOT NULL,
	"preference"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("reservation_id") REFERENCES "Reservations"("id")
);
COMMIT;




-- Insert some users (with hashed passwords)
-- password: password123
INSERT INTO "Users" ("username", "password", "email", "user_type")
VALUES
    ('guide1', '$2a$10$J0pA91zJX5q9yDOzM59pTeIYZxPq9RUclJeQSOdCgC1ckozwGTqVq', 'guide1@example.com', 'guide'),  -- password123 hashed
    ('user1', '$2a$10$S44VThzZdz4ugLU4hg4Fv2C6uFwUwvQpMKxS7XcxmJwQXfyz7KlDu', 'user1@example.com', 'user'),  -- password123 hashed
    ('user2', '$2a$10$L74XJvfi5du4mf3Dh4LOw3yGF3CmubOzzOtbvQI9dCmZT7myLZ9J6', 'user2@example.com', 'user');  -- password123 hashed

-- Insert some trips (no changes here)
INSERT INTO "Trips" ("trip_name", "date", "location", "capacity", "guide_id")
VALUES
    ('Mountain Adventure', '2024-12-15', 'Mountain Base', 10, 1),
    ('Beach Getaway', '2024-12-20', 'Sunny Beach', 15, 1);

-- Insert some reservations (no changes here)
INSERT INTO "Reservations" ("user_id", "trip_id", "number_of_people")
VALUES
    (2, 1, 2),
    (3, 1, 3),
    (2, 2, 1);

-- Insert some preferences (no changes here)
INSERT INTO "Preferences" ("reservation_id", "preference")
VALUES
    (1, 'Vegetarian'),
    (1, 'No nuts'),
    (2, 'Window seat'),
    (3, 'Early check-in');
