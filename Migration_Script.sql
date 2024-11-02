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
