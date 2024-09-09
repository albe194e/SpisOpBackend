CREATE TABLE Address (
    id VARCHAR(36) PRIMARY KEY,
    streetName VARCHAR(255),
    postalCode INT,
    city VARCHAR(255),
    houseNumber VARCHAR(50),
    countryCode VARCHAR(10),
    country VARCHAR(255)
);

CREATE TABLE User (
    id VARCHAR(36) PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255),
    username VARCHAR(255),
    profilePicture VARCHAR(255),
    isAdmin BOOLEAN,
    addressId VARCHAR(36),
    FOREIGN KEY (addressId) REFERENCES Address(id)
);

CREATE TABLE Company (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255),
    addressId VARCHAR(36),
    FOREIGN KEY (addressId) REFERENCES Address(id)
);

CREATE TABLE Community (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255),
    createdById VARCHAR(36),
    FOREIGN KEY (createdById) REFERENCES User(id)
);

CREATE TABLE Allergy (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255),
    icon VARCHAR(255),
    description TEXT
);

CREATE TABLE FoodPost (
    id VARCHAR(36) PRIMARY KEY,
    image VARCHAR(255),
    title VARCHAR(255),
    description TEXT,
    price DECIMAL(10, 2),
    authorUserId VARCHAR(36),
    authorCompanyId VARCHAR(36),
    communityId VARCHAR(36),
    FOREIGN KEY (authorUserId) REFERENCES User(id),
    FOREIGN KEY (authorCompanyId) REFERENCES Company(id),
    FOREIGN KEY (communityId) REFERENCES Community(id)
);

CREATE TABLE Notification (
    id VARCHAR(36) PRIMARY KEY,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    postId VARCHAR(36),
    FOREIGN KEY (postId) REFERENCES FoodPost(id)
);

-- Join tables for many-to-many relationships
CREATE TABLE User_CommunityGroup (
    userId VARCHAR(36),
    communityGroupId VARCHAR(36),
    PRIMARY KEY (userId, communityGroupId),
    FOREIGN KEY (userId) REFERENCES User(id),
    FOREIGN KEY (communityGroupId) REFERENCES Community(id)
);

CREATE TABLE User_Company (
    userId VARCHAR(36),
    companyId VARCHAR(36),
    PRIMARY KEY (userId, companyId),
    FOREIGN KEY (userId) REFERENCES User(id),
    FOREIGN KEY (companyId) REFERENCES Company(id)
);

CREATE TABLE Company_Managers (
    companyId VARCHAR(36),
    userId VARCHAR(36),
    PRIMARY KEY (companyId, userId),
    FOREIGN KEY (companyId) REFERENCES Company(id),
    FOREIGN KEY (userId) REFERENCES User(id)
);

CREATE TABLE FoodPost_Allergy (
    postId VARCHAR(36),
    allergyId VARCHAR(36),
    PRIMARY KEY (postId, allergyId),
    FOREIGN KEY (postId) REFERENCES FoodPost(id),
    FOREIGN KEY (allergyId) REFERENCES Allergy(id)
);
