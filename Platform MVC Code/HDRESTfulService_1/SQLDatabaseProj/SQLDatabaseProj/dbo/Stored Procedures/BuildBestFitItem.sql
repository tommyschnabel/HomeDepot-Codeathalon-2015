CREATE PROCEDURE [dbo].[BuildBestFitItem]
	@ItemID int = -1
AS
	SELECT itemID, Items.typeID, itemName, typeName, Lat, Lng
	FROM Items 
	Left join Types ON Items.typeID = Types.typeID
	WHERE (Items.itemID = @ItemID or @ItemID = -1)
RETURN 0
