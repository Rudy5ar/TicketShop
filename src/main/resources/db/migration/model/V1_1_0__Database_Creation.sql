CREATE TABLE IF NOT EXISTS `Location`
(
    `id`        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `longitude` DOUBLE       NOT NULL,
    `latitude`  DOUBLE       NOT NULL,
    `address`   VARCHAR(100) NULL
);


CREATE TABLE IF NOT EXISTS `Manifestation`
(
    `id`             BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`           VARCHAR(45) NOT NULL,
    `type`           VARCHAR(45) NOT NULL,
    `num_of_seats`   INT         NOT NULL,
    `date`           DATETIME    NOT NULL,
    `price_regular`  DECIMAL     NOT NULL,
    `status`         INT         NOT NULL,
    `location_id`    BIGINT,
    `user_id_seller` BIGINT
);

CREATE TABLE IF NOT EXISTS `User`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username`      VARCHAR(45) NOT NULL,
    `password`      VARCHAR(45) NOT NULL,
    `first_name`    VARCHAR(45) NOT NULL,
    `last_name`     VARCHAR(45) NOT NULL,
    `reward_points` INT         NULL,
    `user_type`     VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Ticket`
(
    `id`               BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `date`             DATETIME    NOT NULL,
    `price`            DECIMAL     NOT NULL,
    `status`           INT         NOT NULL,
    `type`             VARCHAR(45) NOT NULL,
    `manifestation_id` BIGINT,
    `user_id_buyer`    BIGINT
);

ALTER TABLE `Manifestation`
    ADD FOREIGN KEY (user_id_seller) REFERENCES User (`id`);
ALTER TABLE `Manifestation`
    ADD FOREIGN KEY (location_id) REFERENCES Location (`id`);
ALTER TABLE `Ticket`
    ADD FOREIGN KEY (manifestation_id) REFERENCES Manifestation (`id`);
ALTER TABLE `Ticket`
    ADD FOREIGN KEY (user_id_buyer) REFERENCES User (`id`);