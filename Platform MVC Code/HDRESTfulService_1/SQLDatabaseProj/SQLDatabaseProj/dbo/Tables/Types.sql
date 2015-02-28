CREATE TABLE [dbo].[Types] (
    [typeID]   INT            IDENTITY (1, 1) NOT NULL,
    [typeName] NVARCHAR (MAX) NOT NULL,
    [Lat]      FLOAT (53)     NOT NULL,
    [Lng]      FLOAT (53)     NOT NULL,
    CONSTRAINT [PK_Types] PRIMARY KEY CLUSTERED ([typeID] ASC)
);

