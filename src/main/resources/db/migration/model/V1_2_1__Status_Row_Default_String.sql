ALTER TABLE Manifestation MODIFY COLUMN status VARCHAR(45);

ALTER TABLE Manifestation ALTER status SET DEFAULT 'innactive';
