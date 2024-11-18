DELIMITER //
CREATE TRIGGER PreventUserDeletion
    BEFORE DELETE ON User
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM FoodPost WHERE authorUserId = OLD.id) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot delete user who authored FoodPosts';
    END IF;
END;
//
DELIMITER ;
