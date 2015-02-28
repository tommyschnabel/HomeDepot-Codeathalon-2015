CREATE PROCEDURE [dbo].[FindTypeInformation]
	@TypeID int = -1
AS
	SELECT typeID, typeName, Lat, Lng
	FROM Types
	WHERE (typeID = @TypeID or @TypeID = -1)
RETURN 0
