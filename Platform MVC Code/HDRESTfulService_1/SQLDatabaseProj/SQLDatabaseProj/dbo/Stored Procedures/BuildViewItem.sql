CREATE PROCEDURE [dbo].[BuildViewItem]
	@ItemID int = -1
AS
	SELECT itemID, Items.typeID, itemName, typeName
	FROM Items 
	Left join Types ON Items.typeID = Types.typeID
	WHERE (Items.itemID = @ItemID or @ItemID = -1)
RETURN 0
