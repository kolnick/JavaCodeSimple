SELECT COLUMN_NAME
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA=DATABASE()
AND TABLE_NAME='book'
AND COLUMN_NAME='id';

ALTER TABLE book ADD COLUMN `id` int(11) primary key auto_increment;