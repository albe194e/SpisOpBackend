DELIMITER //
CREATE PROCEDURE AddFoodPost(
    IN postId VARCHAR(36),
    IN image VARCHAR(255),
    IN title VARCHAR(255),
    IN description TEXT,
    IN price DECIMAL(10, 2),
    IN authorUserId VARCHAR(36),
    IN authorCompanyId VARCHAR(36),
    IN communityId VARCHAR(36)
)
BEGIN
    INSERT INTO FoodPost (id, image, title, description, price, authorUserId, authorCompanyId, communityId)
    VALUES (postId, image, title, description, price, authorUserId, authorCompanyId, communityId);
END;
//
DELIMITER ;




CALL AddFoodPost(
        'post5',
        'image5.jpg',
        'Organic Apples',
        'Fresh organic apples directly from the farm.',
        9.99,
        'user1',
        'comp1',
        'comm4'
     );


DELIMITER //

CREATE PROCEDURE AddUser(
    IN userId VARCHAR(36),
    IN firstName VARCHAR(255),
    IN lastName VARCHAR(255),
    IN email VARCHAR(255),
    IN username VARCHAR(255),
    IN profilePicture VARCHAR(255),
    IN isAdmin BOOLEAN,
    IN addressId VARCHAR(36)
)
BEGIN
    -- Check if email already exists
    IF EXISTS (SELECT 1 FROM User WHERE email = email) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Email already exists';
    END IF;

    -- Check if username already exists
    IF EXISTS (SELECT 1 FROM User WHERE username = username) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Username already exists';
    END IF;

    -- Insert the new user
    INSERT INTO User (id, firstName, lastName, email, username, profilePicture, isAdmin, addressId)
    VALUES (userId, firstName, lastName, email, username, profilePicture, isAdmin, addressId);
END;
//

DELIMITER ;
