ALTER TABLE PUBLIC.SUPERHEROES ADD STATUS VARCHAR(8) NOT NULL;
ALTER TABLE PUBLIC.SUPERHEROES ADD CREATED_AT TIMESTAMP DEFAULT NOW();
ALTER TABLE PUBLIC.SUPERHEROES ADD UPDATED_AT TIMESTAMP DEFAULT NOW();