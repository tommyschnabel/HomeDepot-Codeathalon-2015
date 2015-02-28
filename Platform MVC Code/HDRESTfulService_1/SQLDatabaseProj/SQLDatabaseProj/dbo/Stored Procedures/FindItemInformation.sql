CREATE PROCEDURE [dbo].[FindItemInformation]
	@ItemID int = -1
AS
	SELECT itemID, itemName, typeID
	From Items
	where (itemID = @ItemID or @ItemID = -1)
RETURN 0
