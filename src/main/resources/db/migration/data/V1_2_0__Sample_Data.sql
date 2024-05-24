-- Sample data for location table
INSERT INTO `ticket_shop`.`location` (`longitude`, `latitude`, `address`)
VALUES (40.7128, -74.0060, 'New York, NY, USA'),
       (34.0522, -118.2437, 'Los Angeles, CA, USA'),
       (51.5074, -0.1278, 'London, UK'),
       (48.8566, 2.3522, 'Paris, France'),
       (37.7749, -122.4194, 'San Francisco, CA, USA'),
       (35.6895, 139.6917, 'Tokyo, Japan'),
       (40.4168, -3.7038, 'Madrid, Spain'),
       (55.7558, 37.6176, 'Moscow, Russia'),
       (52.5200, 13.4050, 'Berlin, Germany'),
       (41.9028, 12.4964, 'Rome, Italy'),
       (22.3193, 114.1694, 'Hong Kong'),
       (19.0760, 72.8777, 'Mumbai, India'),
       (37.5665, 126.9780, 'Seoul, South Korea'),
       (31.2304, 121.4737, 'Shanghai, China'),
       (45.4642, 9.1900, 'Milan, Italy');

-- Sample data for manifestation table
INSERT INTO `ticket_shop`.`manifestation` (`name`, `type`, `num_of_seats`, `date`, `price_regular`, `status`, `location_id`,
                             `user_id_seller`)
VALUES ('Concert', 'Music', 1000, '2024-06-15 19:00:00', 50, 1, 1, 1),
       ('Theater Play', 'Drama', 500, '2024-07-20 18:30:00', 30, 1, 2, 4),
       ('Sports Event', 'Sports', 2000, '2024-08-10 15:00:00', 60, 1, 3, 2),
       ('Conference', 'Business', 300, '2024-09-05 09:00:00', 20, 1, 1, 3),
       ('Opera Night', 'Music', 800, '2024-06-25 20:00:00', 70, 1, 5, 4),
       ('Comedy Show', 'Comedy', 300, '2024-07-30 19:30:00', 25, 1, 10, 1),
       ('Basketball Game', 'Sports', 1500, '2024-08-20 16:00:00', 45, 1, 3, 5),
       ('Tech Summit', 'Technology', 400, '2024-09-15 10:00:00', 15, 1, 1, 6),
       ('Ballet Performance', 'Dance', 700, '2024-10-05 18:00:00', 60, 1, NULL, 9),
       ('Film Festival', 'Film', 200, '2024-11-12 12:00:00', 20, 1, NULL, 1),
       ('Stand-up Comedy', 'Comedy', 500, '2024-12-08 21:00:00', 30, 1, NULL, NULL),
       ('Soccer Match', 'Sports', 2000, '2024-06-18 17:30:00', 50, 1, NULL, NULL),
       ('Art Exhibition', 'Art', 600, '2024-07-10 11:00:00', 35, 1, NULL, NULL),
       ('Business Seminar', 'Business', 250, '2024-08-02 09:30:00', 20, 1, NULL, NULL),
       ('Jazz Concert', 'Music', 400, '2024-09-25 19:30:00', 40, 1, NULL, NULL);

-- Sample data for user table
INSERT INTO `ticket_shop`.`user` (`username`, `password`, `first_name`, `last_name`, `reward_points`, `user_type`)
VALUES ('user1', 'password1', 'John', 'Doe', 100, 'regular'),
       ('user2', 'password2', 'Jane', 'Smith', 50, 'regular'),
       ('admin1', 'adminpass1', 'Admin', 'Adminson', NULL, 'admin'),
       ('user3', 'password3', 'Alice', 'Johnson', 75, 'regular'),
       ('user4', 'password4', 'Emily', 'Brown', 120, 'regular'),
       ('user5', 'password5', 'Michael', 'Wilson', 80, 'regular'),
       ('user6', 'password6', 'Sophia', 'Martinez', 60, 'regular'),
       ('user7', 'password7', 'William', 'Taylor', 110, 'regular'),
       ('user8', 'password8', 'Ethan', 'Anderson', 90, 'regular'),
       ('user9', 'password9', 'Isabella', 'Thomas', 70, 'regular'),
       ('user10', 'password10', 'James', 'Hernandez', 100, 'regular'),
       ('user11', 'password11', 'Ava', 'Young', 75, 'regular'),
       ('user12', 'password12', 'Alexander', 'Lee', 85, 'regular'),
       ('user13', 'password13', 'Olivia', 'King', 95, 'regular'),
       ('user14', 'password14', 'Daniel', 'Scott', 130, 'regular'),
       ('admin2', 'adminpass2', 'Admin', 'Adminson', NULL, 'admin'),
       ('user15', 'password15', 'Charlotte', 'Evans', 140, 'regular');

-- Sample data for ticket table
INSERT INTO `ticket_shop`.`ticket` (`date`, `price`, `status`, `type`, `manifestation_id`, `user_id`)
VALUES ('2024-06-10 15:30:00', 50, 1, 'Regular', 1, 3),
       ('2024-07-18 17:00:00', 30, 1, 'Regular', 2, 6),
       ('2024-08-05 10:30:00', 60, 1, 'Regular', 3, 8),
       ('2024-09-03 08:45:00', 20, 1, 'Regular', 4, 4),
       ('2024-06-20 14:45:00', 70, 1, 'Regular', 5, 9),
       ('2024-07-25 20:15:00', 25, 1, 'Regular', 1, 4),
       ('2024-08-15 11:30:00', 45, 1, 'Regular', 1, 6),
       ('2024-09-20 10:45:00', 15, 1, 'Regular', 6, 7),
       ('2024-10-10 19:15:00', 60, 1, 'Regular', 7, 5),
       ('2024-11-20 13:45:00', 20, 1, 'Regular', NULL, NULL),
       ('2024-12-15 22:30:00', 30, 1, 'Regular', 6, 3),
       ('2024-06-22 16:00:00', 50, 1, 'Regular', NULL, NULL),
       ('2024-07-15 12:30:00', 35, 1, 'Regular', NULL, NULL),
       ('2024-08-05 14:45:00', 20, 1, 'Regular', NULL, NULL),
       ('2024-09-28 19:30:00', 40, 1, 'Regular', NULL, NULL);
