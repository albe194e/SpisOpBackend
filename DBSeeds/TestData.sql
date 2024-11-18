INSERT INTO Address (id, streetName, postalCode, city, houseNumber, countryCode, country)
VALUES
    ('addr1', 'Main St', 12345, 'Springfield', '42', 'US', 'United States'),
    ('addr2', 'Elm St', 67890, 'Shelbyville', '24', 'US', 'United States'),
    ('addr3', 'Pine St', 11223, 'Metropolis', '101', 'US', 'United States'),
    ('addr4', 'Oak Ave', 33456, 'Gotham', '77B', 'US', 'United States');

INSERT INTO User (id, firstName, lastName, email, username, profilePicture, isAdmin, addressId)
VALUES
    ('user1', 'John', 'Doe', 'john.doe@example.com', 'johndoe', 'profile1.jpg', TRUE, 'addr1'),
    ('user2', 'Jane', 'Smith', 'jane.smith@example.com', 'janesmith', 'profile2.jpg', FALSE, 'addr2'),
    ('user3', 'Alice', 'Johnson', 'alice.johnson@example.com', 'alicej', 'profile3.jpg', FALSE, 'addr3'),
    ('user4', 'Bob', 'Williams', 'bob.williams@example.com', 'bobw', 'profile4.jpg', TRUE, 'addr4');

INSERT INTO Company (id, name, addressId)
VALUES
    ('comp1', 'Foodies Inc.', 'addr1'),
    ('comp2', 'Veggie Delight', 'addr2'),
    ('comp3', 'Healthy Bites', 'addr3');

INSERT INTO Company_Managers (companyId, userId)
VALUES
    ('comp1', 'user1'),
    ('comp2', 'user2'),
    ('comp3', 'user4');

INSERT INTO Community (id, name, createdById)
VALUES
    ('comm1', 'Food Lovers', 'user1'),
    ('comm2', 'Vegetarian Club', 'user2'),
    ('comm3', 'Healthy Eaters', 'user3');

INSERT INTO User_CommunityGroup (userId, communityGroupId)
VALUES
    ('user1', 'comm1'),
    ('user2', 'comm2'),
    ('user3', 'comm3'),
    ('user4', 'comm1'),
    ('user3', 'comm2');

INSERT INTO Allergy (id, name, icon, description)
VALUES
    ('allergy1', 'Peanuts', 'icon1.png', 'Peanut allergy'),
    ('allergy2', 'Dairy', 'icon2.png', 'Dairy allergy'),
    ('allergy3', 'Gluten', 'icon3.png', 'Gluten intolerance'),
    ('allergy4', 'Seafood', 'icon4.png', 'Seafood allergy');

INSERT INTO FoodPost (id, image, title, description, price, authorUserId, authorCompanyId, communityId)
VALUES
    ('post1', 'image1.jpg', 'Chocolate Cake', 'A delicious chocolate cake.', 15.99, 'user1', 'comp1', 'comm1'),
    ('post2', 'image2.jpg', 'Vegan Salad', 'Fresh and healthy vegan salad.', 12.50, 'user2', 'comp2', 'comm2'),
    ('post3', 'image3.jpg', 'Grilled Fish', 'Tasty grilled fish with spices.', 18.00, 'user3', 'comp3', 'comm3'),
    ('post4', 'image4.jpg', 'Gluten-Free Bread', 'A soft and tasty gluten-free bread.', 8.99, 'user4', 'comp1', 'comm1');

INSERT INTO FoodPost_Allergy (postId, allergyId)
VALUES
    ('post1', 'allergy1'),
    ('post2', 'allergy2'),
    ('post3', 'allergy4'),
    ('post4', 'allergy3');

INSERT INTO User_Company (userId, companyId)
VALUES
    ('user1', 'comp1'),
    ('user2', 'comp2'),
    ('user3', 'comp3'),
    ('user4', 'comp1');
